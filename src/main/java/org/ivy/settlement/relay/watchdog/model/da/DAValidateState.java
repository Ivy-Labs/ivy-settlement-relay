package org.ivy.settlement.relay.watchdog.model.da;

import org.ivy.settlement.ethereum.model.constants.EthLogEventEnum;
import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.rlp.RLP;
import org.ivy.settlement.infrastructure.rlp.RLPList;
import org.ivy.settlement.relay.watchdog.model.InteractiveLogEventState;
import org.web3j.abi.datatypes.Function;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class DAValidateState extends InteractiveLogEventState {

    private static int CODEC_OFFSET = 4;

    byte[] beaconRootId;

    long signerSet;

    byte[] root;

    int warCount;

    List<byte[]> blobHashes;


    public DAValidateState(byte[] rlpEncoded, byte[] beaconRootId, long signerSet, byte[] root, int warCount, List<byte[]> blobHashes) {
        super(null);
        this.beaconRootId = beaconRootId;
        this.signerSet = signerSet;
        this.root = root;
        this.warCount = warCount;
        this.blobHashes = blobHashes;
        this.rlpEncoded = rlpEncoded();
    }

    public DAValidateState(byte[] rlpEncoded) {
        super(rlpEncoded);
    }

    @Override
    public Function getFunction() {
        return null;
    }

    @Override
    public EthLogEventEnum getLogEventType() {
        return null;
    }

    protected byte[] rlpEncoded() {
        byte[][] encodeArray = new byte[CODEC_OFFSET + this.blobHashes.size()][];
        encodeArray[0] = RLP.encodeElement(this.beaconRootId);
        encodeArray[1] = RLP.encodeBigInteger(BigInteger.valueOf(this.signerSet));
        encodeArray[2] = RLP.encodeElement(this.root);
        encodeArray[3] = RLP.encodeInt(this.warCount);

        for(int i = 0; i < this.blobHashes.size(); ++i) {
            encodeArray[i + CODEC_OFFSET] = RLP.encodeElement(this.blobHashes.get(i));
        }

        return RLP.encodeList(encodeArray);
    }

    protected void rlpDecoded() {
        RLPList rlpList = (RLPList)RLP.decode2(this.rlpEncoded).get(0);
        this.beaconRootId = rlpList.get(0).getRLPData();
        this.signerSet = ByteUtil.byteArrayToLong((rlpList.get(1)).getRLPData());
        this.root = rlpList.get(2).getRLPData();
        this.warCount = ByteUtil.byteArrayToInt((rlpList.get(3)).getRLPData());
        this.blobHashes = new ArrayList<>(rlpList.size() - CODEC_OFFSET);

        for(int i = CODEC_OFFSET; i < rlpList.size(); ++i) {
            this.blobHashes.add(rlpList.get(i).getRLPData());
        }
    }

    public void setWarCount(int warCount) {
        this.warCount = warCount;
    }
}
