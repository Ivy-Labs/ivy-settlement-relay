package org.ivy.settlement.relay.watchdog.state.log.store;

import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;

public class Game extends Persistable {
    Long warHeight;
    ClaimInfo info;
    ChallengeStatus status;

    public Game(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    public Game(Long warHeight, ClaimInfo info, ChallengeStatus status) {
        super(null);
    }

    public Long getWarHeight() {
        return warHeight;
    }

    public void setWarHeight(Long warHeight) {
        this.warHeight = warHeight;
    }

    public ClaimInfo getInfo() {
        return info;
    }

    public void setInfo(ClaimInfo info) {
        this.info = info;
    }

    public ChallengeStatus getStatus() {
        return status;
    }

    public void setStatus(ChallengeStatus status) {
        this.status = status;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
