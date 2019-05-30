package com.zlotran.happyhours.ui.util;

import java.awt.Color;

import com.zlotran.happyhours.config.BarColorConfig;

public class ColorCalculatorUtil {

    private static final int GREEN_DEPTH = BarColorConfig.getInstance().getNumericConfig("green.depth") % 256;
    private static final int LAST_PHASE_BLUENESS = BarColorConfig.getInstance().getNumericConfig("last.blueness") % 256;
    private static final int LAST_PHASE_GREENNESS = BarColorConfig.getInstance().getNumericConfig("last.greenness") % 256;
    private static final int LAST_PHASE_REDNESS = BarColorConfig.getInstance().getNumericConfig("last.redness") % 256;
    private static final int MID_PHASE_GREENNESS = BarColorConfig.getInstance().getNumericConfig("mid.greenness") % 256;
    private static final int MID_PHASE_BLUENESS = BarColorConfig.getInstance().getNumericConfig("mid.blueness") % 256;
    private static final int FIRST_PHASE_BLUENESS = BarColorConfig.getInstance().getNumericConfig("first.blueness") % 256;
    private static final int FIRST_PHASE_REDNESS = BarColorConfig.getInstance().getNumericConfig("first.redness") % 256;

    public static Color calcColor(int barProgress, int peakMidPhase, int minBarProgress, int maxBarProgress) {
        Color color;
        if (barProgress < peakMidPhase) {
            color = new Color(FIRST_PHASE_REDNESS, (int) (((barProgress - minBarProgress) / ((double) peakMidPhase - minBarProgress)) * (255 - GREEN_DEPTH)),
                FIRST_PHASE_BLUENESS);
        } else if (barProgress <= maxBarProgress) {
            color = new Color(255 - (int) (((barProgress - (double) peakMidPhase) / (maxBarProgress - (double) peakMidPhase)) * 255),
                MID_PHASE_GREENNESS - GREEN_DEPTH,
                MID_PHASE_BLUENESS);
        } else {
            color = new Color(LAST_PHASE_REDNESS, LAST_PHASE_GREENNESS - GREEN_DEPTH, LAST_PHASE_BLUENESS);
        }
        return color;
    }
}
