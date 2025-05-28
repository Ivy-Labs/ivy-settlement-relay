package org.ivy.settlement.relay.watchdog.checker;

public enum FieldType {
    BOOLEAN(0,"boolean"),
    UINT256(1,"uint256/uint"),
    STRING(2,"string"),
    ADDRESS(3,"address"),
    BYTES(4,"btyes"),
    BYTES32(5,"bytes32");

    private Integer id;
    private String type;

    FieldType(Integer id, String type) {
        this.id = id;
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public String getType() {
        return type;
    }
}
