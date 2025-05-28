package org.ivy.settlement.relay.watchdog.interactive;

import org.ivy.settlement.infrastructure.crypto.key.asymmetric.SecureKey;
import org.web3j.abi.datatypes.Function;

import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * description:
 * <p>
 * Author lyy
 */
public class InteractiveInstanceScheduler {

    SecureKey key;

    public long delay;

    Map<String, ScheduledFuture> taskMap;

    ScheduledExecutorService scheduledExecutorService;

    public void schedule(InteractiveInstance instance) {
        if (instance.getFunction().isPresent()) {
            if (!taskMap.containsKey(instance.getId())) {
                final var fun = (Function) instance.getFunction().get();
                var futureTask = scheduledExecutorService.scheduleAtFixedRate(new Runnable() {
                    @Override
                    public void run() {
                        send(fun);
                    }
                }, 0, delay, TimeUnit.SECONDS);

                taskMap.put(instance.getId(), futureTask);
            }
        } else {
            cancel(instance);
        }
    }

    public void cancel(InteractiveInstance instance) {
        var futureTask = taskMap.remove(instance.getId());
        if (futureTask != null) {
            futureTask.cancel(true);
        }
    }

    private void send(Function fun) {

    }

    public void clear() {
        this.taskMap.values().forEach(t -> t.cancel(true));
        this.taskMap.clear();
    }
}
