package com.zlotran.happyhours.config;

import com.zlotran.happyhours.config.fallback.FallbackBarColorConfig;

public class BarColorConfig extends Config{

    private volatile static BarColorConfig barColorConfig;
    private static final String CONFIG_FILE_NAME = CONFIG_ROOT + "bar_color.cfg";


    private BarColorConfig() {
        super(CONFIG_FILE_NAME, FallbackBarColorConfig.getFallbackBarColorConfig());
    }

    public static BarColorConfig getInstance() {
        if (barColorConfig == null) {
            synchronized (BarColorConfig.class) {
                if (barColorConfig == null) {
                    barColorConfig = new BarColorConfig();
                }
            }
        }
        return barColorConfig;
    }
}
