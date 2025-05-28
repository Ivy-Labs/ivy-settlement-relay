package org.ivy.settlement.relay.watchdog.interactive.challenge.war;

import org.ivy.settlement.ethereum.model.event.interactive.war.SafetyWarEvent;
import org.ivy.settlement.relay.watchdog.constants.ApplyStatus;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveInstance;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveProcessorBus;
import org.ivy.settlement.relay.watchdog.model.InteractiveStateContextSnapshot;
import org.ivy.settlement.relay.watchdog.model.war.WarLogEventState;
import org.web3j.abi.datatypes.Function;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class SafetyWarInstance extends InteractiveInstance<SafetyWarEvent, WarLogEventState> {


    public SafetyWarInstance(InteractiveProcessorBus interactiveProcessorBus, InteractiveStateContextSnapshot stateSnapshot) {
        super(interactiveProcessorBus, stateSnapshot);
    }

    @Override
    protected void doFirstTimeInit(WarLogEventState logEventStateData) {

    }

    @Override
    protected ApplyStatus doApply(SafetyWarEvent event, WarLogEventState LogEventStateData) {
        return ApplyStatus.KEEP_PROCESSING;
    }

    @Override
    protected Function doGetFun(WarLogEventState LogEventStateData) {
        return null;
    }

    @Override
    protected boolean judgeTimeout(long timeoutNumber, WarLogEventState LogEventStateData) {
        return false;
    }

    @Override
    protected boolean doIgnored(WarLogEventState LogEventStateData) {
        return false;
    }
}
