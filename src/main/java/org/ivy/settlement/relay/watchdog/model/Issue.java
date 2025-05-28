package org.ivy.settlement.relay.watchdog.model;


import org.ivy.settlement.infrastructure.datasource.model.Keyable;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.relay.watchdog.constants.ChallengeType;
import org.jetbrains.annotations.NotNull;


public class Issue extends Persistable implements Keyable {

    private Long warHeight;
    private ChallengeType challengeType;
    private Dispute dispute;

    public Issue(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    public Issue(Long warHeight, ChallengeType type, Dispute dispute) {
        super(null);
    }

    public Long getWarHeight() {
        return warHeight;
    }

    public ChallengeType getChallengeType() {
        return challengeType;
    }

    public Dispute getDispute() {
        return dispute;
    }

    public byte[] getClaimHash() {
        return null;
    }

    public Long getDisputeHeight() {
        if (dispute != null)
            return dispute.getIvyHeight();
        return null;
    }

    public Long getDisputeSrcHeight() {
        if (dispute != null)
            return dispute.getHeaderState().getHeight();
        return null;
    }

    public Integer getDisputeSrcChainId() {
        if (dispute != null)
            return dispute.getHeaderState().getChainId();
        return null;
    }

    public byte[] getDisputeSrcHash() {
        if (dispute !=null)
            return dispute.getHeaderState().getState();
        return null;
    }




    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }

    public byte[] keyBytes() {
        return null;
    }

    @Override
    public int compareTo(@NotNull Keyable o) {
        return 0;
    }
}
