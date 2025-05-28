package org.ivy.settlement.relay.watchdog;


import org.ivy.settlement.ethereum.model.constants.ChainType;
import org.ivy.settlement.infrastructure.datasource.model.Persistable;
import org.ivy.settlement.infrastructure.datasource.rocksdb.RocksDbSource;
import org.ivy.settlement.relay.watchdog.checker.Checker;
import org.ivy.settlement.relay.watchdog.state.log.StateBuilder;

import java.util.HashMap;
import java.util.Map;

public class WatchDog {


    public final String DB_PREFIX= "watchdog";

    protected Checker logChecker;

    protected CheckerManager checkerManager;

    protected ChainType chainType;

    StateBuilder stateBuilder;

    Dog ivyNetworkDog;

    Dog safetyDog;

    Dog livenessDog;

    Dog challengeDog;


    public WatchDog(ChainType chain, RocksDbSource dbSource){
        Map<String, Class<? extends Persistable>> columnFamilies = new HashMap<>();
//        columnFamilies.put()
//        this.dbSource = new RocksDbSource("watch_dog",);
        this.chainType = chain;

    }


    private void watchProtocol() {
        while (true) {
            try {

                stateBuilder.run();

                ivyNetworkDog.run();

                safetyDog.run();

                livenessDog.run();

                challengeDog.run();

                Thread.sleep(200);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
