package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.ethereum.model.constants.EthLogActionEnum;
import org.ivy.settlement.ethereum.model.constants.EthLogEventEnum;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.rlp.RLPModel;
import org.web3j.abi.datatypes.Function;

/**
 * description:
 * <p>
 * @Author carrot
 */
public abstract class LogEventStateData extends Persistable {

    public LogEventStateData(byte[] rlpEncoded) {
        super(rlpEncoded);
    }


    public abstract EthLogActionEnum getLogActionType();

    public abstract EthLogEventEnum getLogEventType();


    public void markDirty() {
        this.rlpEncoded = null;
    }
}
