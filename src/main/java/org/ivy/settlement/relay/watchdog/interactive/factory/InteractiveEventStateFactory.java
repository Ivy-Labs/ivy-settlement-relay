package org.ivy.settlement.relay.watchdog.interactive.factory;

import org.ivy.settlement.relay.watchdog.model.InteractiveLogEventState;

/**
 * description:
 * <p>
 * @Author carrot
 */
public interface InteractiveEventStateFactory {

    InteractiveLogEventState buildState(int eventCode, byte[] content);
}
