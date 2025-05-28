package org.ivy.settlement.relay.watchdog.model;

import org.ivy.settlement.ethereum.model.event.VoterUpdateEvent;
import org.ivy.settlement.infrastructure.crypto.VerifyingKey;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.rlp.RLP;
import org.ivy.settlement.infrastructure.rlp.RLPList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 * <p>
 * @Author carrot
 */
public class VoterInfoState extends Persistable {


    Map<Integer, ValidatorPublicKeyInfo> pk2ValidatorInfo;


    public VoterInfoState(byte[] rlpEncoded) {
        super(rlpEncoded);
    }


    public VoterInfoState(Map<Integer, ValidatorPublicKeyInfo> pk2ValidatorInfo) {
        super(null);
        this.pk2ValidatorInfo = pk2ValidatorInfo;
        this.rlpEncoded = rlpEncoded();
    }

    public VoterInfoState(List<ValidatorPublicKeyInfo> validatorPublicKeyInfos) {
        super(null);
        covertMap(validatorPublicKeyInfos);
        this.rlpEncoded = rlpEncoded();
    }

    private void covertMap(List<ValidatorPublicKeyInfo> validatorPublicKeyInfos) {
        var pk2ValidatorInfo = new HashMap<Integer, ValidatorPublicKeyInfo>();
        if (validatorPublicKeyInfos != null && !validatorPublicKeyInfos.isEmpty()) {
            validatorPublicKeyInfos.forEach(validatorPublicKeyInfo -> pk2ValidatorInfo.put(
                    validatorPublicKeyInfo.getSlotIndex()
                    , validatorPublicKeyInfo.clone()));
        }
        this.pk2ValidatorInfo = pk2ValidatorInfo;
    }


    public byte[] getPublishKey(Integer slot) {
        // Since `address_to_validator_info` is a `BTreeMap`, the `.keys()` iterator
        // is guaranteed to be sorted.
        var key = this.pk2ValidatorInfo.get(slot);
        return key == null? null : key.getConsensusPublicKey().getSecurePublicKey().getRawPubKey();
    }

    public void updateVerifier(List<VoterUpdateEvent> updateValidatorEvents) {
        for (var event : updateValidatorEvents) {
            var ethPubKey = VerifyingKey.generateETHKey(event.getPubKey());
            var ethPubKeyBytes = ethPubKey.getSecurePublicKey().getPubKey();
            switch (event.getOpType()) {
                case VoterUpdateEvent.NODE_UPDATE -> {
                    //var pubKey = ByteUtil.copyFrom(event.getPubKey());
                    this.pk2ValidatorInfo.put(event.getSlotIndex(), new ValidatorPublicKeyInfo(event.getSlotIndex(), event.getConsensusVotingPower(), ethPubKey));
                }
                case VoterUpdateEvent.NODE_DEATH -> {
                    var pre = this.pk2ValidatorInfo.remove(event.getSlotIndex());
                }
                default -> {
                    System.exit(0);
                }
            }
        }

        this.rlpEncoded = rlpEncoded();
    }


    @Override
    protected byte[] rlpEncoded() {
        byte[][] validatorPublicKeyInfos = new byte[pk2ValidatorInfo.size()][];
        int i = 0;
        for (ValidatorPublicKeyInfo validatorPublicKeyInfo: pk2ValidatorInfo.values()) {
            validatorPublicKeyInfos[i] = validatorPublicKeyInfo.getEncoded();
            i++;
        }

        return RLP.encodeList(validatorPublicKeyInfos);
    }

    @Override
    protected void rlpDecoded() {
        var params = RLP.decode2(rlpEncoded);
        var verifier = (RLPList) params.get(0);
        //RLPList validatorPublicKeyInfos = (RLPList) verifier.get(0);

        var address2ValidatorInfo = new HashMap<Integer, ValidatorPublicKeyInfo>();
        for (var rlpElement: verifier) {
            var validatorPublicKeyInfo = new ValidatorPublicKeyInfo(rlpElement.getRLPData());
            address2ValidatorInfo.put(validatorPublicKeyInfo.getSlotIndex(), validatorPublicKeyInfo);
        }

        this.pk2ValidatorInfo = address2ValidatorInfo;
    }
}
