package org.ivy.settlement.relay.watchdog.checker.bsc;

import org.ivy.settlement.infrastructure.codec.borsh.Borsh;
import org.ivy.settlement.relay.watchdog.checker.EVMHeader;

import java.util.ArrayList;

public class Header implements Borsh {
    EVMHeader header;
    ArrayList<Byte> latestEpoch; // size-32
    ArrayList<Byte> preEpoch; // size-32
    Long latestNums;
    Long preNums;

}
