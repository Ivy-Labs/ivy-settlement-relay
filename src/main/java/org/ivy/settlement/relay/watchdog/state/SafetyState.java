package org.ivy.settlement.relay.watchdog.state;

import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;
import org.ivy.settlement.relay.watchdog.constants.Role;

public interface SafetyState {

     Object claimInfo(Long warHeight, String address);

     Boolean isReport(Long warHeight, String address);
     Boolean warIsEnd(Long warHeight);

     ChallengeStatus challengeState(Long challengeIndex);

     String findAlivePlayer(Long warHeight, Role role);

     Long onGoingChallenge(Long warHeight, String address);

     Boolean warOvertime(Long warHeight);


}
