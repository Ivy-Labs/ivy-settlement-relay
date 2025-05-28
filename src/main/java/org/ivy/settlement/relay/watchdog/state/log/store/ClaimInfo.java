package org.ivy.settlement.relay.watchdog.state.log.store;

import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.ivy.settlement.relay.watchdog.model.HeaderState;

public class ClaimInfo {
    Integer claimStatus; // Safety:0-Nonexistent,1-Idle,2-In Challenge,3-Dead,4-Bridge Network Node | Liveness:0-Not Created,1-Created,2-Gracefully Closed,3-Settled(Usable),4-Settled(Unusable)
    ChallengeType type;
    HeaderState claim;

    public ClaimInfo(Integer claimStatus, ChallengeType type, HeaderState claim) {
        this.claimStatus = claimStatus;
        this.type = type;
        this.claim = claim;
    }

    public Integer getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(Integer claimStatus) {
        this.claimStatus = claimStatus;
    }

    public ChallengeType getType() {
        return type;
    }

    public void setType(ChallengeType type) {
        this.type = type;
    }

    public HeaderState getClaim() {
        return claim;
    }

    public void setClaim(HeaderState claim) {
        this.claim = claim;
    }
}
