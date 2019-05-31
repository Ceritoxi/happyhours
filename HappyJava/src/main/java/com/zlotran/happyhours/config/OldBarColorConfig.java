package com.zlotran.happyhours.config;

import com.zlotran.happyhours.config.fallback.FallbackOldBarColorConfig;

@Deprecated
public final class OldBarColorConfig extends Config{

    private static final int COLOR_LIMIT = 256;
    private volatile static OldBarColorConfig oldBarColorConfig;
    private static final String CONFIG_FILE_NAME = CONFIG_ROOT + "old_bar_color.cfg";

    @Deprecated
    private OldBarColorConfig() {
        super(CONFIG_FILE_NAME, FallbackOldBarColorConfig.getFallbackBarColorConfig());
    }

    @Deprecated
    public static OldBarColorConfig getInstance() {
        if (oldBarColorConfig == null) {
            synchronized (OldBarColorConfig.class) {
                if (oldBarColorConfig == null) {
                    oldBarColorConfig = new OldBarColorConfig();
                }
            }
        }
        return oldBarColorConfig;
    }

    @Override
    @Deprecated
    public Integer getNumericConfig(final String key) {
        return super.getNumericConfig(key) % COLOR_LIMIT;
    }
}
