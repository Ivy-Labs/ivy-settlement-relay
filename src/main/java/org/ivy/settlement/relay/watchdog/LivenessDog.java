package org.ivy.settlement.relay.watchdog;


import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.codec.borsh.Borsh;
import org.ivy.settlement.infrastructure.merkle.MerkleProof;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.ivy.settlement.relay.watchdog.constants.Role;
import org.ivy.settlement.relay.watchdog.constants.SettleMode;
import org.ivy.settlement.relay.watchdog.contract.LivenessManager;
import org.ivy.settlement.relay.watchdog.contract.RelayerManager;
import org.ivy.settlement.relay.watchdog.model.*;
import org.ivy.settlement.relay.watchdog.state.IvyChainState;
import org.ivy.settlement.relay.watchdog.state.LivenessState;
import org.ivy.settlement.relay.watchdog.model.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class LivenessDog extends Dog {

    IvyChainState ivyChainState;

    LivenessState livenessState;

    ArrayDeque<ClaimAction> pendingClaim = new ArrayDeque<>();

    ArrayDeque<ClaimAction> pendingTxLiveness = new ArrayDeque<>();

    ArrayDeque<ClaimAction> pendingClaimAgainst = new ArrayDeque<>();

    ArrayDeque<WarAction> pendingWar = new ArrayDeque<>();

    ArrayDeque<SettleAction> pendingSettle = new ArrayDeque<>();
    Long retryPeriod;

    Web3j web3j;
    String walletAddress;

    Credentials credential;

    DefaultGasProvider gasProvider;

    CheckerManager checkerManager;

    Send challengeSender;

//    private void init() {
//        currentOffset = readFinishedFlag();
//        readDispute();
//    }

    @Override
    protected void processMsg() {
//        onTxLiveness();
        // claim
        onClaim();
        // claim as againster
        claimAgainst();
        // challenge
        onChallenge();
        // settle
        onSettle();
    }

    @Override
    protected void loadPendingMsg() {
        while (true) {
            DogMsg msg = pendingMsg.poll();
            if (msg == null) {
                return;
            }
            Issue issue = new Issue(msg.msgContent);
            switch (issue.getChallengeType()) {
                case REPORTBLOCKLIVENESS:
                    pendingClaim.add(new ClaimAction(issue));
                case REPORTTXLIVENESS:
                    pendingTxLiveness.add(new ClaimAction(issue));
                    break;
                default:
                    throw new RuntimeException("Unknown challenge type");
            }
        }

    }

    public void notifyLossLiveness(Issue issue) {
        try {
//            urging.put(issue);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void onTxLiveness() {
        while (true) {
            ClaimAction action = pendingTxLiveness.peek();
            if (action == null) {
                return;
            }

            // if success


            if (new Date().getTime() - action.getLastExecTime() <= retryPeriod) {
                break;
            }

            String tx = null;
            RelayerManager livenessManager = relayerInstance();
            BigInteger depositVal = BigInteger.ZERO;
            BigInteger gasLimit = BigInteger.ZERO;
            try {
                tx = livenessManager.penalityRelayer(BigInteger.valueOf(action.getIssue().getDisputeSrcChainId()), gasLimit, BigInteger.valueOf(action.getIssue().getDisputeHeight()), new byte[] {},"", depositVal).send().getTransactionHash();
            } catch (Exception ignored) {

            }
            if (tx != null)
                action.setLastExecTime(new Date().getTime());

        }
    }

    private void onClaim() {
        while (true) {
            ClaimAction action = pendingClaim.peek();
            if (action == null) {
                return;
            }
            // whether claimed
            if (livenessState.isClaimed(walletAddress, action.getIssue().getClaimHash())) {
                ClaimAction fp = pendingClaim.pop();
                pendingWar.add(new WarAction(Role.CHALLENGER, fp.getIssue()));
                continue;
            }
            // whethe solved
            Integer state = livenessState.claimState(action.getIssue().getClaimHash());
            if (state != null && (state == 2 || state == 3 || state == 4)) {
                pendingClaim.pop();
                continue;
            }
            // not claim yet, or invalid claim
            if (new Date().getTime() - action.getLastExecTime() <= retryPeriod) {
                break;
            }
            String tx = null;
            LivenessManager livenessManager = livenessInstance();
            BigInteger depositVal = BigInteger.ZERO;
            try {
                tx = livenessManager.claim(Arrays.asList(BigInteger.valueOf(action.getIssue().getDisputeSrcChainId())), Arrays.asList(BigInteger.valueOf(action.getIssue().getDisputeSrcHeight())), Arrays.asList(action.getIssue().getDisputeSrcHash()), depositVal).send().getTransactionHash();
            } catch (Exception ignored) {
            }
            if (tx != null)
                action.setLastExecTime(new Date().getTime());

        }
    }

    private void claimAgainst() {
        while (true) {
            if (pendingClaimAgainst.isEmpty()) {
                // not claim as againster yet and still alive
                ArrayList<HeaderState> aliveClaim = livenessState.aliveClaim(walletAddress);
                for (HeaderState h : aliveClaim) {
                    pendingClaimAgainst.add(new ClaimAction(new Issue(null, ChallengeType.REPORTBLOCKLIVENESS, new Dispute(null, new HeaderState(h.getChainId(), h.getHeight(), h.getState())))));
                }
            }
            ClaimAction action = pendingClaimAgainst.peek();
            if (action == null) {
                return;
            }
            // check the existence of a block on the source chain, claim as an opponent if it does not exist.
            if (action.getIssue().getDisputeSrcHash() == checkerManager.getSrcStateHash(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight())) {
                // if exist, end
                if (action.getIssue().getDisputeSrcHash() == ivyChainState.srcBlockInIvy(action.getIssue().getDisputeHeight(), action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight())) {
                    ClaimAction fp = pendingClaimAgainst.pop();
                    pendingSettle.add(new SettleAction(SettleMode.PEACEFULEND, fp.getIssue()));
                }
                ClaimAction fp = pendingClaimAgainst.pop();
                continue;
            }

            if (livenessState.isClaimed(walletAddress, action.getIssue().getClaimHash())) {
                ClaimAction fp = pendingClaimAgainst.pop();
                pendingWar.add(new WarAction(Role.CHALLENGER, fp.getIssue()));
                continue;
            }

            if (new Date().getTime() - action.getLastExecTime() <= retryPeriod) {
                break;
            }
            String tx = null;
            LivenessManager livenessManager = livenessInstance();
            BigInteger depositVal = BigInteger.ZERO;
            try {
                tx = livenessManager.againstClaims(Arrays.asList(action.getIssue().getClaimHash()), depositVal).send().getTransactionHash();
            } catch (Exception ignored) {}
            if (tx != null)
                action.setLastExecTime(new Date().getTime());
            }
    }

    private void sendChallenge(Long challengeIndex) {

    }
    private void onChallenge(){
        while (true) {
            WarAction action = pendingWar.peek();
            if (action == null) {
                return;
            }

            if (action.getOnGoingChallenge() != null) {
                if (livenessState.challengeState(action.getOnGoingChallenge()).equals(ChallengeStatus.ENDED)) {
                    // current challenge is over while war still open
                    action.setOnGoingChallenge(null);
                    action.resetTExecTime();
                } else {
                    // skip current challenge if not end yet
                    continue;
                }
            }

            Long challenge = livenessState.onGoingChallenge(walletAddress);
            if (challenge != null) {
                sendChallenge(challenge);
                challengeSender.sendMsg(new DogMsg(ByteUtil.longToBytes(challenge)));
                action.setOnGoingChallenge(challenge);
                continue;
            }

            //  cannot be challenged
            if (!checkerManager.canChallenge(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight())) {
                continue;
            }

            String against = livenessState.findAlivePlayer(action.getIssue().getClaimHash(), Role.CHALLENGER);
            if (against == null && livenessState.warOvertime(action.getIssue().getWarHeight())) {
                // cannot find vaild challenger, waiting...
                action.resetTExecTime();
                pendingSettle.add(new SettleAction(SettleMode.END, action.getIssue()));
                pendingWar.pop();
                continue;
            }

            if (new Date().getTime() - action.getLastExecTime() <= retryPeriod ) {
                continue;
            }

            String tx = null;
            LivenessManager livenessManager = livenessInstance();
            IvyBlock bb = ivyChainState.getIvyBlock(action.getIssue().getWarHeight());
            MerkleProof proof = bb.getMerkleProof(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight());
            LivenessManager.UploadedHeader header = new LivenessManager.UploadedHeader(BigInteger.valueOf(bb.getHeight()), Borsh.serialize(proof));
            try {
                tx = livenessManager.requestChallenge(action.getIssue().getClaimHash(), against, header).send().getTransactionHash();
            } catch (Exception e) {
                tx = null;
            }
            if (tx != null) {
                action.setLastExecTime(new Date().getTime());
            }

        }
    }

    private void onSettle(){
        while (true) {
            SettleAction action = pendingSettle.peek();
            if (action == null) {
                return;
            }
            if (livenessState.warIsEnd(action.getIssue().getClaimHash())) {
                finishMsg(action.getId());
                pendingSettle.pop();
                continue;
            }

            if (new Date().getTime() - action.getLastExecTime() <= retryPeriod ) {
                continue;
            }
            LivenessManager livenessManager = livenessInstance();
            String tx = null;

            switch (action.getMode()) {
                case END -> {
                    try {
                        tx = livenessManager.settleClaims(Arrays.asList(action.getIssue().getClaimHash())).send().getTransactionHash();
                    } catch (Exception ignored) {}
                }
                case PEACEFULEND -> {
                    try{
                        LivenessManager.UploadedHeader uploadedHeader = new LivenessManager.UploadedHeader((BigInteger) null,null);
                        tx = livenessManager.closeClaims(Arrays.asList(uploadedHeader),Arrays.asList(action.getIssue().getClaimHash())).send().getTransactionHash();
                    } catch (Exception ignored) {}
                }
                default -> throw new RuntimeException("Unknown settle mode");
            }
            if (tx != null) {
                action.setLastExecTime(new Date().getTime());
            }

        }
    }
    private LivenessManager livenessInstance() {
        TransactionReceiptProcessor txp = new NoOpProcessor(web3j);
        TransactionManager txm = new FastRawTransactionManager(web3j, credential, txp);
        return LivenessManager.load("contractAddress", web3j, txm, gasProvider);
    }

    private RelayerManager relayerInstance() {
        TransactionReceiptProcessor txp = new NoOpProcessor(web3j);
        TransactionManager txm = new FastRawTransactionManager(web3j, credential, txp);
        return RelayerManager.load("contractAddress", web3j, txm, gasProvider);
    }

}
