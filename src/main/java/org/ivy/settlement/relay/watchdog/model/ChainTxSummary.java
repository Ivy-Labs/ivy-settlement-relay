package org.ivy.settlement.relay.watchdog.model;

import java.util.Date;

public class ChainTxSummary {

    final Long livenessPeriod;

    Boolean isUrge;

    Long lastHeartbeat;

    Long nextCommitSeq;

    String nextCommitHash;

    public ChainTxSummary(Long livenessPeriod) {
        this.livenessPeriod = livenessPeriod;
    }

    public Boolean isLossLiveness() {
        Long now = new Date().getTime();
        return now - lastHeartbeat > livenessPeriod;
    }


    public Boolean getUrge() {
        return isUrge;
    }

    public void setUrge(Boolean urge) {
        isUrge = urge;
    }

    public Long getLastHeartbeat() {
        return lastHeartbeat;
    }

    public void setLastHeartbeat(Long lastHeartbeat) {
        this.lastHeartbeat = lastHeartbeat;
    }

    public Long getNextCommitSeq() {
        return nextCommitSeq;
    }

    public void setNextCommitSeq(Long nextCommitSeq) {
        this.nextCommitSeq = nextCommitSeq;
    }

    public String getNextCommitHash() {
        return nextCommitHash;
    }

    public void setNextCommitHash(String nextCommitHash) {
        this.nextCommitHash = nextCommitHash;
    }
}
