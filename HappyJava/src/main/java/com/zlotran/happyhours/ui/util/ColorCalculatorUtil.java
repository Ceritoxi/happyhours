package com.zlotran.happyhours.ui.util;

import java.awt.Color;

public class ColorCalculatorUtil {

    public static Color calcColor(int barProgress, int peakYellow, int minBarProgress, int maxBarProgress) {
        Color color;
        if (barProgress < peakYellow) {
            color = new Color(255, (int) (((barProgress - minBarProgress) / ((double)peakYellow - minBarProgress)) * (255 - 100)), 0);
        } else if (barProgress <= maxBarProgress) {
            color = new Color(255 - (int) (((barProgress - (double)peakYellow) / (maxBarProgress - (double)peakYellow)) * 255), 255 - 100, 0);
        } else {
            color = new Color(0, 255 - 100, 0);
        }
        return color;
    }
}
