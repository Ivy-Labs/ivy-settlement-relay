package org.ivy.settlement.relay.watcher;

import org.ivy.settlement.ethereum.model.settlement.LatestFollowerChainBlockBatch;
import org.ivy.settlement.ethereum.model.settlement.SettlementBlockInfo;
import org.ivy.settlement.follower.store.FollowerChainStore;
import org.ivy.settlement.infrastructure.string.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

import static org.ivy.settlement.ethereum.watcher.BeaconWatcher.MAX_RETRY_TIME;

/**
 * description:
 * <p>
 * @Author carrot
 */
public abstract class ChainWatcherTask implements Runnable {

    protected static final Logger logger = LoggerFactory.getLogger("eth");

    protected static final int MAX_REQUEST_BATCH = 8;

    protected boolean stop;

    protected int chain;

    FollowerChainStore followerChainStore;

    Executor executor;

    public ChainWatcherTask(int chain, FollowerChainStore followerChainStore, Executor executor) {
        this.chain = chain;
        this.followerChainStore = followerChainStore;
        this.executor = executor;
    }

    @Override
    public void run() {
        while (true) {
            try {
                if (stop) {
                    return;
                }

                tryUpdateFinalityState();
            } catch (Throwable t) {
                logger.warn(String.format("[%s] do work warn!", Thread.currentThread().getName()), t);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {
                }
            }

        }
    }

    protected void tryUpdateFinalityState() {
        var currentNumber = followerChainStore.retrievalFollowerChainOffset(this.chain) + 1;
        var latestNumber = getFinalityNumber();
        while (currentNumber < latestNumber) {
            var start = System.currentTimeMillis();
            var maxPullNumber = Math.min(currentNumber + MAX_REQUEST_BATCH, latestNumber);
            var batchSize = (int) (maxPullNumber - currentNumber + 1);
            var pullFuts = new CompletableFuture[batchSize];
            var settlementBlockInfos = new ArrayList<SettlementBlockInfo>(batchSize);
            var i = 0;
            for (var pullNum = currentNumber; pullNum <= maxPullNumber; pullNum++) {
                pullFuts[i] = pullFinalityBlockInfo(pullNum);
            }

            for (var fut : pullFuts) {
                try {
                    var blockInfo = fut.get();
                    if (blockInfo == null) continue;
                    settlementBlockInfos.add((SettlementBlockInfo) blockInfo);
                } catch (Exception e) {
                    break;
                }
            }

            var latestFollowerChainBlockBatch = new LatestFollowerChainBlockBatch(chain, currentNumber, maxPullNumber, settlementBlockInfos);

            logger.info("[{}] pull slot from {} to {}, cost {}ms", chain, currentNumber, maxPullNumber, System.currentTimeMillis() - start);
            this.followerChainStore.persist(List.of(latestFollowerChainBlockBatch));
            currentNumber = followerChainStore.retrievalFollowerChainOffset(this.chain) + 1;
        }
    }

    protected abstract long getFinalityNumber();

    protected CompletableFuture<SettlementBlockInfo> pullFinalityBlockInfo(long pullNum) {
        var future = new CompletableFuture<SettlementBlockInfo>();
        this.executor.execute(() -> {
            var maxRetry = MAX_RETRY_TIME;
            while (maxRetry >= 0) {
                try {
                    future.complete(doPullFinalityBlockInfo(pullNum));
                    return;
                } catch (Exception e) {
                    logger.warn("get signed block failed!", e);
                    maxRetry--;
                }
            }
            future.completeExceptionally(new RuntimeException(StringUtils.format("[{}]get finality block[{}] failed, cause out of max retry!", ChainWatcherTask.this.chain, pullNum)));
        });
        return future;
    }

    protected abstract SettlementBlockInfo doPullFinalityBlockInfo(long pullNum);
}
