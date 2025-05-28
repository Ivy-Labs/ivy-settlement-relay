package org.ivy.settlement.relay.watchdog.interactive.factory;

import org.ivy.settlement.ethereum.model.event.interactive.InteractiveEvent;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveInstance;
import org.ivy.settlement.relay.watchdog.interactive.InteractiveProcessorBus;
import org.ivy.settlement.relay.watchdog.interactive.challenge.da.DAValidateInstance;
import org.ivy.settlement.relay.watchdog.interactive.challenge.vm.EvmChallengeInstance;
import org.ivy.settlement.relay.watchdog.interactive.challenge.war.SafetyWarInstance;
import org.ivy.settlement.relay.watchdog.model.InteractiveStateContextSnapshot;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

import static org.ivy.settlement.ethereum.model.constants.EthLogEventEnum.*;
import static org.ivy.settlement.infrastructure.anyhow.Assert.ensure;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class InteractiveInstanceFactory {

    Map<Integer, BiFunction<InteractiveProcessorBus, InteractiveStateContextSnapshot, InteractiveInstance>> factoryInstanceMap;

    InteractiveProcessorBus processorBus;

    public InteractiveInstanceFactory(InteractiveProcessorBus processorBus) {
        this.processorBus = processorBus;
        factoryInstanceMap = new HashMap<>();
        factoryInstanceMap.put(EVM_CHALLENGE_EVENT.getId(), EvmChallengeInstance::new);
        factoryInstanceMap.put(SAFETY_WAR_EVENT.getId(), SafetyWarInstance::new);
        factoryInstanceMap.put(UPLOAD_SUCCESS_EVENT.getId(), DAValidateInstance::new);
    }

    public InteractiveInstance buildInstance(InteractiveEvent event) {
        var eventCode = event.getEthLogEvent().getId();
        var state = InteractiveLogEventStateFactory.INTERACTIVE_LOG_EVENT_STATE_FACTORY.buildState(eventCode, event.buildInitContent());
        var snapshot = new InteractiveStateContextSnapshot(event.getId(), state);
        var factory = this.factoryInstanceMap.get(eventCode);
        ensure(factory != null, "instance build error, un know event code:{}", eventCode);
        return factory.apply(this.processorBus, snapshot);
    }

    public InteractiveInstance buildInstance(InteractiveStateContextSnapshot snapshot) {
        var eventCode = snapshot.getId();
        var factory = this.factoryInstanceMap.get(eventCode);
        ensure(factory != null, "instance build error, un know event code:{}", eventCode);
        return factory.apply(this.processorBus, snapshot);
    }
}
