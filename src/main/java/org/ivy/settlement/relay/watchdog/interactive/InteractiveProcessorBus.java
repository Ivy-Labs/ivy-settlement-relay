package org.ivy.settlement.relay.watchdog.interactive;

import org.ivy.settlement.ethereum.model.event.EpochChangeEvent;
import org.ivy.settlement.ethereum.model.event.ManagerChainEvent;
import org.ivy.settlement.ethereum.model.event.VoterUpdateEvent;
import org.ivy.settlement.ethereum.model.event.interactive.InteractiveEvent;
import org.ivy.settlement.ethereum.store.BeaconChainStore;
import org.ivy.settlement.infrastructure.collections.LRUCache;
import org.ivy.settlement.infrastructure.string.StringUtils;
import org.ivy.settlement.relay.watchdog.interactive.factory.InteractiveInstanceFactory;
import org.ivy.settlement.relay.watchdog.store.InteractiveStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

import static org.ivy.settlement.ethereum.model.constants.EthLogActionEnum.ALL;
import static org.ivy.settlement.infrastructure.anyhow.Assert.ensure;

/**
 * description:
 * <p>
 * Author lyy
 */
public class InteractiveProcessorBus {

    private static final Logger logger = LoggerFactory.getLogger("watchdog");

    InteractiveStore interactiveStore;

    BeaconChainStore beaconChainStore;

    LRUCache<String, InteractiveInstance> noTimeoutProcessingInstances;

    //Will time InteractiveInstance has a little, so cache all;
    HashMap<String, InteractiveInstance> allWillTimeoutProcessingInstance;

    InteractiveInstanceFactory instanceFactory;

    InteractiveInstanceScheduler scheduler;

    EthEpochStateManager ethEpochStateManager;


    public void registerInstance(InteractiveInstance interactiveInstance) {
        if (interactiveInstance.willTimeout()) {
            this.allWillTimeoutProcessingInstance.put(interactiveInstance.id, interactiveInstance);
        } else {
            this.noTimeoutProcessingInstances.put(interactiveInstance.id, interactiveInstance);
        }
    }

    public long getLatestValidOffset() {
        return this.beaconChainStore.getCurrentLatestValidNumber();
    }

    public void processAll() {
        try {
            var start = this.interactiveStore.latestProcessedNumber() + 1;
            var end = this.beaconChainStore.getCurrentLatestValidNumber();

            if (start >= end) {
                return;
            }

            var batch = new HashMap<String, InteractiveInstance>();
            var allLogsOp = this.beaconChainStore.retrievalLog(start, end, ALL);
            ensure(allLogsOp.isPresent(), "from {} to {} ,INTERACTIVE logs lack!", start, end);
            for (var allEvents : allLogsOp.get()) {
                for (var event : allEvents) {
                    if (event instanceof InteractiveEvent interactiveEvent) {
                        processInteractiveEvent(interactiveEvent, batch);
                    } else if (event instanceof ManagerChainEvent managerChainEvent) {
                        processManagerChainEvent(managerChainEvent);
                    } else if (event instanceof VoterUpdateEvent || event instanceof EpochChangeEvent) {
                        this.ethEpochStateManager.apply(event);
                    }
                }
            }

            processAllWillTimeoutInstance(batch, end);
            this.interactiveStore.persist(this.ethEpochStateManager.export(), batch, end);
        } catch (Throwable e) {
            logger.warn("instance bus process all error!", e);
            this.scheduler.clear();
            this.clear();
        }
    }

    private void processInteractiveEvent(InteractiveEvent interactiveEvent, Map<String, InteractiveInstance> batch) {
        var interactiveInstance = this.getInstance(interactiveEvent);

        if (interactiveInstance != null) {
            var applyStatus = interactiveInstance.apply(interactiveEvent);
            switch (applyStatus) {
                case KEEP_PROCESSING -> {
                    batch.put(interactiveInstance.id, interactiveInstance);
                    this.scheduler.schedule(interactiveInstance);
                }
                case EXE_FINISH, STATE_ERROR -> {
                    batch.put(interactiveInstance.id, null);// for delete
                    this.deleteCache(interactiveInstance);
                    this.scheduler.cancel(interactiveInstance);
                }
                case EXE_RETRY -> throw new RuntimeException(StringUtils.format("{} will retry", interactiveInstance));
            }
        } else {
            if (interactiveEvent.createInstant()) {
                interactiveInstance = instanceFactory.buildInstance(interactiveEvent);
                if (!interactiveInstance.ignored()) {
                    batch.put(interactiveInstance.id, interactiveInstance);
                    addCache(interactiveInstance);
                    this.scheduler.schedule(interactiveInstance);
                }
            }
        }
    }

    private InteractiveInstance getInstance(InteractiveEvent event) {
        var instance = event.willTimeout()? this.allWillTimeoutProcessingInstance.get(event.getId()) : this.noTimeoutProcessingInstances.get(event.getId());
        if (instance != null) return instance;

        var snapshot = this.interactiveStore.getSnapshot(event);
        if (snapshot != null) {
            return instanceFactory.buildInstance(snapshot);
        }

        return null;
    }

    private void processAllWillTimeoutInstance(Map<String, InteractiveInstance> batch, long currentNumber) {
        for (var instance : allWillTimeoutProcessingInstance.values()) {
            if (instance.isTimeout(currentNumber)) {
                batch.put(instance.id, null);// for delete
                deleteCache(instance);
                this.scheduler.cancel(instance);
            }
        }
    }

    private void addCache(InteractiveInstance instance) {
        if (instance.willTimeout()) {
            this.allWillTimeoutProcessingInstance.put(instance.getId(), instance);
        } else {
            this.noTimeoutProcessingInstances.put(instance.getId(), instance);
        }
    }

    private void deleteCache(InteractiveInstance instance) {
        if (instance.willTimeout()) {
            this.allWillTimeoutProcessingInstance.remove(instance.getId());
        } else {
            this.noTimeoutProcessingInstances.remove(instance.getId());
        }
    }

    private void clear() {
        this.noTimeoutProcessingInstances.clear();
        this.allWillTimeoutProcessingInstance.clear();
        //todo ::reset EthEpochStateManager
    }

    private void processManagerChainEvent(ManagerChainEvent managerChainEvent) {

    }
}
