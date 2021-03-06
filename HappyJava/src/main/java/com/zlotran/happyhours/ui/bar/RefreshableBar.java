package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.config.BarColorConfig;
import com.zlotran.happyhours.config.BarConfig;
import com.zlotran.happyhours.config.GeneralConfig;
import com.zlotran.happyhours.ui.refresher.Refresher;

public abstract class RefreshableBar extends JProgressBar {

    private static final boolean STRING_PAINTED = true;
    String label;
    int barProgress;
    private Refresher refresher;
    static final int SCREEN_FRAME_WIDTH = GeneralConfig.getInstance().getNumericConfig("screen.width");
    static final int SCREEN_FRAME_HEIGHT = GeneralConfig.getInstance().getNumericConfig("screen.height");
    static final int FROM = 0;
    static final int TO = 1;
    static final int BAR_WIDTH = BarConfig.getInstance().getNumericConfig("default.width");
    static final int BAR_HEIGHT = BarConfig.getInstance().getNumericConfig("default.height");
    private static final Color DEFAULT_COLOR = new Color(BarColorConfig.getInstance().getNumericConfig("color.default.red"), BarColorConfig.getInstance().getNumericConfig("color.default.green"), BarColorConfig
        .getInstance().getNumericConfig("color.default.blue"));

    RefreshableBar(final Refresher refresher) {
        super();
        this.refresher = refresher;
        this.setForeground(DEFAULT_COLOR);
        this.setStringPainted(STRING_PAINTED);
    }

    public void refresh() {
        label = refresher.labelRefresh();
        barProgress = refresher.progressRefresh();
    }

    public void setRefresher(Refresher refresher) {
        this.refresher = refresher;
    }
}
