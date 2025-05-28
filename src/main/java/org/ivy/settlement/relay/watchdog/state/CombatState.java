package org.ivy.settlement.relay.watchdog.state;


import org.ivy.settlement.relay.watchdog.state.log.store.ChallengeState;

public interface CombatState {
    Boolean isSettle(Long challenge);
    ChallengeState getChallengeState(Long index);
}
