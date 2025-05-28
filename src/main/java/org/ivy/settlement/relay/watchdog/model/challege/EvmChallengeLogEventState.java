package org.ivy.settlement.relay.watchdog.model.challege;

import org.ivy.settlement.ethereum.model.constants.EthLogEventEnum;
import org.ivy.settlement.relay.watchdog.model.InteractiveLogEventState;
import org.web3j.abi.datatypes.Function;

import static org.ivy.settlement.ethereum.model.constants.EthLogEventEnum.EVM_CHALLENGE_EVENT;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class EvmChallengeLogEventState extends InteractiveLogEventState {

    public long step;

    public long next;


    public int chainId;


    public int getChainId() {
        return chainId;
    }

    public EvmChallengeLogEventState(byte[] rlpEncoded) {
        super(rlpEncoded);
    }


    @Override
    public EthLogEventEnum getLogEventType() {
        return EVM_CHALLENGE_EVENT;
    }

    @Override
    public Function getFunction() {
        return null;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
