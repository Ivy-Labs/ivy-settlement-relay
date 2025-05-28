package org.ivy.settlement.relay.watchdog.state.log.store;

import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.relay.watchdog.constants.ChallengeStatus;

import java.util.HashMap;
import java.util.Map;

public class SafetyReportState extends Persistable {
    Long bridgeHeight;
    Map<String, Report> reportMap;

    public SafetyReportState(Long bridgeHeight) {
        super(null);
        reportMap = new HashMap<>();
    }
    public void addReport(String address, Report report) {
        reportMap.put(address, report);
    }

    public boolean isReport(String address) {
        Report r = reportMap.get(address);
        if (r == null){
            return false;
        }
        return r.getStatus().equals(ChallengeStatus.ENDED);
    }

    public Long getBridgeHeight() {
        return bridgeHeight;
    }

    public Map<String, Report> getReportMap() {
        return reportMap;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }


}
