package org.ivy.settlement.relay.watchdog.model;


public class Dispute {
    Long ivyHeight;
    HeaderState headerState;  // null if vaild block

    public Dispute(Long ivyHeight, HeaderState headerState) {
        this.ivyHeight = ivyHeight;
        this.headerState = headerState;
    }

    public Long getIvyHeight() {
        return ivyHeight;
    }

    public HeaderState getHeaderState() {
        return headerState;
    }
}
