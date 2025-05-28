package org.ivy.settlement.relay.watchdog.model;

import org.jetbrains.annotations.NotNull;

public class HeaderState implements Comparable<HeaderState> {
    private int chainId;
    private long height;
    private byte[] state;
    public byte[] claimHash() {
        return null;
    }


    public HeaderState(Integer chainId, long height, byte[] state) {
        this.chainId = chainId;
        this.height = height;
        this.state = state;
    }

    public int getChainId() {
        return chainId;
    }

    public long getHeight() {
        return height;
    }

    public byte[] getState() {
        return state;
    }

    public void setChainId(int chainId) {
        this.chainId = chainId;
    }

    public void setHeight(long height) {
        this.height = height;
    }

    public void setState(byte[] state) {
        this.state = state;
    }

    @Override
    public int compareTo(@NotNull HeaderState o) {
        return (int)(this.height - o.getHeight());
    }
}

