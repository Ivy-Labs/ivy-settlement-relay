package org.ivy.settlement.relay.watchdog.checker;

import org.ivy.settlement.infrastructure.codec.borsh.Borsh;

import java.util.ArrayList;

public class EVMHeader implements Borsh {
    ArrayList<Byte> parentHash; // size-32
    ArrayList<Byte> uncleHash; // size-32
    ArrayList<Byte> coinbase; // size-32
    ArrayList<Byte> root; // size-32
    ArrayList<Byte> txHash; // size-32
    ArrayList<Byte> receiptRoot; // size-32
    ArrayList<Byte> bloom; // size-256
    Long difficulty;
    Long number;
    Long gasLimit;
    Long gasUsed;
    Long time;
    ArrayList<Byte> extra; // size-dynamic
    ArrayList<Byte> mixDigest;  // size-32
    ArrayList<Byte> nonce; // size-8
    ArrayList<ExtraFields> extraFields;
}
