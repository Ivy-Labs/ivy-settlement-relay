package org.ivy.settlement.relay.watchdog.model;

import java.util.Date;

public class ChainSummary {

    final Long livenessPeriod;

    Boolean isUrge;

    // last heartbeat timestamp of source chain
    Long lastHeartbeat;

    // last commit height of source chain
    Long lastCommitHeight;

    public ChainSummary(Long livenessPeriod) {
        this.livenessPeriod = livenessPeriod;
    }

    public Boolean isLossLiveness() {
        Long now = new Date().getTime();
        return now - lastHeartbeat > livenessPeriod;
    }

    public Long getLivenessPeriod() {
        return livenessPeriod;
    }

    public Boolean getUrge() {
        return isUrge;
    }

    public Long getLastHeartbeat() {
        return lastHeartbeat;
    }

    public Long getLastCommitHeight() {
        return lastCommitHeight;
    }

    public void setUrge(Boolean urge) {
        isUrge = urge;
    }

    public void setLastHeartbeat(Long lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public void setLastCommitHeight(Long lastCommitHeight) {
        this.lastCommitHeight = lastCommitHeight;
    }
}
