package org.ivy.settlement.relay.watchdog.interactive.factory;

import org.ivy.settlement.relay.watchdog.model.InteractiveLogEventState;
import org.ivy.settlement.relay.watchdog.model.challege.EvmChallengeLogEventState;
import org.ivy.settlement.relay.watchdog.model.da.DAValidateState;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import static org.ivy.settlement.ethereum.model.constants.EthLogEventEnum.*;
import static org.ivy.settlement.infrastructure.anyhow.Assert.ensure;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class InteractiveLogEventStateFactory {

    public static final InteractiveLogEventStateFactory INTERACTIVE_LOG_EVENT_STATE_FACTORY = new InteractiveLogEventStateFactory();

    Map<Integer, Function<byte[], InteractiveLogEventState>> factoryStateMap;

    private InteractiveLogEventStateFactory() {
        this.factoryStateMap = new HashMap<>();
        factoryStateMap.put(EVM_CHALLENGE_EVENT.getId(), EvmChallengeLogEventState::new);
        factoryStateMap.put(UPLOAD_SUCCESS_EVENT.getId(), DAValidateState::new);
        factoryStateMap.put(UPLOAD_RESIGN_EVENT.getId(), DAValidateState::new);

    }

    public InteractiveLogEventState buildState(int eventCode, byte[] content) {
        var factory = this.factoryStateMap.get(eventCode);
        ensure(factory != null, "create log event state error, un know action:{}", eventCode);
        return factory.apply(content);
    }
}
