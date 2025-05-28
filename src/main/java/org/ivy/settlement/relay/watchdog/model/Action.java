package org.ivy.settlement.relay.watchdog.model;

import java.util.Date;

public class Action {

    String id; // corresponding index in msg

    protected Long lastExecTime;

    final Long retryPeriod = 10L;

    public Action(Long retryPeriod) {
        lastExecTime = 0L;
    }

    public String getId() {
        return id;
    }

    public void resetTExecTime() {
        lastExecTime = 0L;
    }
    
    public Boolean tryAgain() {
        return new Date().getTime() - lastExecTime > retryPeriod;
    }

    public void setLastExecTime(long time) {
        this.lastExecTime = lastExecTime;
    }

    public Long getLastExecTime() {
        return lastExecTime;
    }

    public Long getRetryPeriod() {
        return retryPeriod;
    }
}
