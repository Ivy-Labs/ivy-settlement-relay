package org.ivy.settlement.relay.watchdog.constants;


public enum ChallengeType {
    REPORTFAKE(0,"reportFake"),
    REPORTLACK(1,"reportLack"),
    REPORTFAULTY(2, "reportFaulty"),
    REPORTORDER(3,"reportOrder"),
    REPORTBLOCKLIVENESS(4, "reportBlockLiveness"),
    REPORTTXLIVENESS(5, "reportTxLiveness");


    ChallengeType(Integer type, String typeName) {
        this.type = type;
        this.typeName = typeName;
    }

    private Integer type;

    private String typeName;

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }

    public static ChallengeType findByType(Integer id) {
        for (var t: ChallengeType.values()){
            if (id.equals(t.type)) {
                return t;
            }
        }
        throw new RuntimeException("No such challengeType");
    }
}
