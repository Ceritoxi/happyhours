package com.zlotran.happyhours.ui.util;

import java.awt.Color;

import com.zlotran.happyhours.config.BarColorConfig;

public class ColorCalculatorUtil {

    private static final int COLOR_LIMIT = 256;

    public static Color calcColor(int barProgress, int peakMidPhase, int minBarProgress, int maxBarProgress) {
        Color color;
        if (barProgress < peakMidPhase) {
            color = new Color(getConfig("first.mid.redness"), (int) (((barProgress - minBarProgress) / ((double) peakMidPhase - minBarProgress)) * (getConfig("first.greenness") - getConfig("green.depth"))),
                getConfig("first.blueness"));
        } else if (barProgress <= maxBarProgress) {
            color = new Color(getConfig("first.mid.redness") - (int) (((barProgress - (double) peakMidPhase) / (maxBarProgress - (double) peakMidPhase)) * getConfig("first.mid.redness")),
                getConfig("mid.greenness") - getConfig("green.depth"),
                getConfig("mid.blueness"));
        } else {
            color = new Color(getConfig("last.redness"), getConfig("last.greenness") - getConfig("green.depth"), getConfig("last.blueness"));
        }
        return color;
    }

    private static int getConfig(String s) {
        return BarColorConfig.getInstance().getNumericConfig(s) % COLOR_LIMIT;
    }
}
