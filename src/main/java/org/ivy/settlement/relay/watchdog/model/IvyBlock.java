package org.ivy.settlement.relay.watchdog.model;


import org.ivy.settlement.infrastructure.bytes.ByteUtil;
import org.ivy.settlement.infrastructure.crypto.HashUtil;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.merkle.MerkleProof;
import org.ivy.settlement.infrastructure.merkle.MerkleTree;
import org.ivy.settlement.relay.watchdog.contract.IIvyChainManager;
import org.web3j.protocol.core.methods.response.EthLog;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class IvyBlock extends Persistable {
    private Long epoch;
    private Long height;
    private Integer txNums;
    private byte[] root;
    private byte[] data;
    private BigInteger signerSet;
    private boolean isEmpty;
    private ArrayList<HeaderState> headerStates;

    public boolean isEmpty() {
        return this.isEmpty;
    }

    public IvyBlock(EthLog log) {
        super(null);
    }

    public IvyBlock(IIvyChainManager.UploadSuccessEventResponse uploadEvent) {
        super(null);
        epoch = uploadEvent.epoch.longValue();
        height = uploadEvent.height.longValue();

    }



    public ArrayList<HeaderState> getHeaderStates() {
        if (this.headerStates == null) {
            recoverHeaderState();
        }
        return this.headerStates;
    }

    public HeaderState getHeaderState(Integer chainId, Long height) {
        return null;
    }

    private void recoverHeaderState(){
        if (this.headerStates != null) {
            return;
        }
        if (this.isEmpty) {
            throw new RuntimeException("empty bridge block can not recovery");
        }
        int num = this.data.length / 42;
        ArrayList<HeaderState> headerStates = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            HeaderState state = new HeaderState(null, null, null);
            state.setChainId(ByteBuffer.wrap(Arrays.copyOfRange(data,i,i+2)).getInt());
            state.setHeight(ByteBuffer.wrap(Arrays.copyOfRange(data,i+2,i+10)).getLong());
            state.setState(Arrays.copyOfRange(data,i+10,i+42));
            headerStates.add(state);
        }
        this.headerStates = headerStates;
    }

    public byte[] getMerkleRoot() {
        if (this.headerStates == null) {
            recoverHeaderState();
        }
        ArrayList<byte[]> leaves = (ArrayList<byte[]>) this.headerStates.stream().map((header)->{
            byte[] b1 = ByteUtil.shortToBytes((short) header.getChainId());
            byte[] b2 = ByteUtil.longToBytes(header.getHeight());
            return HashUtil.sha3(ByteUtil.merge(b1, b2, header.getState()));
        }).collect(Collectors.toList());
        return MerkleTree.buildMerkleTree(leaves);
    }

    public MerkleProof getMerkleProof(int chainId, long height) {
        if (headerStates == null) {
            recoverHeaderState();
        }
        int pos = 0;
        for(int i = 0; i < headerStates.size(); i++) {
            if (headerStates.get(i).getChainId() == chainId && headerStates.get(i).getHeight() == height) {
                pos = i;
            }
        }
        ArrayList<byte[]> leaves = (ArrayList<byte[]>) this.headerStates.stream().map((header)->{
            byte[] b1 = ByteUtil.shortToBytes((short) header.getChainId());
            byte[] b2 = ByteUtil.longToBytes(header.getHeight());
            return HashUtil.sha3(ByteUtil.merge(b1, b2, header.getState()));
        }).collect(Collectors.toList());
        return MerkleTree.getProof(leaves, pos);
    }


    @Override
    protected byte[] rlpEncoded() {
        return new byte[0];
    }

    @Override
    protected void rlpDecoded() {

    }

    public Long getEpoch() {
        return epoch;
    }

    public Long getHeight() {
        return height;
    }

    public Integer getTxNums() {
        return txNums;
    }

    public byte[] getRoot() {
        return root;
    }

    public byte[] getData() {
        return data;
    }

    public BigInteger getSignerSet() {
        return signerSet;
    }
}
