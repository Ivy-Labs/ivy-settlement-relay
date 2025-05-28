package org.ivy.settlement.relay.watchdog.model;


import org.ivy.settlement.infrastructure.datasource.model.Persistable;

import java.util.HashMap;
import java.util.Set;

public class RLPMap<K,V> extends Persistable {
    private HashMap<K,V> hashMap;
    public RLPMap() {
        super(null);
        hashMap = new HashMap<>();
    }

    public V put(K key, V value) {
        return hashMap.put(key, value);
    }

    public V get(Object key) {
        return hashMap.get(key);
    }

    public V remove(Object key) {
        return hashMap.remove(key);
    }

    public Set<K> keySet() {
        return hashMap.keySet();
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
