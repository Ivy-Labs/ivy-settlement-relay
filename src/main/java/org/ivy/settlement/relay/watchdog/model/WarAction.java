package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.relay.watchdog.constants.Role;

public class WarAction extends Action {
    Role role;
    Issue issue;
    private Long onGoingChallenge;

    public WarAction (Role role, Issue issue){
        super(null);
        this.role = role;
        this.issue = issue;
    }

    public Role getRole() {
        return role;
    }

    public Issue getIssue() {
        return issue;
    }

    public Long getOnGoingChallenge() {
        return onGoingChallenge;
    }

    public void setOnGoingChallenge(Long onGoingChallenge) {
        this.onGoingChallenge = onGoingChallenge;
    }
}
