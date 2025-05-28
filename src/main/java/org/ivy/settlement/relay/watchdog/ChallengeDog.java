package org.ivy.settlement.relay.watchdog;

import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.relay.watchdog.checker.Checker;
import org.ivy.settlement.relay.watchdog.contract.ChallengeManager;
import org.ivy.settlement.relay.watchdog.state.CombatState;
import org.web3j.abi.datatypes.Function;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.tx.FastRawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.DefaultGasProvider;
import org.web3j.tx.response.NoOpProcessor;
import org.web3j.tx.response.TransactionReceiptProcessor;

import java.util.ArrayDeque;
import java.util.Date;

public class ChallengeDog extends Dog {

    CombatState stateHandler;

    ArrayDeque<Challenge> onGoingChallenge;
    ArrayDeque<Challenge> settling;

    String walletAddress;
    Web3j web3j;
    Credentials credential;
    ContractGasProvider gasProvider = new DefaultGasProvider();
    Checker checker;
    ChallengeManager challengeManager;

    public ChallengeDog(){
        TransactionReceiptProcessor txp = new NoOpProcessor(web3j);
        TransactionManager txm = new FastRawTransactionManager(web3j, credential, txp);
        challengeManager = ChallengeManager.load("contractAddress", web3j, txm, gasProvider);
    }

    @Override
    protected void processMsg() {

        advanceProgress();

        settle();
    }

    @Override
    protected void loadPendingMsg() {
        while (true) {
            DogMsg msg = pendingMsg.poll();
            if (msg == null) {
                return;
            }
            Challenge ch = new Challenge(ByteUtil.byteArrayToLong(msg.getMsgContent()));
            onGoingChallenge.add(ch);
        }

    }

    private void settle() {
        while (true) {
            Challenge ch = settling.peek();
            if (ch == null) {
                return;
            }
            if (stateHandler.isSettle(ch.getIndex())) {
                settling.pop();
                continue;
            }
            var state = stateHandler.getChallengeState(ch.getIndex());
            var now = new Date().getTime();
            if (ch.tryAgain()) {
                continue;
            }


        }
    }
    private void advanceProgress() {
        while (true) {
            long now = new Date().getTime();
            Challenge ch = onGoingChallenge.peek();
            if (ch == null) {
                return;
            }

            var state = stateHandler.getChallengeState(ch.getIndex());

            // overdue settlement
            if (state.overtime()) {
                ch.resetTExecTime();
                settling.push(ch);
                onGoingChallenge.pop();
                continue;
            }

            // TODO：The rest time
            if (state.getReactor().equals(walletAddress) && ch.tryAgain()) {
                Function function = checker.onChallenge(state.getLatestEvent());
                if (function == null) {
                    return;
                }
//                RawTransaction rawTransaction = RawTransaction.createTransaction(chainId, nonce, gasLimit, to, value, data, maxPriorityFeePerGas, maxFeePerGas);
                sendTransaction(function);

                ch.setLastExecTime(new Date().getTime());

            }
        }
    }

    private void sendTransaction(Function function) {

    }


}
