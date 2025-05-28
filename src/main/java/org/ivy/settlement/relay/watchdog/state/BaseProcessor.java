package org.ivy.settlement.relay.watchdog.state;


import org.ivy.settlement.infrastructure.datasource.model.Keyable;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.web3j.protocol.core.methods.response.EthLog;

public abstract class BaseProcessor{

    protected String contract;

    // PREFIX must be initialized
    protected String PREFIX;
    // public cache
    private final LRUCache cache;

    RocksDbSource dbSource;


    protected BaseProcessor(LRUCache cache, RocksDbSource dbSource) {
        this.cache = cache;
        this.dbSource = dbSource;
    }

    public void deliverLog(EthLog log) {
        try {
            processLog(log);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public abstract void processLog(EthLog log);

    protected void writeStateToDb(Object key, Persistable state) {
        dbSource.put(Keyable.ofDefault((PREFIX + key).getBytes()), state);
    }

    protected Object readStateFromDb(Class<?> model, Object key) {
        return dbSource.get(model, Keyable.ofDefault((PREFIX + key).getBytes()));
    }

    protected void writeStateToCache(Object key, Object state) {
        cache.put((PREFIX+key).getBytes(), state);
    }
    protected Object readStateFromCache(Object key) {
        return cache.get(Keyable.ofDefault((PREFIX + key).getBytes()));
    }
}
