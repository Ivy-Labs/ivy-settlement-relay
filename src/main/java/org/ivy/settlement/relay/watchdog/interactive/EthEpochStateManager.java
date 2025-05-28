package org.ivy.settlement.relay.watchdog.interactive;

import org.apache.commons.lang3.tuple.Pair;
import org.ivy.settlement.ethereum.model.event.EpochChangeEvent;
import org.ivy.settlement.ethereum.model.event.VoterUpdateEvent;
import org.ivy.settlement.infrastructure.datasource.model.EthLogEvent;
import org.ivy.settlement.relay.watchdog.model.EpochState;

import java.util.List;
import java.util.Optional;

/**
 * description:
 * <p>
 * Author lyy
 */
public class EthEpochStateManager {

    private EpochState currentEpochState;

    private EpochState nextEpochState;

    private boolean dirty;

    public EthEpochStateManager(EpochState currentEpochState, EpochState nextEpochState) {
        this.currentEpochState = currentEpochState;
        this.nextEpochState = nextEpochState;
    }

    void apply(EthLogEvent event) {
        this.dirty = true;
        if (event instanceof VoterUpdateEvent voterUpdateEvent) {
            this.currentEpochState.updateValidators(List.of(voterUpdateEvent));
            this.nextEpochState.updateValidators(List.of(voterUpdateEvent));
        } else if (event instanceof EpochChangeEvent) {
            this.currentEpochState = nextEpochState;
            this.nextEpochState = this.nextEpochState.copyForNext(true);
        }
    }

    void reset(EpochState currentEpochState, EpochState nextEpochState) {
        this.currentEpochState = currentEpochState;
        this.nextEpochState = nextEpochState;
    }

    Pair<Optional<EpochState>, Optional<EpochState>> export() {
        try {
            if (dirty) {
                return Pair.of(Optional.of(this.currentEpochState), Optional.of(this.nextEpochState));

            } else {
                return Pair.of(Optional.empty(), Optional.empty());
            }
        } finally {
            this.dirty = false;
        }
    }

    public byte[] getVoter(int slot) {
        return this.currentEpochState.getPublishKey(slot);
    }
}
