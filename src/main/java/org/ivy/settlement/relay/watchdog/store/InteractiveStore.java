package org.ivy.settlement.relay.watchdog.store;

import org.apache.commons.lang3.tuple.Pair;
import org.ivy.settlement.ethereum.model.event.interactive.InteractiveEvent;
import org.ivy.settlement.infrastructure.collections.LRUCache;
import org.ivy.settlement.infrastructure.datasource.model.Keyable;
import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveInstance;
import org.ivy.settlement.relay.watchdog.model.EpochState;
import org.ivy.settlement.relay.watchdog.model.InteractiveStateContextSnapshot;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Optional;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class InteractiveStore {

    protected RocksDbSource dbSource;

    LRUCache<String, InteractiveStateContextSnapshot> processingNoTimeoutInstance;


    LRUCache<String, InteractiveStateContextSnapshot> processingWillTimeoutInstance;



    public long latestProcessedNumber() {
        return 1;
    }


    public InteractiveStateContextSnapshot getSnapshot(InteractiveEvent event) {
        var state = event.willTimeout()? processingWillTimeoutInstance.get(event.getId()) : processingNoTimeoutInstance.get(event.getId());
        if (state == null) {
            state = (InteractiveStateContextSnapshot) this.dbSource.get(InteractiveStateContextSnapshot.class, Keyable.ofDefault(event.getId().getBytes(StandardCharsets.UTF_8)));
            if (state != null) {
                var old = event.willTimeout()? processingWillTimeoutInstance.put(event.getId(), state) : processingNoTimeoutInstance.put(event.getId(), state);
            }
        }
        return state;
    }


    public void persist(Pair<Optional<EpochState>, Optional<EpochState>> epochStates, Map<String, InteractiveInstance> batch, long latestProcessedNum) {

    }





}
