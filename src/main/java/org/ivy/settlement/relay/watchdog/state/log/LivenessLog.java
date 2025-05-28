package org.ivy.settlement.relay.watchdog.state.log;


import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.ivy.settlement.relay.watchdog.constants.Role;
import org.ivy.settlement.relay.watchdog.contract.LivenessManager;
import org.ivy.settlement.relay.watchdog.model.HeaderState;
import org.ivy.settlement.relay.watchdog.model.RLPMap;
import org.ivy.settlement.relay.watchdog.state.BaseProcessor;
import org.ivy.settlement.relay.watchdog.state.LivenessState;
import org.ivy.settlement.relay.watchdog.state.log.store.ClaimInfo;
import org.ivy.settlement.relay.watchdog.state.log.store.Game;
import org.ivy.settlement.relay.watchdog.state.log.store.LivenessChallengeState;
import org.ivy.settlement.relay.watchdog.state.log.store.SafetyWarState;
import org.web3j.protocol.core.methods.response.EthLog;

import java.util.ArrayList;

import static org.ivy.settlement.relay.watchdog.constants.ChallengeStatus.REQUEST;

public class LivenessLog extends BaseProcessor implements LivenessState {

    // hash -> war
    private String PREFIX1 = "liveness:claim";
    private String PREFIX2 = "liveness:allClaimIndex";

    private String PREFIX3 = "liveness:challenge";

    private String PREFIX4 = "liveness:whoIsIn";

    public LivenessLog(LRUCache cache, RocksDbSource dbSource) {
        super(cache, dbSource);
    }


    @Override
    public void processLog(EthLog log) {
        switch (log.getTopics().getFirst()) {
            case "NewClaim" -> {
                LivenessManager.NewClaimEventResponse claim = LivenessManager.getNewClaimEventFromLog(log.asWeb3jLog());
                HeaderState headerState = new HeaderState(claim.chainId.intValue(), claim.height.longValue(), claim.hashVal);
                LivenessChallengeState state = new LivenessChallengeState(new ClaimInfo(1, ChallengeType.REPORTBLOCKLIVENESS, headerState));

                updateLivenessClaim(ByteUtil.toHexString(headerState.claimHash()), state);
                updateAllClaimIndex(state.getClaimId(), true);
            }
            case "NewInvolved" -> {
                LivenessManager.NewInvolvedEventResponse involved = LivenessManager.getNewInvolvedEventFromLog(log.asWeb3jLog());
                LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(involved.claimId));
                // default idle
                state.updatePlayer(involved.addr, involved.identity.intValue(), 1);
                updateLivenessClaim(state.getClaimId(), state);
            }
            case "ChallengeRequested" -> {
                LivenessManager.ChallengeRequestedEventResponse request = LivenessManager.getChallengeRequestedEventFromLog(log.asWeb3jLog());
                LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(request.claimId));
                Game game = new Game(null, state.getClaimInfo(), REQUEST);
                state.updatePlayer(request.challenger, 2);
                state.updatePlayer(request.defender, 2);
                updateLivenessChallenge(request.challengeId.longValue(), game);
                updateLivenessClaim(state.getClaimId(), state);
                updateLivenessWhoIsIn(request.challengeId.longValue(), request.challenger, request.defender);
            }
            case "ChallengeSettled" -> {
                LivenessManager.ChallengeSettledEventResponse settle = LivenessManager.getChallengeSettledEventFromLog(log.asWeb3jLog());
                Game game = tryReadLivenessChallenge(settle.challengeId.longValue());
                game.setStatus(ChallengeStatus.ENDED);
                LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(game.getInfo().getClaim().claimHash()));
                state.updatePlayer(settle.winner, 1);
                state.updatePlayer(settle.loser, 3);
                updateLivenessChallenge(settle.challengeId.longValue(), game);
                updateLivenessClaim(state.getClaimId(), state);
                removeLivenessWhoIsIn(settle.loser, settle.winner);
            }
            case "ClaimSettled" -> {
                LivenessManager.ClaimSettledEventResponse claimSettle = LivenessManager.getClaimSettledEventFromLog(log.asWeb3jLog());
                LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(claimSettle.claimId));
                state.getClaimInfo().setClaimStatus(claimSettle.finnalState.intValue());
                updateLivenessClaim(state.getClaimId(), state);
                updateAllClaimIndex(state.getClaimId(), false);
            }
            default -> throw new RuntimeException("Unknown log type: "+log.getTopics().getFirst());

        }
    }


    @Override
    public Integer claimState(byte[] claim) {
        LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(claim));
        if (state == null) {
            return null;
        }
        return state.getClaimInfo().getClaimStatus();
    }


    @Override
    public ArrayList<HeaderState> aliveClaim(String address) {
        ArrayList<HeaderState> aliveClaims = new ArrayList<>();
        RLPMap<String, Boolean> allClaims = tryReadAllClaimIndex();
        for (String claimId: allClaims.keySet()) {
            if (allClaims.get(claimId)) {
                LivenessChallengeState state = tryReadLivenessClaim(claimId);
                if (!state.existInAgainst(address) && !state.existInAdvocate(address)) {
                    aliveClaims.add(state.getClaimInfo().getClaim());
                }
            }
        }
        return aliveClaims;
    }

    @Override
    public Boolean isClaimed(String address, byte[] hash) {
        LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(hash));
        if (state == null) {
            return false;
        }
        return state.existInAdvocate(address) || state.existInAgainst(address);
    }

    @Override
    public ChallengeStatus challengeState(Long challengeIndex) {
        return tryReadLivenessChallenge(challengeIndex).getStatus();
    }

    @Override
    public Long onGoingChallenge(String address) {
        return tryReadLivenessWhoIsIn().get(address);
    }

    @Override
    public String findAlivePlayer(byte[] claimId, Role role) {
        LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(claimId));
        if (state == null) {
            return null;
        }
        switch (role) {
            case CHALLENGER -> {
                // search supervisors who have chosen a faction first, then Ivy nodes.
                return null;
            }
            case DEFENDER -> {
                for(String address: state.getAgainst().keySet()) {
                    if (state.getAgainst().get(address) == 1) {
                        return address;
                    }
                }
                return null;
            }
            default -> throw new RuntimeException("Unknown role");
        }
    }

    @Override
    public Boolean warOvertime(Long warHeight) {
        // 7-days
        return null;
    }

    @Override
    public Boolean warIsEnd(byte[] claimId) {
        LivenessChallengeState state = tryReadLivenessClaim(ByteUtil.toHexString(claimId));
        if (state == null) {
            return true;
        }
        Integer status = state.getClaimInfo().getClaimStatus();
        return status == 2 || status == 3 || status == 4;
    }

    private LivenessChallengeState tryReadLivenessClaim(String digest) {
        Object key = PREFIX1+digest;
        LivenessChallengeState state = (LivenessChallengeState) readStateFromCache(key);
        if (state == null) {
            state = (LivenessChallengeState) readStateFromDb(SafetyWarState.class, key);
            writeStateToCache(key, state);
        }
        return state;
    }

    private void updateLivenessClaim(String digest, LivenessChallengeState state) {
        Object key = PREFIX1+digest;
        writeStateToCache(key, state);
        writeStateToCache(key, state);
    }

    private RLPMap<String, Boolean> tryReadAllClaimIndex() {
        Object key = PREFIX2;
        RLPMap<String, Boolean> map = (RLPMap<String, Boolean>) readStateFromCache(key);
        if (map == null) {
            map = (RLPMap<String, Boolean>) readStateFromDb(RLPMap.class, key);
            if (map == null)
                map = new RLPMap<>();
            writeStateToCache(key, map);
        }
        return map;
    }

    private void updateAllClaimIndex(String claimId, Boolean alive ) {
        Object key = PREFIX2;
        RLPMap<String, Boolean> map = tryReadAllClaimIndex();
        map.put(claimId, alive);
        writeStateToCache(key, map);
        writeStateToDb(key, map);
    }

    private Game tryReadLivenessChallenge(Long challengeIndex){
        Object key = PREFIX3+challengeIndex;
        Game state = (Game) readStateFromCache(key);
        if (state == null) {
            state = (Game) readStateFromDb(Game.class, key);
            writeStateToCache(key, state);
        }
        return state;
    }
    private void updateLivenessChallenge(Long challengeIndex, Game state) {
        Object key = PREFIX3+challengeIndex;
        writeStateToCache(key, state);
        writeStateToCache(key, state);
    }

    private RLPMap<String, Long> tryReadLivenessWhoIsIn(){
        Object key = PREFIX4;
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
    private void updateLivenessWhoIsIn(Long challengeIndex, String address1, String address2) {
        Object key = PREFIX4;
        RLPMap<String, Long> hashMap = tryReadLivenessWhoIsIn();
        hashMap.put(address1, challengeIndex);
        hashMap.put(address2, challengeIndex);
        writeStateToCache(key, hashMap);
        writeStateToDb(key, hashMap);
    }

    private void removeLivenessWhoIsIn(String address1, String address2) {
        Object key = PREFIX4;
        RLPMap<String, Long> hashMap = tryReadLivenessWhoIsIn();
        hashMap.remove(address1);
        hashMap.remove(address2);
        writeStateToCache(key, hashMap);
        writeStateToDb(key, hashMap);
    }
}
