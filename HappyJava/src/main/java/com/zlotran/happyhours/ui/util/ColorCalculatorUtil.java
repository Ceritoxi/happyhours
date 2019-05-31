package com.zlotran.happyhours.ui.util;

import java.awt.Color;

import com.zlotran.happyhours.config.BarColorConfig;

public class ColorCalculatorUtil {


    private static final int COLOR_LIMIT = 256;

    private static final int RED_START = getConfig("first.redness");
    private static final int GRE_START = getConfig("first.greenness");
    private static final int BLU_START = getConfig("first.blueness");
    private static final int RED_MID = getConfig("mid.redness");
    private static final int GRE_MID = getConfig("mid.greenness");
    private static final int BLU_MID = getConfig("mid.blueness");
    private static final int RED_END = getConfig("last.redness");
    private static final int GRE_END = getConfig("last.greenness");
    private static final int BLU_END = getConfig("last.blueness");

    public static Color calcColor(int barProgress, int peakMidPhase, int minBarProgress, int maxBarProgress) {
        Color color;
        if(barProgress < peakMidPhase) {
            color = new Color(
                (int)(RED_START + (RED_MID - RED_START) * ((double)(barProgress - minBarProgress) / (double)(peakMidPhase - minBarProgress))),
                (int)(GRE_START + (GRE_MID - GRE_START) * ((double)(barProgress - minBarProgress) / (double)(peakMidPhase - minBarProgress))),
                (int)(BLU_START + (BLU_MID - BLU_START) * ((double)(barProgress - minBarProgress) / (double)(peakMidPhase - minBarProgress)))
            );
        }
        else if(barProgress < maxBarProgress) {
            color = new Color(
                (int)(RED_MID + (RED_END - RED_MID) * ((double)(barProgress - peakMidPhase) / (double)(maxBarProgress - peakMidPhase))),
                (int)(GRE_MID + (GRE_END - GRE_MID) * ((double)(barProgress - peakMidPhase) / (double)(maxBarProgress - peakMidPhase))),
                (int)(BLU_MID + (BLU_END - BLU_MID) * ((double)(barProgress - peakMidPhase) / (double)(maxBarProgress - peakMidPhase)))
            );
        }
        else {
            color = new Color(
                RED_END,
                GRE_END,
                BLU_END
            );
        }
        return color;
    }
	
	

    private static int getConfig(String s) {
        return BarColorConfig.getInstance().getNumericConfig(s) % COLOR_LIMIT;
    }
}
