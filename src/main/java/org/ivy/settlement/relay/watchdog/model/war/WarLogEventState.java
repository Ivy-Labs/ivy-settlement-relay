package org.ivy.settlement.relay.watchdog.model.war;

import org.ivy.settlement.ethereum.model.constants.EthLogEventEnum;
import org.ivy.settlement.relay.watchdog.model.InteractiveLogEventState;
import org.web3j.abi.datatypes.Function;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class WarLogEventState extends InteractiveLogEventState {
    public WarLogEventState(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    @Override
    public Function getFunction() {
        return null;
    }

    @Override
    public EthLogEventEnum getLogEventType() {
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
