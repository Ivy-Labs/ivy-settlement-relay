package org.ivy.settlement.relay.watchdog.model;


import org.ivy.settlement.relay.watchdog.constants.SettleMode;

public class SettleAction extends WarAction{

    private SettleMode mode;

    public SettleAction(SettleMode mode, Issue issue) {
        super(null, issue);
    }

    public SettleMode getMode() {
        return mode;
    }
}
