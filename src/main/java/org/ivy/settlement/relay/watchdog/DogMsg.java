package org.ivy.settlement.relay.watchdog;

import org.ivy.settlement.infrastructure.datasource.model.Persistable;

public class DogMsg extends Persistable {

    String id;

    byte[] msgContent;

    public DogMsg(byte[] msgContent) {
        super(null);
        this.id = "";
        this.msgContent = msgContent;
    }

    public String getId() {
        return id;
    }

    public byte[] getMsgContent() {
        return msgContent;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
