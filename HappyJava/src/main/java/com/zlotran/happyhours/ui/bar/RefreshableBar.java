package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.config.GeneralConfig;
import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public abstract class RefreshableBar extends JProgressBar {

    String label;
    int barProgress;
    Color color;
    private LabelRefresher labelRefresher;
    static final int SCREEN_FRAME_WIDTH = GeneralConfig.getInstance().getNumericConfig("screen.width");
    static final int SCREEN_FRAME_HEIGHT = GeneralConfig.getInstance().getNumericConfig("screen.height");
    static final int FROM = 0;
    static final int TO = 1;
    private static final double PEAK_YELLOW = 0.8d;
    static final int BAR_WIDTH = 350;
    static final int BAR_HEIGHT = 20;
    static final String LABEL_PREFIX = "Change me:\t";

    RefreshableBar(int from, int to, LabelRefresher labelRefresher) {
        super(from, to);
        this.labelRefresher = labelRefresher;
    }

    public void refresh() {
        label = labelRefresher.labelRefresh();
        barProgress = labelRefresher.progressRefresh();
        this.color = calcColor();
    }
    //SZARAZEGÉSZ
    private Color calcColor() {
        Color color;
        if (barProgress < PEAK_YELLOW) {
            color = new Color(255,(int)(((barProgress - FROM) / (PEAK_YELLOW - FROM)) * (255 - 50)),0);
        } else if (barProgress <= TO) {
            color = new Color(255 - (int)(((barProgress - PEAK_YELLOW) / (TO - PEAK_YELLOW)) * 255),255 -50,0 );
        } else {
            color = new Color(0,255-50,0 );
        }
        return color;
    }
}
