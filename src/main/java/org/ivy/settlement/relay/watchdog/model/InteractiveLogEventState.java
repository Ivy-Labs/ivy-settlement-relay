package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.ethereum.model.constants.EthLogActionEnum;
import org.web3j.abi.datatypes.Function;

import static org.ivy.settlement.ethereum.model.constants.EthLogActionEnum.INTERACTIVE;

/**
 * description:
 * <p>
 * @Author carrot
 */
public abstract class InteractiveLogEventState extends LogEventStateData {

    public InteractiveLogEventState(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    public abstract Function getFunction();

    public void reEncode() {
        this.rlpEncoded = rlpEncoded();
    }

    @Override
    public EthLogActionEnum getLogActionType() {
        return INTERACTIVE;
    }
}
