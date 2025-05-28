package org.ivy.settlement.relay.watchdog.state.log;


import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.ivy.settlement.relay.watchdog.constants.Role;
import org.ivy.settlement.relay.watchdog.contract.SafetyManager;
import org.ivy.settlement.relay.watchdog.model.RLPMap;
import org.ivy.settlement.relay.watchdog.state.BaseProcessor;
import org.ivy.settlement.relay.watchdog.state.SafetyState;
import org.ivy.settlement.relay.watchdog.state.log.store.*;
import org.ivy.settlement.relay.watchdog.state.log.store.*;
import org.web3j.protocol.core.methods.response.EthLog;

public class SafetyLog extends BaseProcessor implements SafetyState {

    private final String PREFIX1 = "safety:war";
    private final String PREFIX2 = "safety:report";

    private final String PREFIX3 = "safety:challenge";
    // Persistable

    private String PREFIX4 = "safety:deadPlayers";

    private final String PREFIX5 = "safety:whoIsIn";
//    ConcurrentMap<String, Long> whoIsIn;

    public SafetyLog(LRUCache cache, RocksDbSource dbSource) {
        super(cache, dbSource);
        PREFIX = "Safety";
    }


    @Override
    public void processLog(EthLog log) {
        SafetyWarState state;
        SafetyReportState reportState;
        switch (log.getTopics().getFirst()) {
            case "ChallengerClaimed":
                SafetyManager.ChallengerClaimedEventResponse claim = SafetyManager.getChallengerClaimedEventFromLog(log.asWeb3jLog());
                state = tryReadSafetyWar(claim.warHeight.longValue());
                ClaimInfo c = new ClaimInfo(1, ChallengeType.findByType(claim.claimType.intValue()),new HeaderState(claim.chainId.intValue(), claim.height.longValue(), claim.hash));
                state.putChallenge(claim.challenger, c);
                break;
            case "WarCreated":
                SafetyManager.WarCreatedEventResponse war = SafetyManager.getWarCreatedEventFromLog(log.asWeb3jLog());
                state = new SafetyWarState(war.warHeight.longValue());
                updateSafetyWar(war.warHeight.longValue(), state);
                break;
            case "WarEnded":
                SafetyManager.WarEndedEventResponse end = SafetyManager.getWarEndedEventFromLog(log.asWeb3jLog());
                state = tryReadSafetyWar(end.warheight.longValue());
                state.setEnd(true);
                updateSafetyWar(end.warheight.longValue(), state);
                break;
            case "ChallengeRequested":
                SafetyManager.ChallengeRequestedEventResponse request = SafetyManager.getChallengeRequestedEventFromLog(log.asWeb3jLog());
                state = tryReadSafetyWar(request.warHeight.longValue());
                state.updateChalleneInfo(request.challenger, 2);
                state.updateChalleneInfo(request.defender, 2);
                updateSafetyWar(request.warHeight.longValue(), state);
                // TODO: other state management
                ClaimInfo ci = state.getChallenge(request.challenger);
                Game game = new Game(request.warHeight.longValue(), ci, ChallengeStatus.REQUEST);
                updateSafetyChallenge(request.challengeIndex.longValue(), game);
                updateSafetyWhoIsIn(request.challengeIndex.longValue(), request.challenger, request.defender);
            case "ChallengeSettled":
                SafetyManager.ChallengeSettledEventResponse settle = SafetyManager.getChallengeSettledEventFromLog(log.asWeb3jLog());
                Game game2 = tryReadSafetyChallenge(settle.challengeIndex.longValue());
                game2.setStatus(ChallengeStatus.ENDED);
                state = tryReadSafetyWar(game2.getWarHeight());
                // TODO
                ClaimInfo loseClaim = state.getChallengeMap().get(settle.loser);
                loseClaim.setClaimStatus(3);
                state.putChallenge(settle.loser, loseClaim);

                // TODO: atomic code block, requires atomic DB writes.
                updateSafetyWar(game2.getWarHeight(), state);
                updateSafetyChallenge(game2.getWarHeight(), game2);
                removeSafetyWhoIsIn(settle.winner, settle.loser);
                break;
            case "FaultyBlockReported":
                SafetyManager.FaultyBlockReportedEventResponse faulty = SafetyManager.getFaultyBlockReportedEventFromLog((log.asWeb3jLog()));
                // usually requires one-time init, no subsequent changes
                reportState = new SafetyReportState(faulty.bridgeHeight.longValue());
                reportState.addReport(faulty.sender, new Report(ChallengeType.REPORTFAULTY,ChallengeStatus.ENDED));
                updateSafetyReport(faulty.bridgeHeight.longValue(), reportState);
                break;
            case "UnsortedBlockReported":
                SafetyManager.UnsortedBlockReportedEventResponse unsorted = SafetyManager.getUnsortedBlockReportedEventFromLog(log.asWeb3jLog());

                reportState = new SafetyReportState(unsorted.bridgeHeight.longValue());
                reportState.addReport(unsorted.sender, new Report(ChallengeType.REPORTORDER,ChallengeStatus.ENDED));
                updateSafetyReport(unsorted.bridgeHeight.longValue(), reportState);
                break;
            default: break;
        }
    }

    @Override
    public ClaimInfo claimInfo(Long warHeight, String address) {
        SafetyWarState state = tryReadSafetyWar(warHeight);
        if (state != null) {
            return state.getChallenge(address);
        }
        return null;
    }

    @Override
    public Boolean isReport(Long warHeight, String address) {
        SafetyReportState state = tryReadSafetyReport(warHeight);
        if (state != null) {
            return state.getReportMap().get(address).getType() != null;
        }
        return false;
    }

    @Override
    public Boolean warIsEnd(Long warHeight) {
        SafetyWarState state = tryReadSafetyWar(warHeight);
        if (state != null) {
            return state.isEnd;
        }
        return false;
    }

    @Override
    public ChallengeStatus challengeState(Long challengeIndex) {
        Game game = tryReadSafetyChallenge(challengeIndex);
        if (game != null) {
            return game.getStatus();
        }
        return null;
    }
    @Override
    public String findAlivePlayer(Long warHeight, Role role) {
        switch (role) {
            case CHALLENGER -> {
                //TODO： search for defenders first, then validators.
                return "";
            }
            case DEFENDER -> {
                SafetyWarState state = tryReadSafetyWar(warHeight);
                for (String address: state.getChallengeMap().keySet()) {
                    if (state.getChallenge(address).getClaimStatus() == 1) {
                        return address;
                    }
                }
                return null;
            }
            default -> throw new RuntimeException("Unknown role");
        }
    }

    @Override
    public Long onGoingChallenge(Long warHeight, String address) {
        return tryReadSafetyWhoIsIn().get(address);
    }

    @Override
    public Boolean warOvertime(Long warHeight) {
        return null;
    }

    private SafetyWarState tryReadSafetyWar(Long warHeight){
        Object key = PREFIX1+warHeight;
        SafetyWarState state = (SafetyWarState) readStateFromCache(key);
        if (state == null) {
            state = (SafetyWarState) readStateFromDb(SafetyWarState.class, key);
            writeStateToCache(key, state);
        }
        return state;
    }

    private void updateSafetyWar(Long warHeight, SafetyWarState state) {
        Object key = PREFIX1+warHeight;
        writeStateToCache(key, state);
        writeStateToCache(key, state);
    }


    private SafetyReportState tryReadSafetyReport(Long warHeight){
        Object key = PREFIX2+warHeight;
        SafetyReportState state = (SafetyReportState) readStateFromCache(key);
        if (state == null) {
            state = (SafetyReportState) readStateFromDb(SafetyReportState.class, key);
            writeStateToCache(key, state);
        }
        return state;
    }
    private void updateSafetyReport(Long warHeight, SafetyReportState state) {
        Object key = PREFIX2+warHeight;
        writeStateToCache(key, state);
        writeStateToCache(key, state);
    }

    private Game tryReadSafetyChallenge(Long challengeIndex){
        Object key = PREFIX3+challengeIndex;
        Game state = (Game) readStateFromCache(key);
        if (state == null) {
            state = (Game) readStateFromDb(Game.class, key);
            writeStateToCache(key, state);
        }
        return state;
    }
    private void updateSafetyChallenge(Long challengeIndex, Game state) {
        Object key = PREFIX3+challengeIndex;
        writeStateToCache(key, state);
        writeStateToCache(key, state);
    }

    private RLPMap<String, Long> tryReadSafetyWhoIsIn(){
        Object key = PREFIX5;
        RLPMap map = (RLPMap) readStateFromCache(key);
        if (map == null) {
            map = (RLPMap) readStateFromDb(RLPMap.class, key);
            if (map == null) {
                map = new RLPMap();
            } else {
                writeStateToCache(key, map);
            }
        }
        return map;
    }
    private void updateSafetyWhoIsIn(Long challengeIndex, String address1, String address2) {
        Object key = PREFIX5;
        RLPMap<String, Long> hashMap = tryReadSafetyWhoIsIn();
        hashMap.put(address1, challengeIndex);
        hashMap.put(address2, challengeIndex);
        writeStateToCache(key, hashMap);
        writeStateToDb(key, hashMap);
    }

    private void removeSafetyWhoIsIn(String address1, String address2) {
        Object key = PREFIX5;
        RLPMap<String, Long> hashMap = tryReadSafetyWhoIsIn();
        hashMap.remove(address1);
        hashMap.remove(address2);
        writeStateToCache(key, hashMap);
        writeStateToDb(key, hashMap);
    }
}
