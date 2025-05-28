package org.ivy.settlement.relay.watchdog.state.log;

import org.ivy.settlement.infrastructure.datasource.model.Keyable;
import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.model.Offset;
import org.ivy.settlement.relay.watchdog.state.BaseProcessor;
import org.web3j.protocol.core.methods.response.EthLog;

import java.util.ArrayList;
import java.util.Map;

public class StateBuilder {
    Map<String, BaseProcessor> logProcessors;

    RocksDbSource dbSource;
    Offset offset;
    String PREFIX = "STATE_BUILDER";

    public StateBuilder() {

    }

    private ArrayList<EthLog> readEthBlock(Long height) {
        return null;
    }

    public void run() {
        ArrayList<EthLog> logs = readEthBlock(offset.getValue() + 1);
        if (logs == null) {
            return;
        }
        for (EthLog log: logs) {
            BaseProcessor p = logProcessors.get(log.getAddress());
            if (p!=null) {
                p.deliverLog(log);
            }
        }
        offset.add(1L);
        dbSource.put(Keyable.ofDefault(PREFIX.getBytes()), offset);
    }
}
