package org.ivy.settlement.relay.watchdog.constants;



public enum SettleMode {
    END(0,"warEnd"),
    PEACEFULEND(1,"peacefulEnd");
    private int id;
    private String mode;

    SettleMode(int id, String mode) {
        this.id = id;
        this.mode = mode;
    }

    public int getId() {
        return id;
    }

    public String getMode() {
        return mode;
    }
}
