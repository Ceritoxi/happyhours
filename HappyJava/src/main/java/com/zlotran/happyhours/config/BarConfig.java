package com.zlotran.happyhours.config;

import com.zlotran.happyhours.config.fallback.FallbackBarConfig;

public class BarConfig extends Config {

    private volatile static BarConfig barConfig;
    private static final String CONFIG_FILE_NAME = CONFIG_ROOT + "bar.cfg";

    private BarConfig() {
        super(CONFIG_FILE_NAME, FallbackBarConfig.getFallbackBarConfig());
    }

    public static BarConfig getInstance() {
        if (barConfig == null) {
            synchronized (BarConfig.class) {
                if (barConfig == null) {
                    barConfig = new BarConfig();
                }
            }
        }
        return barConfig;
    }

}
