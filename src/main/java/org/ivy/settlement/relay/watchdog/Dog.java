package org.ivy.settlement.relay.watchdog;

import org.apache.commons.lang3.tuple.Pair;
import org.ivy.settlement.infrastructure.datasource.model.Keyable;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.model.RLPMap;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public abstract class Dog implements Send {
    String PREFIX;
    RocksDbSource dbSource;
    final String TOTAL_MSG = "pending_msg";
    final String MSG = "msg";
    RLPMap<Object, DogMsg> unFinishedMsg;

    ArrayDeque<DogMsg> pendingMsg;



    public void init() {

    }

    @Override
    public void sendMsg(DogMsg msg) {
        pendingMsg.add(msg);
        unFinishedMsg.put(msg.getId(), msg);
        List<Pair<Keyable, Persistable>> batch = new ArrayList<>();
        batch.add(Pair.of(Keyable.ofDefault((PREFIX+TOTAL_MSG).getBytes()), unFinishedMsg));
        batch.add(Pair.of(Keyable.ofDefault(((PREFIX+MSG)+msg.getId()).getBytes()), msg));
        dbSource.updateBatch(batch);
    }

    public void run() {
        loadPendingMsg();
        processMsg();
    }



    protected abstract void processMsg();
    protected abstract void loadPendingMsg();
    // local index
    protected void finishMsg(String id) {
        unFinishedMsg.remove(id);
        List<Pair<Keyable, Persistable>> batch = new ArrayList<>();
        batch.add(Pair.of(Keyable.ofDefault((PREFIX+TOTAL_MSG).getBytes()), unFinishedMsg));
        // delete value
        batch.add(Pair.of(Keyable.ofDefault(((PREFIX+MSG)+id).getBytes()), new DogMsg(null)));
        dbSource.updateBatch(batch);
    }

}
