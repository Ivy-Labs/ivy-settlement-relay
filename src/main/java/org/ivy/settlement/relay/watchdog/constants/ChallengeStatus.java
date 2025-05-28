package org.ivy.settlement.relay.watchdog.constants;


public enum ChallengeStatus {

    CLAIM(1,"claim"),
    REQUEST(2,"request"),
    SOLVE(3, "solve"),
    WAIT(4, "wait"),
    ENDED(5,"ended");

    private Integer status;

    private String statusName;

    ChallengeStatus(Integer status, String statusName) {
        this.status = status;
        this.statusName = statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public String getStatusName() {
        return statusName;
    }
}
