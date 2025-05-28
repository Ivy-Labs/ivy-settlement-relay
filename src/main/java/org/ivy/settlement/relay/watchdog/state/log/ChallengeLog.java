package org.ivy.settlement.relay.watchdog.state.log;

import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.contract.ChallengeManager;
import org.ivy.settlement.relay.watchdog.state.BaseProcessor;
import org.ivy.settlement.relay.watchdog.state.CombatState;
import org.ivy.settlement.relay.watchdog.state.log.store.ChallengeState;
import org.web3j.protocol.core.methods.response.EthLog;

public class ChallengeLog extends BaseProcessor implements CombatState {

    public ChallengeLog(LRUCache cache, RocksDbSource dbSource) {
        super(cache,dbSource);
        PREFIX = "Challenge";
    }
    @Override
    public void processLog(EthLog log) {
        if (!log.getAddress().equals(contract)) {
            return;
        }

        ChallengeState state;
        switch (log.getTopics().getFirst()) {
            case "Published":
                ChallengeManager.PublishedEventResponse publish = ChallengeManager.getPublishedEventFromLog(log.asWeb3jLog());

                if (publish.challengeSeq.longValue() == 1) {
                    state = new ChallengeState(publish);
                } else {
                    // search for exist challenge in cache
                    state = tryReadChallenge(publish.challengeIndex.longValue());
                }
                if (state.getSeq() + 1 == publish.challengeSeq.longValue()) {
                    state.setReactor(publish.reactor);
                    state.setLatestEvent(publish);
                    state.setSeq(publish.challengeSeq.longValue());
                    state.setDeadline(publish.deadline.longValue());
                }
                // write db and cache

                break;
            case "ChallengeEnded":
                ChallengeManager.ChallengeEndedEventResponse end = ChallengeManager.getChallengeEndedEventFromLog(log.asWeb3jLog());
                state = tryReadChallenge(end.challengeIndex.longValue());
                state.setSettle(true);
                break;
            default:
                throw new RuntimeException("Unknown log");
        }
        writeStateToDb(state.index, state);
        writeStateToCache(state.index, state);
    }

    @Override
    public Boolean isSettle(Long challenge) {
        ChallengeState state = tryReadChallenge(challenge);
        return state.getSettle();
    }

    @Override
    public ChallengeState getChallengeState(Long index) {
        return tryReadChallenge(index);
    }


    private ChallengeState tryReadChallenge(Long index) {
        ChallengeState state = (ChallengeState) readStateFromCache(index);
        if (state == null) {
            state = (ChallengeState) readStateFromDb(ChallengeState.class, index);
        }
        return state;
    }
}
