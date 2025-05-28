package org.ivy.settlement.relay.watchdog.interactive.challenge.vm;

import org.ivy.settlement.relay.watchdog.interactive.challenge.vm.resournce.ArbEvmChallengeResource;
import org.ivy.settlement.relay.watchdog.interactive.challenge.vm.resournce.EvmChallengeResource;
import org.ivy.settlement.relay.watchdog.interactive.challenge.vm.resournce.OpEvmChallengeResource;
import org.ivy.settlement.relay.watchdog.model.challege.EvmChallengeLogEventState;

import java.util.Map;

import static org.ivy.settlement.ethereum.model.constants.ChainType.ARBITRUM;
import static org.ivy.settlement.ethereum.model.constants.ChainType.OPTIMISTIC;

/**
 * description:
 * <p>
 * Author lyy
 */
public class ResourceFactory {

    private static  Map<Integer, EvmChallengeResource> resourceMap;


    {
        resourceMap.put((int) ARBITRUM.getId(), new ArbEvmChallengeResource());
        resourceMap.put((int) OPTIMISTIC.getId(), new OpEvmChallengeResource());
    }




    public static byte[] xxxxState(EvmChallengeLogEventState state) {
        var resource = resourceMap.get(state.getChainId());
        return resource.xxxxState(state);
    }


}
