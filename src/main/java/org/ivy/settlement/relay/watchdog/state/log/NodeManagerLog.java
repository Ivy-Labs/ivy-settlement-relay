package org.ivy.settlement.relay.watchdog.state.log;


import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.state.BaseProcessor;
import org.web3j.protocol.core.methods.response.EthLog;

public class NodeManagerLog extends BaseProcessor {

    /**
     * @dev Provides information about the current execution context, including the
     * sender of the transaction and its data. While these are generally available
     * via msg.sender and msg.data, they should not be accessed in such a direct
     * manner, since when dealing with meta-transactions the account sending and
     * paying for execution may not be the actual sender (as far as an application
     * is concerned).
     *
     * This contract is only required for intermediate, library-like contracts.
     */

    public NodeManagerLog(LRUCache cache, RocksDbSource dbSource) {
        super(cache, dbSource);
        this.contract = contract;
    }
    @Override
    public void processLog(EthLog log) {
        switch (log.getTopics().getFirst()) {
            case "NodeAdd" ->{

            }
            case "NodeRemove" -> {

            }
            case "Stake" ->{

            }
            case "Unstake" -> {

            }
            case "NodeStateUpdate" -> {

            }
            case "VoterInfoUpdate" -> {

            }
            default -> {throw new RuntimeException("Unknown log type: "+log.getTopics().getFirst());}
        }
    }
}
