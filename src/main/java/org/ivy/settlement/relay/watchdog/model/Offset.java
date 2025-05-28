package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.infrastructure.datasource.model.Persistable;

public class Offset extends Persistable {

    Long value;

    public Offset(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    public void add(Long v){
        this.value += v;
    }

    public Offset(Long value) {
        super(null);
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
