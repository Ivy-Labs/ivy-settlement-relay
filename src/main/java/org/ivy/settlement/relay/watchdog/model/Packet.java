package org.ivy.settlement.relay.watchdog.model;

public class Packet {

    Long height;

    Long bridgeHeight;

    Long seq;

    String packetHash;

    public Long getHeight() {
        return height;
    }

    public Long getBridgeHeight() {
        return bridgeHeight;
    }

    public Long getSeq() {
        return seq;
    }

    public String getPacketHash() {
        return packetHash;
    }
}
