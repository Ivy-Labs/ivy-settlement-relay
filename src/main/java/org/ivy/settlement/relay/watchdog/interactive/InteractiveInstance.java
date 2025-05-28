package org.ivy.settlement.relay.watchdog.interactive;

import org.ivy.settlement.ethereum.model.event.interactive.InteractiveEvent;
import org.ivy.settlement.relay.watchdog.constants.ApplyStatus;
import org.ivy.settlement.relay.watchdog.model.InteractiveLogEventState;
import org.ivy.settlement.relay.watchdog.model.InteractiveStateContextSnapshot;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.web3j.abi.datatypes.Function;

import java.util.Optional;

import static org.ivy.settlement.relay.watchdog.constants.ApplyStatus.EXE_RETRY;

/**
 * description:
 * <p>
 * Author lyy
 */
public abstract class InteractiveInstance<C extends InteractiveEvent, E extends InteractiveLogEventState> {

    protected static final Logger logger = LoggerFactory.getLogger("interactive");


    protected String id;

    protected InteractiveStateContextSnapshot stateSnapshot;

    Optional<Function> functionOp;

    InteractiveProcessorBus interactiveProcessorBus;

    public InteractiveInstance(InteractiveProcessorBus interactiveProcessorBus, InteractiveStateContextSnapshot stateSnapshot) {
        this.id = stateSnapshot.getId();
        this.stateSnapshot = stateSnapshot;
        this.interactiveProcessorBus = interactiveProcessorBus;
        interactiveProcessorBus.registerInstance(this);
        this.initContext((E)stateSnapshot.getInteractiveLogEventState());
        if (!stateSnapshot.isFirstTimeInit()) {
            firstTimeInit((E)stateSnapshot.getInteractiveLogEventState());
            stateSnapshot.resetState(stateSnapshot.getInteractiveLogEventState());

        }

        var fun = doGetFun((E)stateSnapshot.getInteractiveLogEventState());
        if (fun == null) {
            this.functionOp = Optional.empty();
        } else {
            this.functionOp = Optional.of(fun);
        }
    }

    protected void initContext(E logEventStateData) {

    }

    private void firstTimeInit(E logEventStateData) {
        doFirstTimeInit(logEventStateData);
    }

    protected abstract void doFirstTimeInit(E logEventStateData);

    public ApplyStatus apply(C event) {
        try {
            //copy
            E interactiveLogEventState = (E) stateSnapshot.getInteractiveLogEventState();
            var applyStatus = doApply(event, interactiveLogEventState);
            var fun = interactiveLogEventState.getFunction();
            if (fun == null) {
                this.functionOp = Optional.empty();
            } else {
                this.functionOp = Optional.of(fun);
            }
            stateSnapshot.resetState(interactiveLogEventState);
            return applyStatus;
        }  catch (Throwable t) {
            logger.error("InteractiveInstance apply state transfer un know error!", t);
            return EXE_RETRY;
        }
    }

    protected abstract ApplyStatus doApply(C event, E LogEventStateData);


    public Optional<Function> getFunction() {
        return this.functionOp;
    }

    protected abstract Function doGetFun(E LogEventStateData);

    public boolean isTimeout(long timeoutNumber) {
        return judgeTimeout(timeoutNumber, (E)this.stateSnapshot.getInteractiveLogEventState());
    }

    protected abstract boolean judgeTimeout(long timeoutNumber, E LogEventStateData);


    public boolean ignored() {
        return doIgnored((E)this.stateSnapshot.getInteractiveLogEventState());
    }

    protected boolean doIgnored(E LogEventStateData) {
        return false;
    }

    protected boolean willTimeout() {
        return false;
    }

    public String getId() {
        return id;
    }

    public InteractiveStateContextSnapshot getStateSnapshot() {
        return stateSnapshot;
    }
}
