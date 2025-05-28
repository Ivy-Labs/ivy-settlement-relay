package org.ivy.settlement.relay.watchdog;

import org.ivy.settlement.relay.watchdog.checker.Checker;
import org.ivy.settlement.relay.watchdog.model.Packet;

import java.util.Map;


public class CheckerManager {
    private Map<Integer, Checker> checkers;

    public CheckerManager() {

    }

    public Checker getChecker(int chainId) {
        return checkers.get(chainId);
    }
    public FilteredBlock getHeaderState(int chainId, long height) {
        return null;
    }
    public FilteredBlock getNextHeaderState(int chainId, long height) {
        return null;
    }

    public Packet getSrcPacket(Integer chainId, Long seq) {return null;}
    public Boolean canChallenge(Integer chainId, Long height) {
        return checkers.get(chainId).canChallenge(height);
    }

    public byte[] getSrcStateHash(Integer chainId, Long height) {
        return null;
    }

}
