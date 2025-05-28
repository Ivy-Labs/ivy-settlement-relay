package org.ivy.settlement.relay.watchdog.checker.arbitrum;

import org.ivy.settlement.infrastructure.codec.borsh.Borsh;

import java.util.ArrayList;

public class Rollup implements Borsh {
    Long l2SeqNumber;
    Long height;
    ArrayList<Byte> blockHash;
    ArrayList<Byte> sendRoot;
}
