package org.ivy.settlement.relay.watchdog;

import org.ivy.settlement.ethereum.model.constants.ChainType;
import org.ivy.settlement.infrastructure.datasource.model.Keyable;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.ivy.settlement.relay.watchdog.model.*;
import org.ivy.settlement.relay.watchdog.state.IvyChainState;

import java.util.*;

public class IvyNetworkDog extends Dog {
    public final String DB_PREFIX= "IvyNetworkDog";
    IvyChainState ivyChainState;
    Map<Integer, ChainSummary> chainSummaryMap;

    protected ChainType chainType;
    protected CheckerManager checkerManager;


    Integer numDispute;

    Offset watchedIvyHeight;


    Send safetySender;
    Send livenessSender;

//    private void init(){
//        try {
//            // recover storage
////            this.offset = (Offset) this.dbSource.get(Offset.class, Keyable.ofDefault((DB_PREFIX+chainType+"offset").getBytes()));
////            if (this.offset == null) {
////                // read configs
////                long blockOffset = Long.parseLong(WatchDogConfig.getConfigByKey("bridgeDog."+this.chainType.getName()+".blockOffset"));
////                long logOffset = 0;
////                this.offset = new Offset(blockOffset, logOffset);
////            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            throw e;
//        }
//    }

    @Override
    protected void processMsg() {
        IvyBlock block = ivyChainState.getIvyBlock(watchedIvyHeight.getValue() + 1);
        if (block != null && !block.isEmpty() ) {
            safetyCheck(block);
            watchedIvyHeight.add(1L);
        }
        livenessCheck();
        dbSource.put(Keyable.ofDefault("OFFSET".getBytes()), watchedIvyHeight);
    }

    @Override
    protected void loadPendingMsg() {
        return;
    }
    @Override
    public void run() {
        processMsg();
    }

    private void livenessCheck() {
        for (Integer chainId: chainSummaryMap.keySet()) {
            var chain = chainSummaryMap.get(chainId);
            if (chain.isLossLiveness() && !chain.getUrge()) {
                // lose liveness
                var next = checkerManager.getNextHeaderState(chainId, chain.getLastCommitHeight());
                var issue = new Issue(next.getHeight(), ChallengeType.REPORTBLOCKLIVENESS, null);
                livenessSender.sendMsg(new DogMsg(issue.valueBytes()));
                chain.setUrge(true);
            }
        }
    }



    private void safetyCheck(IvyBlock block) {
        // merkle root invalid, reportFaultyBlock
        if (!Arrays.equals(block.getRoot(), block.getMerkleRoot())) {
            numDispute++;
            Issue issue = new Issue(block.getHeight(), ChallengeType.REPORTFAULTY, new Dispute(block.getHeight(), null));
            safetySender.sendMsg(new DogMsg(issue.valueBytes()));
            return;
        }

        // classify by chainId
        var unChecked = new HashMap<Integer, List<HeaderState>>();
        for (var state : block.getHeaderStates()) {
            unChecked.computeIfAbsent(state.getChainId(), k -> new ArrayList<>());
            unChecked.get(state.getChainId()).add(state);
        }
        var ordered = new ArrayList<HeaderState>();
        // sort by height
        for (var chainId : unChecked.keySet()) {
            var unOrdered = unChecked.get(chainId);
            unOrdered.sort(Comparator.naturalOrder());
            ordered.addAll(unOrdered);
        }
        // check the block
        for (var checking : ordered) {
            var chain = chainSummaryMap.get(checking.getChainId());
            var lastCommit = chain.getLastCommitHeight();
            if (lastCommit >= checking.getHeight()) {
                // disorder
                numDispute++;
                var dispute = new Dispute(null,null);
                var issue = new Issue(block.getHeight(), ChallengeType.REPORTORDER, dispute);
                safetySender.sendMsg(new DogMsg(issue.valueBytes()));
                break;
            }
            var next = checkerManager.getHeaderState(checking.getChainId(), checking.getHeight());

            if (next == null) {
                // inexistent block
                throw new RuntimeException("empty checking height");
            }

            if (next.getPreHeight() < lastCommit) {
                throw new RuntimeException("Should not reach here");
            } else if (next.getPreHeight() > lastCommit) {
                // lacked block
                numDispute++;
                Dispute dispute = new Dispute(null,null);
                Issue issue = new Issue(block.getHeight(), ChallengeType.REPORTLACK, dispute);
                safetySender.sendMsg(new DogMsg(issue.valueBytes()));
                break;
            } else if (!Arrays.equals(next.getRoot(), checking.getState())) {
                // invalid block
                numDispute++;
                Dispute dispute = new Dispute(null,null);
                Issue issue = new Issue(block.getHeight(), ChallengeType.REPORTFAKE, dispute);
                safetySender.sendMsg(new DogMsg(issue.valueBytes()));
                break;
            }  else {
                // verify success, record timestamp
                chain.setLastCommitHeight(checking.getHeight());
                chain.setLastHeartbeat(new Date().getTime());
                chain.setUrge(false);
            }
        }
    }
}
