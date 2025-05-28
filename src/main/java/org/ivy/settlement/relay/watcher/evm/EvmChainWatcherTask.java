package org.ivy.settlement.relay.watcher.evm;

import org.bouncycastle.util.encoders.Hex;
import org.ivy.settlement.ethereum.log.EthReceiptsLogParser;
import org.ivy.settlement.ethereum.model.event.EthCrossChainEvent;
import org.ivy.settlement.ethereum.model.event.SettlementLogEventCollector;
import org.ivy.settlement.ethereum.model.settlement.SettlementBlockInfo;
import org.ivy.settlement.ethereum.remote.ExecuteChainRemoter;
import org.ivy.settlement.ethereum.trie.Trie;
import org.ivy.settlement.follower.store.FollowerChainStore;
import org.ivy.settlement.relay.watcher.ChainWatcherTask;
import org.web3j.utils.Numeric;

import java.math.BigInteger;
import java.util.Set;
import java.util.concurrent.Executor;

import static org.ivy.settlement.infrastructure.anyhow.Assert.ensure;

/**
 * description:
 * <p>
 * @Author carrot
 */
public abstract class EvmChainWatcherTask extends ChainWatcherTask {

    ExecuteChainRemoter executeChainRemoter;

    EthReceiptsLogParser ethReceiptsLogParser;

    public EvmChainWatcherTask(int chain, Set<String> targetAddressSet, FollowerChainStore followerChainStore, Executor executor, ExecuteChainRemoter executeChainRemoter) {
        super(chain, followerChainStore, executor);
        this.executeChainRemoter = executeChainRemoter;
        this.ethReceiptsLogParser = new EthReceiptsLogParser(targetAddressSet);
    }

    @Override
    protected SettlementBlockInfo doPullFinalityBlockInfo(long pullNum) {
        var ethReceipts = this.executeChainRemoter.getReceiptsByBlockNumber(Numeric.encodeQuantity(BigInteger.valueOf(pullNum)));
        ensure(!ethReceipts.isEmpty(), "get an empty receipts block[{}] from chain[{}]!", pullNum, super.chain);
        var blockHash = Hex.decode(ethReceipts.getFirst().getBlockHash());
        var collector = new SettlementLogEventCollector(pullNum);
        this.ethReceiptsLogParser.processReceipts(ethReceipts, collector);
        if (collector.getCrossChainEvents() != null && !collector.getCrossChainEvents().isEmpty()) {
            return new SettlementBlockInfo(super.chain, pullNum, blockHash, Trie.calcLocalReceiptsTrie(ethReceipts), collector.getCrossChainEvents().stream().map(EthCrossChainEvent::getCrossChainEvent).toList());
        } else {
            return null;
        }
    }
}
