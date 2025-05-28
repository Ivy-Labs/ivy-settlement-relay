package org.ivy.settlement.relay.watchdog.state.log.store;

import org.ivy.settlement.infrastructure.datasource.model.Persistable;

import java.util.HashMap;
import java.util.Map;

public class SafetyWarState extends Persistable {
    Long bridgeHeight;
    
    Map<String, ClaimInfo> challengeMap;

    public Boolean isEnd;

    public void updateChalleneInfo(String address, Integer status) {
        ClaimInfo info = challengeMap.get(address);
        if (info != null) {
            info.setClaimStatus(status);
            challengeMap.put(address, info);
        }
    }

    public SafetyWarState(Long height) {
        super(null);
        bridgeHeight = height;
        challengeMap = new HashMap<>();
    }

    public void putChallenge(String address, ClaimInfo c) {
        challengeMap.put(address, c);
    }

    public ClaimInfo getChallenge(String address) {
        return challengeMap.get(address);
    }


    public String getAliveChallenger() {
        for (String a: challengeMap.keySet() ) {
            if (challengeMap.get(a).getClaimStatus() == 1) {
                return a;
            }
        }
        return null;
    }


    public Boolean isClaim(String address) {
        return challengeMap.containsKey(address);
    }

    public Long getBridgeHeight() {
        return bridgeHeight;
    }

    public void setBridgeHeight(Long bridgeHeight) {
        this.bridgeHeight = bridgeHeight;
    }

    public Map<String, ClaimInfo> getChallengeMap() {
        return challengeMap;
    }

    public void setChallengeMap(Map<String, ClaimInfo> challengeMap) {
        this.challengeMap = challengeMap;
    }

    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
