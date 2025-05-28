package org.ivy.settlement.relay.watchdog.interactive.challenge.vm;

import org.ivy.settlement.ethereum.model.event.interactive.challenge.EVMChallengeEvent;
import org.ivy.settlement.relay.watchdog.constants.ApplyStatus;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveInstance;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveProcessorBus;
import org.ivy.settlement.relay.watchdog.model.InteractiveStateContextSnapshot;
import org.ivy.settlement.relay.watchdog.model.challege.EvmChallengeLogEventState;
import org.web3j.abi.datatypes.Function;

/**
 * description:
 * <p>
 * Author lyy
 */
public class EvmChallengeInstance extends InteractiveInstance<EVMChallengeEvent, EvmChallengeLogEventState> {

    public EvmChallengeInstance(InteractiveProcessorBus interactiveProcessorBus, InteractiveStateContextSnapshot stateSnapshot) {
        super(interactiveProcessorBus, stateSnapshot);
    }

    @Override
    protected void doFirstTimeInit(EvmChallengeLogEventState logEventStateData) {
    }

    @Override
    protected ApplyStatus doApply(EVMChallengeEvent event, EvmChallengeLogEventState LogEventStateData) {


        var proof = ResourceFactory.xxxxState(LogEventStateData);
        return ApplyStatus.KEEP_PROCESSING;
    }

    @Override
    protected Function doGetFun(EvmChallengeLogEventState LogEventStateData) {
        return null;
    }

    @Override
    protected boolean judgeTimeout(long timeoutNumber, EvmChallengeLogEventState LogEventStateData) {
        return false;
    }

    @Override
    protected boolean willTimeout() {
        return true;
    }
}
