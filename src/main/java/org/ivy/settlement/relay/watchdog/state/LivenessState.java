package org.ivy.settlement.relay.watchdog.state;

import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.Role;
import org.ivy.settlement.relay.watchdog.model.HeaderState;

import java.util.ArrayList;

public interface LivenessState {
    Integer claimState(byte[] claimId);

    ArrayList<HeaderState> aliveClaim(String address);

    Boolean isClaimed(String address, byte[] claimId);

    ChallengeStatus challengeState(Long challengeIndex);
    Long onGoingChallenge(String address);
    String findAlivePlayer(byte[] claimId, Role role);
    Boolean warOvertime(Long warHeight);

    Boolean warIsEnd(byte[] claimId);


}
