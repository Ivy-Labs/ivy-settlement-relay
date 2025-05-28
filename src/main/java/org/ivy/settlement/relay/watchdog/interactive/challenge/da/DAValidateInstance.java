package org.ivy.settlement.relay.watchdog.interactive.challenge.da;

import org.ivy.settlement.ethereum.model.event.UploadBlobEvent;
import org.ivy.settlement.relay.watchdog.constants.ApplyStatus;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveInstance;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveProcessorBus;
import org.ivy.settlement.relay.watchdog.model.InteractiveStateContextSnapshot;
import org.ivy.settlement.relay.watchdog.model.da.DAValidateState;
import org.web3j.abi.datatypes.Function;

import java.util.List;

/**
 * description:
 * <p>
 * Author lyy
 */
public class DAValidateInstance extends InteractiveInstance<UploadBlobEvent, DAValidateState> {


    long blobNumber;


    List<String> blobs;

    //todo::read preBlobs
    List<String> preBlobs;


    public DAValidateInstance(InteractiveProcessorBus interactiveProcessorBus, InteractiveStateContextSnapshot stateSnapshot) {
        super(interactiveProcessorBus, stateSnapshot);
    }

    protected void initContext(DAValidateState logEventStateData) {

    }

    @Override
    protected void doFirstTimeInit(DAValidateState logEventStateData) {

    }

    @Override
    protected ApplyStatus doApply(UploadBlobEvent event, DAValidateState LogEventStateData) {
        return null;
    }

    @Override
    protected Function doGetFun(DAValidateState LogEventStateData) {
        return null;
    }

    @Override
    protected boolean judgeTimeout(long timeoutNumber, DAValidateState LogEventStateData) {
        return false;
    }
}
