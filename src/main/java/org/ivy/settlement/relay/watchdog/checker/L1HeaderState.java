package org.ivy.settlement.relay.watchdog.checker;

import org.ivy.settlement.infrastructure.codec.borsh.Borsh;

import java.util.ArrayList;

public class L1HeaderState implements Borsh {
    Long height;
    ArrayList<Byte> headerState; // size-32
}
