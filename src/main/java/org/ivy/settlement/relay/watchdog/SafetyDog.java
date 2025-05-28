package org.ivy.settlement.relay.watchdog;

import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.codec.borsh.Borsh;
import org.ivy.settlement.infrastructure.merkle.MerkleProof;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.Role;
import org.ivy.settlement.relay.watchdog.contract.SafetyManager;
import org.ivy.settlement.relay.watchdog.model.ClaimAction;
import org.ivy.settlement.relay.watchdog.model.Issue;
import org.ivy.settlement.relay.watchdog.model.IvyBlock;
import org.ivy.settlement.relay.watchdog.model.WarAction;
import org.ivy.settlement.relay.watchdog.state.IvyChainState;
import org.ivy.settlement.relay.watchdog.state.SafetyState;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.math.BigInteger;
import java.util.ArrayDeque;
import java.util.Date;

public class SafetyDog extends Dog {

    SafetyState safetyState;

    IvyChainState ivyChainState;

    // task cache
    ArrayDeque<ClaimAction> pendingClaim;
    ArrayDeque<WarAction> pendingWar;
    ArrayDeque<ClaimAction> pendingReport;
    ArrayDeque<WarAction> pendingSettle;
    CheckerManager checkers;
    Web3j web3j;
    Credentials credential;
    ContractGasProvider gasProvider = new DefaultGasProvider();
    String walletAddress;
    Role role;

    Send challengeSender;

    public SafetyDog() {
    }


    @Override
    protected void processMsg() {


        // report
        onReport();
        // claim
        onClaim();
        // clallenge
        onChallenge();
        // settle
        onSettle();
    }

    @Override
    protected void loadPendingMsg() {
        while (true) {
            var msg = pendingMsg.poll();
            if (msg == null) {
                return;
            }
            var issue = new Issue(msg.getMsgContent());
            var challengeType = issue.getChallengeType();
            switch (challengeType) {
                case ChallengeType.REPORTFAKE, ChallengeType.REPORTLACK -> pendingClaim.add(new ClaimAction(issue));
                case ChallengeType.REPORTFAULTY, ChallengeType.REPORTORDER -> pendingReport.add(new ClaimAction(issue));
                default -> throw new RuntimeException("Unknown challenge type");
            }
        }
    }


    public void onReport() {
        while (true) {
            var action = pendingReport.peek();
            if (action == null) {
                return;
            }
            if (safetyState.isReport(action.getIssue().getWarHeight(), walletAddress)) {
                pendingReport.pop();
                continue;
            }
            // execute sequentially, report in parallel for now.
            if (!action.tryAgain()) {
                return;
            }
            SafetyManager safetyManager = safetyInstance();
            String tx = null;
            IvyBlock bb = ivyChainState.getIvyBlock(action.getIssue().getDisputeHeight());
            switch (action.getChallengeType()) {
                case ChallengeType.REPORTFAULTY:
                    SafetyManager.IvyBlock b = new SafetyManager.IvyBlock(BigInteger.valueOf(bb.getHeight()), bb.getData(), bb.getMerkleRoot());
                    try {
                        tx = safetyManager.reportFaultyBlock(BigInteger.valueOf(bb.getHeight()), b).send().getTransactionHash();
                    } catch (Exception ignored) {}
                    break;
                case ChallengeType.REPORTORDER:
                    SafetyManager.UploadedHeader low = new SafetyManager.UploadedHeader( BigInteger.ZERO, new byte[]{});
                    SafetyManager.UploadedHeader high = new SafetyManager.UploadedHeader( BigInteger.ONE, new byte[]{});
                    try {
                        tx = safetyManager.reportUnsortedBlock(BigInteger.valueOf(bb.getHeight()), low, high).send().getTransactionHash();
                    } catch (Exception ignored) {}
                    break;
                default: throw new RuntimeException("Unknown type: "+action.getChallengeType());
            }
            if (tx != null) {
                action.setLastExecTime(new Date().getTime());
            }
            return;
        }
    }






    private void onClaim() {
        while (true) {
            ClaimAction action = pendingClaim.peek();
            if (action == null) {
                return;
            }
            if (safetyState.claimInfo(action.getIssue().getWarHeight(), walletAddress) != null) {
                ClaimAction fp = pendingClaim.pop();
                pendingWar.add(new WarAction(Role.CHALLENGER, fp.getIssue()));
                continue;
            }
            if (!action.tryAgain()) {
                return;
            }
            SafetyManager safetyManager = safetyInstance();
            BigInteger depositValue = BigInteger.ZERO;
            String tx = null;
            IvyBlock bb = ivyChainState.getIvyBlock(action.getIssue().getDisputeHeight());
            switch (action.getChallengeType()){
                case ChallengeType.REPORTFAKE:
                    MerkleProof proof = bb.getMerkleProof(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight());
                    SafetyManager.UploadedHeader header = new SafetyManager.UploadedHeader(BigInteger.valueOf(bb.getHeight()), Borsh.serialize(proof));
                    try {
                        tx = safetyManager.claimFakeBlk(BigInteger.valueOf(bb.getHeight()), header, depositValue).send().getTransactionHash();
                    } catch (Exception ignored) {}

                    break;
                case ChallengeType.REPORTLACK:
                    MerkleProof proofL = bb.getMerkleProof(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight());
                    SafetyManager.UploadedHeader headerL = new SafetyManager.UploadedHeader(BigInteger.valueOf(bb.getHeight()), Borsh.serialize(proofL));
                    SafetyManager.LackedHeader lack = new SafetyManager.LackedHeader(BigInteger.valueOf(bb.getHeight()), new byte[] {}, new byte[] {});
                    try {
                        tx = safetyManager.claimLackedBlk(BigInteger.valueOf(bb.getHeight()), headerL, lack, depositValue).send().getTransactionHash();
                    } catch (Exception ignored) {}

                    break;
                default: throw new RuntimeException("Unknown claim type: "+action.getChallengeType());
            }
            if (tx != null )
                action.setLastExecTime(new Date().getTime());
            return;
        }
    }


    private void onChallenge() {
        while (true) {
            WarAction action = pendingWar.peek();
            if (action == null) {
                return;
            }

            if (action.getOnGoingChallenge() != null) {
                if (safetyState.challengeState(action.getOnGoingChallenge()).equals(ChallengeStatus.ENDED)) {
                    action.setOnGoingChallenge(null);
                    action.resetTExecTime();
                } else {
                    return;
                }
            }

            Long challenge = safetyState.onGoingChallenge(action.getIssue().getWarHeight(), walletAddress);
            if (challenge != null) {
                challengeSender.sendMsg(new DogMsg(ByteUtil.longToBytes(challenge)));
                action.setOnGoingChallenge(challenge);
                continue;
            }

            if (!checkers.canChallenge(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight())) {
                continue;
            }

            String against = safetyState.findAlivePlayer(action.getIssue().getWarHeight(), role);
            // TODO: safetyState.warOvertime(action.getIssue().getWarHeight())
            if (against == null) {
                action.resetTExecTime();
                pendingSettle.add(action);
                pendingWar.pop();
                continue;
            }

            if (!action.tryAgain()) {
                continue;
            }

            IvyBlock bb = ivyChainState.getIvyBlock(action.getIssue().getWarHeight());
            MerkleProof proof = bb.getMerkleProof(action.getIssue().getDisputeSrcChainId(), action.getIssue().getDisputeSrcHeight());
            SafetyManager.UploadedHeader header = new SafetyManager.UploadedHeader(BigInteger.valueOf(bb.getHeight()), Borsh.serialize(proof));
            SafetyManager safetyManager = safetyInstance();
            String tx;
            try {
                tx = safetyManager.requestChallenge(BigInteger.valueOf(bb.getHeight()), against,header).send().getTransactionHash();
            } catch (Exception e) {
                tx = null;
            }
            if (tx != null) {
                action.setLastExecTime(new Date().getTime());
            }

        }
    }

    private void onSettle() {
        while (true) {
            WarAction action = pendingSettle.peek();
            if (action == null) {
                return;
            }
            if (safetyState.warIsEnd(action.getIssue().getWarHeight())) {
                pendingSettle.pop();
                finishMsg(action.getId());
                continue;
            }

            if (!action.tryAgain()) {
                continue;
            }
            SafetyManager safetyManager = safetyInstance();
            String tx;
            try {
                tx = safetyManager.settleWar(BigInteger.valueOf(action.getIssue().getWarHeight())).send().getTransactionHash();
            } catch (Exception e) {
                tx = null;
            }
            if (tx != null) {
                action.setLastExecTime(new Date().getTime());
            }

        }
    }


    private SafetyManager safetyInstance() {
        TransactionReceiptProcessor txp = new NoOpProcessor(web3j);
        TransactionManager txm = new FastRawTransactionManager(web3j, credential, txp);
        SafetyManager safetyManager = SafetyManager.load("contractAddress", web3j, txm, gasProvider );
        return safetyManager;
    }

}
