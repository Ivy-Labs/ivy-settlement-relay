package org.ivy.settlement.relay.watchdog.checker.arbitrum;

public enum DisputeIndex {
    ARBITRUM_BLOCK(0x0000L, "arbitrum_block"),
    ROLLUP_BLOCK(0x0001L, "rollup_block"),
    CONFIRM_ON_ETH(0x0002L, "confirm_on_eth");

    private Long index;

    private String indexName;

    DisputeIndex(Long index, String indexName) {
        this.index = index;
        this.indexName = indexName;
    }

    public Long getIndex() {
        return index;
    }

    public String getIndexName() {
        return indexName;
    }
}
