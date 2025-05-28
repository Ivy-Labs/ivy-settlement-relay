package org.ivy.settlement.relay.watchdog.state.log;

import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.contract.IIvyChainManager;
import org.ivy.settlement.relay.watchdog.model.IvyBlock;
import org.ivy.settlement.relay.watchdog.state.BaseProcessor;
import org.ivy.settlement.relay.watchdog.state.IvyChainState;
import org.web3j.protocol.core.methods.response.EthLog;

public class IvyNetworkLog extends BaseProcessor implements IvyChainState {

    public IvyNetworkLog(LRUCache cache, RocksDbSource dbSource) {
        super(cache, dbSource);
        PREFIX = "IvyChain";
    }

    @Override
    public void processLog(EthLog log) {
        processIvyBlockLog(log);
    }

    private void processIvyBlockLog(EthLog log) {

        switch (log.getTopics().getFirst()) {
            case "UploadSuccess":
                IIvyChainManager.UploadSuccessEventResponse upload = IIvyChainManager.getUploadSuccessEventFromLog(log.asWeb3jLog());
                // TODO: Check consistency for duplicate writes?
                IvyBlock block = new IvyBlock(upload);
                // write db
                writeStateToDb(block.getHeight(),block);
                // write cache
                writeStateToCache(block.getHeight(), block);
            default: throw new RuntimeException("Unknown log type: "+log.getTopics().getFirst());
        }

    }

    @Override
    public IvyBlock getIvyBlock(Long height) {
        IvyBlock block =(IvyBlock) readStateFromCache(height);
        if (block == null) {
            // 读数据库
             block = (IvyBlock) readStateFromDb(IvyBlock.class, height);
        }
        return block;
    }

    @Override
    public byte[] srcBlockInIvy(Long ivyHeight, Integer chainId, Long height) {
        IvyBlock block = (IvyBlock) readStateFromCache(height);
        if (block == null) {
            // 读数据库
            block = (IvyBlock) readStateFromDb(IvyBlock.class, height);
        }
        return block.getHeaderState(chainId, height).getState();
    }
}
