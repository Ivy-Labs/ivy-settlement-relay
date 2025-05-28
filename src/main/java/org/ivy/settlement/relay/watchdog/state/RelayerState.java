package org.ivy.settlement.relay.watchdog.state;

public interface RelayerState {

    public boolean isSrcPacketCommit(Integer chainId, String packetHash);

}
