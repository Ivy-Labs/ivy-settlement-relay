package org.ivy.settlement.relay.watchdog.state;


import org.ivy.settlement.relay.watchdog.model.IvyBlock;

public interface IvyChainState {

    IvyBlock getIvyBlock(Long height);


    byte[] srcBlockInIvy(Long ivyHeight, Integer chainId, Long height);

}
