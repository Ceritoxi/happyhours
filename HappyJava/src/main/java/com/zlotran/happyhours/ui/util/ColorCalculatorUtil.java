package com.zlotran.happyhours.ui.util;

import java.awt.Color;

import com.zlotran.happyhours.config.OldBarColorConfig;

/**
 * I don't like this, but whatevs
 */
@Deprecated
public class ColorCalculatorUtil {

    private static final int PEAK_YELLOW = 28800;
    private static final int PEAK = 30600;

    public static Color calcColor(int barProgress) {
        if (barProgress < (PEAK_YELLOW / 4)) {
            return new Color(getRedPhase(1), getGreenPhase(1), getBluePhase(1));
        } else if(barProgress < (PEAK_YELLOW / 4) * 2) {
            return new Color(getRedPhase(2), getGreenPhase(2), getBluePhase(2));
        } else if(barProgress < (PEAK_YELLOW / 4) * 3) {
            return new Color(getRedPhase(3), getGreenPhase(3), getBluePhase(3));
        } else if(barProgress < PEAK_YELLOW) {
            return new Color(getRedPhase(4), getGreenPhase(4), getBluePhase(4));
        } else if(barProgress < PEAK_YELLOW + (PEAK - PEAK_YELLOW) / 3) {
            return new Color(getRedPhase(5), getGreenPhase(5), getBluePhase(5));
        } else if(barProgress < PEAK_YELLOW + ((PEAK - PEAK_YELLOW) / 3) * 2) {
            return new Color(getRedPhase(6), getGreenPhase(6), getBluePhase(6));
        } else {
            return new Color(getRedPhase(7), getGreenPhase(7), getBluePhase(7));
        }
    }

    private static int getRedPhase(int phase) {
        return OldBarColorConfig.getInstance().getNumericConfig("colorchange.phase." + phase + ".red");
    }

    private static int getGreenPhase(int phase) {
        return OldBarColorConfig.getInstance().getNumericConfig("colorchange.phase." + phase + ".green");
    }

    private static int getBluePhase(int phase) {
        return OldBarColorConfig.getInstance().getNumericConfig("colorchange.phase." + phase + ".blue");
    }
}
