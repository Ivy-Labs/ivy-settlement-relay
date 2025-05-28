package org.ivy.settlement.relay.watchdog;

import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.ivy.settlement.relay.watchdog.model.*;
import org.ivy.settlement.relay.watchdog.model.*;
import org.ivy.settlement.relay.watchdog.state.RelayerState;

import java.util.HashMap;
import java.util.Map;

public class RelayerDog {

    RelayerState stateHandler;

    Map<Integer, ChainTxSummary> chainTxSummaryMap;

    LivenessDog livenessDog;
    protected CheckerManager checkerManager;

    public RelayerDog() {
        chainTxSummaryMap = new HashMap<>();

    }

    public void watchRelayerManager() {
        while (true) {
            try {
                for (var chainId: chainTxSummaryMap.keySet()) {
                    var chainTxSummary = chainTxSummaryMap.get(chainId);
                    boolean isCommit = stateHandler.isSrcPacketCommit(chainId, chainTxSummaryMap.get(chainId).getNextCommitHash());
                    if (isCommit) {
                        var next = checkerManager.getSrcPacket(chainId, chainTxSummary.getNextCommitSeq() + 1);
                        chainTxSummary.setNextCommitSeq(next.getSeq());
                        chainTxSummary.setNextCommitHash(next.getPacketHash());
                    } else if (chainTxSummary.isLossLiveness() && !chainTxSummary.getUrge()) {
                        // lose liveness
                        Packet next = checkerManager.getSrcPacket(chainId, chainTxSummary.getNextCommitSeq());
                        Issue issue = new Issue(null, ChallengeType.REPORTBLOCKLIVENESS, new Dispute(next.getBridgeHeight(), new HeaderState(chainId, next.getHeight(), null)));
                        livenessDog.notifyLossLiveness(issue);
                        chainTxSummary.setUrge(true);
                    }
                    // waiting for tx processing successfully, then go forward
                }
                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("bridgeDog exception");
            }
        }
    }

}
