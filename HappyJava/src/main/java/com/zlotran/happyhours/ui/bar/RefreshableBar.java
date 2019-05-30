package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.config.GeneralConfig;
import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public abstract class RefreshableBar extends JProgressBar {

    String label;
    int barProgress;
    protected LabelRefresher labelRefresher;
    static final int SCREEN_FRAME_WIDTH = GeneralConfig.getInstance().getNumericConfig("screen.width");
    static final int SCREEN_FRAME_HEIGHT = GeneralConfig.getInstance().getNumericConfig("screen.height");
    static final int FROM = 0;
    static final int TO = 1;
    static final int BAR_WIDTH = 350;
    static final int BAR_HEIGHT = 20;
    static final Color DEFAULT_COLOR = new Color(37, 40, 198);
    static final String LABEL_PREFIX = "Change me:\t";

    RefreshableBar(LabelRefresher labelRefresher) {
        super();
        this.labelRefresher = labelRefresher;
        this.setForeground(DEFAULT_COLOR);
    }

    public void refresh() {
        label = labelRefresher.labelRefresh();
        barProgress = labelRefresher.progressRefresh();
    }

}
