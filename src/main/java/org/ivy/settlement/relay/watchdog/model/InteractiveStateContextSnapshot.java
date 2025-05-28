package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.rlp.RLP;
import org.ivy.settlement.infrastructure.rlp.RLPList;

import static org.ivy.settlement.relay.watchdog.interactive.factory.InteractiveLogEventStateFactory.INTERACTIVE_LOG_EVENT_STATE_FACTORY;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class InteractiveStateContextSnapshot extends Persistable {

    private String id;

    private boolean firstTimeInit;

    private InteractiveLogEventState interactiveLogEventState;

    public InteractiveStateContextSnapshot(String id, InteractiveLogEventState interactiveLogEventState) {
        super(null);
        this.id = id;
        this.firstTimeInit = false;
        this.interactiveLogEventState = interactiveLogEventState;
        this.rlpEncoded = rlpEncoded();
    }

    public InteractiveStateContextSnapshot(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    public String getId() {
        return id;
    }

    public boolean isFirstTimeInit() {
        return firstTimeInit;
    }

    public InteractiveLogEventState getInteractiveLogEventState() {
        return interactiveLogEventState;
    }

    public void resetState(InteractiveLogEventState interactiveLogEventState) {
        interactiveLogEventState.reEncode();
        this.interactiveLogEventState = interactiveLogEventState;
        this.firstTimeInit = true;
        this.rlpEncoded = rlpEncoded();
    }

    @Override
    protected byte[] rlpEncoded() {
        var id = RLP.encodeString(this.id);
        var eventId = RLP.encodeInt(this.interactiveLogEventState.getLogEventType().getId());
        var stateData = this.interactiveLogEventState.getEncoded();
        return RLP.encodeList(id, eventId, stateData);
    }

    @Override
    protected void rlpDecoded() {
        var payload = (RLPList) RLP.decode2(this.rlpEncoded).get(0);
        this.id = new String(payload.get(0).getRLPData());
        var eventId =  ByteUtil.byteArrayToInt(payload.get(1).getRLPData());
        var content = payload.get(2).getRLPData();
        this.interactiveLogEventState = INTERACTIVE_LOG_EVENT_STATE_FACTORY.buildState(eventId, content);
    }
}
