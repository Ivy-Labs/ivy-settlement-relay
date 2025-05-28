package org.ivy.settlement.relay.watchdog.checker;


import org.ivy.settlement.infrastructure.codec.borsh.Borsh;

import java.util.ArrayList;

public class ExtraFields implements Borsh {
    /**
     *     enum Type{
     *         BOOLEAN,
     *         UINT256,
     *         STRING,
     *         ADDRESS,
     *         BYTES,
     *         BYTES32
     *     }
     */
    Byte type;
    ArrayList<Byte> value;
}
