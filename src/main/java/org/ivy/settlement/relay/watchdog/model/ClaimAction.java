package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.relay.watchdog.constants.ChallengeType;

public class ClaimAction extends Action {
    Issue issue;

    public ClaimAction(Issue issue) {
        super(null);
        this.issue = issue;}
    public ChallengeType getChallengeType() {
        return issue.getChallengeType();
    }

    public Issue getIssue() {
        return issue;
    }
}
