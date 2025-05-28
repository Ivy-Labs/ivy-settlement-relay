package org.ivy.settlement.relay.watchdog.state.log.store;


import java.util.HashMap;
import java.util.Map;

public class LivenessChallengeState {
    String claimId;
    Long createdTime;
    ClaimInfo claimInfo;

    Map<String, Integer> against; 
    Map<String, Integer> advocate;

    public LivenessChallengeState(ClaimInfo claimInfo) {
        this.claimInfo = claimInfo;
        against = new HashMap<>();
        advocate = new HashMap<>();
    }

    public void addNewPlayer(String address, Integer state) {
        // TODO：challenger identity
        advocate.put(address, state);
    }

    public void updatePlayer(String address, Integer identity, Integer state) {
        switch (identity) {
            case 1->{
                // external defender
                advocate.put(address, state);
            }
            case 2, 3 -> {
                against.put(address, state);
            }
            default -> throw new RuntimeException("Unknown identity: " + identity);
        }
    }

    public void updatePlayer(String address, Integer state) {
        if (advocate.get(address) != null) {
            advocate.put(address, state);
        }
        if (against.get(address) != null) {
            against.put(address, state);
        }
    }


    public void settleChallenge(String win, String lose) {

    }

    public Boolean existInAgainst(String address) {
        return against.get(address) != null;
    }

    public Boolean existInAdvocate(String address) {
        return advocate.get(address) != null;
    }

    public String getClaimId() {
        return claimId;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public ClaimInfo getClaimInfo() {
        return claimInfo;
    }

    public Map<String, Integer> getAgainst() {
        return against;
    }

    public Map<String, Integer> getAdvocate() {
        return advocate;
    }
}
