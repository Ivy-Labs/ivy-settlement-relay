package org.ivy.settlement.relay.watchdog;


import org.ivy.settlement.relay.watchdog.model.Action;


public class Challenge extends Action {

    protected Long index;

    public Challenge(Long challengeIndex) {
        super(null);
        index = challengeIndex;
    }

    public Long getIndex() {
        return index;
    }
}
