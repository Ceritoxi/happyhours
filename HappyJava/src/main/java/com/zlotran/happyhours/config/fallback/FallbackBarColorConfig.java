package com.zlotran.happyhours.config.fallback;

import java.util.HashMap;
import java.util.Map;

public class FallbackBarColorConfig {

    private static final String GREEN_DEPTH_KEY = "green.depth";
    private static final String LAST_PHASE_REDNESS_KEY = "last.redness";
    private static final String LAST_PHASE_GREENNESS_KEY = "last.greenness";
    private static final String LAST_PHASE_BLUENESS_KEY = "last.blueness";
    private static final String MID_PHASE_GREENNESS_KEY = "mid.greenness";
    private static final String MID_PHASE_BLUENESS_KEY = "mid.blueness";
    private static final String FIRST_MID_PHASE_REDNESS_KEY = "first.mid.redness";
    private static final String FIRST_PHASE_GREENNESS_KEY = "first.mid.redness";
    private static final String FIRST_PHASE_BLUENESS_KEY = "first.blueness";

    private static final String GREEN_DEPTH_VALUE = "100";
    private static final String LAST_PHASE_REDNESS_VALUE = "0";
    private static final String LAST_PHASE_GREENNESS_VALUE = "255";
    private static final String LAST_PHASE_BLUENESS_VALUE = "0";
    private static final String MID_PHASE_GREENNESS_VALUE = "255";
    private static final String MID_PHASE_BLUENESS_VALUE = "0";
    private static final String FIRST_MID_PHASE_REDNESS_VALUE = "255";
    private static final String FIRST_PHASE_GREENNESS_VALUE = "255";
    private static final String FIRST_PHASE_BLUENESS_VALUE = "0";

    public static Map<String, String> getFallbackBarColorConfig() {
        return assemblyFallbackConfig();
    }

    private static Map<String, String> assemblyFallbackConfig() {
        Map<String, String> result = new HashMap<>();
        result.put(GREEN_DEPTH_KEY, GREEN_DEPTH_VALUE);
        result.put(LAST_PHASE_REDNESS_KEY, LAST_PHASE_REDNESS_VALUE);
        result.put(LAST_PHASE_GREENNESS_KEY, LAST_PHASE_GREENNESS_VALUE);
        result.put(LAST_PHASE_BLUENESS_KEY, LAST_PHASE_BLUENESS_VALUE);
        result.put(MID_PHASE_GREENNESS_KEY, MID_PHASE_GREENNESS_VALUE);
        result.put(MID_PHASE_BLUENESS_KEY, MID_PHASE_BLUENESS_VALUE);
        result.put(FIRST_MID_PHASE_REDNESS_KEY, FIRST_MID_PHASE_REDNESS_VALUE);
        result.put(FIRST_PHASE_GREENNESS_KEY, FIRST_PHASE_GREENNESS_VALUE);
        result.put(FIRST_PHASE_BLUENESS_KEY, FIRST_PHASE_BLUENESS_VALUE);
        return result;
    }
}
