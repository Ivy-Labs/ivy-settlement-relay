package org.ivy.settlement.relay.watchdog.state.log.store;


import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;

public class Report {
    ChallengeType type;
    ChallengeStatus status;

    public Report(ChallengeType type, ChallengeStatus status) {
        this.type = type;
        this.status = status;
    }

    public ChallengeType getType() {
        return type;
    }

    public ChallengeStatus getStatus() {
        return status;
    }
}
