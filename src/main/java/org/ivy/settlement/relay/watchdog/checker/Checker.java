package org.ivy.settlement.relay.watchdog.checker;

import org.web3j.abi.datatypes.Function;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.EthLog;

import java.util.ArrayList;

public interface Checker {

    ArrayList<EthLog> readBlockLogs(Long height);

    // for source chain
    Boolean canChallenge(Long height);

    Function onChallenge(BaseEventResponse event);
}
