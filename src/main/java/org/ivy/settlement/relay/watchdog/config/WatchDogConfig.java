package org.ivy.settlement.relay.watchdog.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class WatchDogConfig {

    private static final Logger logger = LoggerFactory.getLogger("watchdog");
    private static final String WATCHDOG_CONFIG_FILE = "/watchDogConfig.properties";
    private static final String DEFAULT_WATCHER_CONFIG_FILE = "defaultWatchDogConfig.properties";
    private static Properties WATCHDOG_CONFIG = null;
    static {
        loadWatchDogConfigFile();
    }

    public static synchronized void loadWatchDogConfigFile() {

    }

    public static String getConfigByKey(String key) {
        return "";
    }

}
