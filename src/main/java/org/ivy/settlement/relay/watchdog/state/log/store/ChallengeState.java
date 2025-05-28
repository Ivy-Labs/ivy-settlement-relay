package org.ivy.settlement.relay.watchdog.state.log.store;

import org.apache.commons.lang3.tuple.Pair;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.relay.watchdog.contract.ChallengeManager;
import org.web3j.protocol.core.methods.response.BaseEventResponse;

import java.util.Date;

public class ChallengeState extends Persistable {
    public Long index;
    Long seq;
    Long deadline;
    Pair<String, String> players;
    String reactor;
    String settleAddress;
    String progressSnap;
    BaseEventResponse latestEvent;
    Boolean isSettle = false;

    public ChallengeState(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    public ChallengeState(ChallengeManager.PublishedEventResponse publish) {
        super(null);

    }
    public Boolean overtime() {
        return new Date().getTime() > deadline;
    }

    public Long getIndex() {
        return index;
    }

    public Long getSeq() {
        return seq;
    }

    public Long getDeadline() {
        return deadline;
    }

    public Pair<String, String> getPlayers() {
        return players;
    }

    public String getReactor() {
        return reactor;
    }

    public String getSettleAddress() {
        return settleAddress;
    }

    public String getProgressSnap() {
        return progressSnap;
    }

    public BaseEventResponse getLatestEvent() {
        return latestEvent;
    }

    public Boolean getSettle() {
        return isSettle;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }

    public void setDeadline(Long deadline) {
        this.deadline = deadline;
    }

    public void setPlayers(Pair<String, String> players) {
        this.players = players;
    }

    public void setReactor(String reactor) {
        this.reactor = reactor;
    }

    public void setSettleAddress(String settleAddress) {
        this.settleAddress = settleAddress;
    }

    public void setProgressSnap(String progressSnap) {
        this.progressSnap = progressSnap;
    }

    public void setLatestEvent(BaseEventResponse latestEvent) {
        this.latestEvent = latestEvent;
    }

    public void setSettle(Boolean settle) {
        isSettle = settle;
    }

    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }
}
