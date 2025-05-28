package org.ivy.settlement.relay.watchdog.checker.arbitrum;

import org.ivy.settlement.infrastructure.codec.borsh.Borsh;
import org.ivy.settlement.relay.watchdog.checker.EVMHeader;
import org.ivy.settlement.relay.watchdog.checker.L1HeaderState;

public class Header implements Borsh {
    Rollup rollup;
    EVMHeader l2Header;
    L1HeaderState l1Header;

}
