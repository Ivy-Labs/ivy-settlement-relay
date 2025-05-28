package org.ivy.settlement.relay.watchdog.checker.arbitrum;

public enum SubStep {
    ARB_HEADER_PUBLISH(0x00000001L, "arb_header_publish"),
    ARB_CONFIRM_PUBLISH(0x00000002L, "arb_confirm_publish");

    private final Long step;

    private final String stepName;

    SubStep(Long step, String stepName) {
        this.step = step;
        this.stepName = stepName;
    }

    public Long getStep() {
        return step;
    }

    public String getStepName() {
        return stepName;
    }
}
