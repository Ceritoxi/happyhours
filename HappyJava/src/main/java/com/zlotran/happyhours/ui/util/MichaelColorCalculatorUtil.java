package com.zlotran.happyhours.ui.util;

import java.awt.Color;

import com.zlotran.happyhours.config.BarColorConfig;
import com.zlotran.happyhours.config.OldBarColorConfig;

public class MichaelColorCalculatorUtil {

    public static Color calcColor(int barProgress, int peakMidPhase, int minBarProgress, int maxBarProgress) {
        Color color;
        if(barProgress < peakMidPhase) {
            color = new Color(
                (int)(getConfig("first.redness") + (getConfig("mid.redness") - getConfig("first.redness")) * ((double)(barProgress - minBarProgress) / (double)(peakMidPhase - minBarProgress))),
                (int)(getConfig("first.greenness") + (getConfig("mid.greenness") - getConfig("first.greenness")) * ((double)(barProgress - minBarProgress) / (double)(peakMidPhase - minBarProgress))),
                (int)(getConfig("first.blueness") + (getConfig("mid.blueness") - getConfig("first.blueness")) * ((double)(barProgress - minBarProgress) / (double)(peakMidPhase - minBarProgress)))
            );
        }
        else if(barProgress < maxBarProgress) {
            color = new Color(
                (int)(getConfig("mid.redness") + (getConfig("last.redness") - getConfig("mid.redness")) * ((double)(barProgress - peakMidPhase) / (double)(maxBarProgress - peakMidPhase))),
                (int)(getConfig("mid.greenness") + (getConfig("last.greenness") - getConfig("mid.greenness")) * ((double)(barProgress - peakMidPhase) / (double)(maxBarProgress - peakMidPhase))),
                (int)(getConfig("mid.blueness") + (getConfig("last.blueness") - getConfig("mid.blueness")) * ((double)(barProgress - peakMidPhase) / (double)(maxBarProgress - peakMidPhase)))
            );
        }
        else {
            color = new Color(
                getConfig("last.redness"),
                getConfig("last.greenness"),
                getConfig("last.blueness")
            );
        }
        return color;
    }
	
	

    private static int getConfig(String s) {
        return BarColorConfig.getInstance().getNumericConfig(s);
    }
}
