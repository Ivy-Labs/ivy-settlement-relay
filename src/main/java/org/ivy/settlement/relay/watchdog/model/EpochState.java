package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.ethereum.model.event.VoterUpdateEvent;
import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.rlp.RLP;
import org.ivy.settlement.infrastructure.rlp.RLPList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.List;

import static org.ivy.settlement.ethereum.model.event.VoterUpdateEvent.NODE_DEATH;
import static org.ivy.settlement.ethereum.model.event.VoterUpdateEvent.NODE_UPDATE;

/**
 * EpochState.java description：
 *
 * @Author laiyiyu
 */
public class EpochState extends Persistable  {

    private static final Logger logger = LoggerFactory.getLogger("consensus");

    long ethEpoch;

    VoterInfoState voterInfoState;

    public EpochState(byte[] encoded) {
        super(encoded);
    }

    public EpochState(long ethEpoch, VoterInfoState voterInfoState) {
        super(null);
        this.ethEpoch = ethEpoch;
        this.voterInfoState = voterInfoState;
        this.rlpEncoded = rlpEncoded();
    }



    public long getEthEpoch() {
        return ethEpoch;
    }


    public byte[] getPublishKey(int slot) {
        return this.voterInfoState.getPublishKey(slot);
    }


    public void resetValidators(VoterInfoState voterInfoState) {
        this.voterInfoState = voterInfoState;
        this.rlpEncoded = rlpEncoded();
    }

    public void updateValidators(List<VoterUpdateEvent> updateEvents) {
        var validEvents = updateEvents
                .stream()
                .filter(e -> {
                    if (e.getOpType() == NODE_DEATH) {
                        return this.ethEpoch == e.getOpEthEpoch() || this.ethEpoch == e.getOpEthEpoch() + 1;
                    } else if (e.getOpType() == NODE_UPDATE) {
                        return this.ethEpoch == e.getOpEthEpoch();
                    } else {
                        logger.error("update Epoch State, but meet un know event: {}", e);
                        return false;
                    }
                })
                .toList();
        if (validEvents.isEmpty()) return;
        this.voterInfoState.updateVerifier(updateEvents);
        this.rlpEncoded = rlpEncoded();
    }

    @Override
    protected byte[] rlpEncoded() {
        byte[] ehtEpoch = RLP.encodeBigInteger(BigInteger.valueOf(this.ethEpoch));
        byte[] validatorVerifier = this.voterInfoState.getEncoded();
        return RLP.encodeList(ehtEpoch, validatorVerifier);
    }

    @Override
    protected void rlpDecoded() {
        var rlpEpochState = (RLPList) RLP.decode2(rlpEncoded).get(0);
        this.ethEpoch = ByteUtil.byteArrayToLong(rlpEpochState.get(0).getRLPData());
        this.voterInfoState = new VoterInfoState(rlpEpochState.get(1).getRLPData());
    }

    public EpochState copy() {
        if (this.rlpEncoded == null) {
            this.rlpEncoded = rlpEncoded();
        }
        return new EpochState(ByteUtil.copyFrom(this.rlpEncoded));
    }

    public EpochState copyForNext(boolean updateEthEpoch) {
        var ethEpoch = updateEthEpoch ? this.ethEpoch + 1 : this.ethEpoch;
        return new EpochState(ethEpoch, new VoterInfoState(ByteUtil.copyFrom(this.voterInfoState.getEncoded())));
    }

    @Override
    public String toString() {
        return "EpochState{" +
                ", ethEpoch=" + ethEpoch +
                ", voterInfoState=" + voterInfoState +
                '}';
    }
}
