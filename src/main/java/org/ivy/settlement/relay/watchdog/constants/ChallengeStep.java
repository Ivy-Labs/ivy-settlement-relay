package org.ivy.settlement.relay.watchdog.constants;

public enum ChallengeStep {
    PUBLISH1(0x00010000L, "publish1"),
    PUBLISH2(0x00020000L, "publish2"),
    CHALLENGE(0x00040000L,"challenge"),
    BISECT(0x00080000L,"bisect"),
    CHOOSE(0x00100000L,"choose"),
    END(0x00200000L,"end");

    private Long step;

    private String stepName;

    ChallengeStep(Long step, String stepName) {
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
