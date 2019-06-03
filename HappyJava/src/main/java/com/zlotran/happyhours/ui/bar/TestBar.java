package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import com.zlotran.happyhours.ui.refresher.Refresher;
import com.zlotran.happyhours.ui.util.MichaelColorCalculatorUtil;

@Deprecated
public class TestBar extends ColorChangerRefreshableBar {

    private static final int TO = 30_600;
    private int i = 0;

    public TestBar(final Refresher refresher) {
        super(refresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(SCREEN_FRAME_WIDTH / 2 - BAR_WIDTH / 2, 3 * (SCREEN_FRAME_HEIGHT / 3) - BAR_HEIGHT / 2 - ((SCREEN_FRAME_HEIGHT / 3) / 2) - 50,
            BAR_WIDTH,
            BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(FROM);
    }

    @Override
    public void refresh() {
        barProgress = ++i;
        if (barProgress > 32_000) {
            i = 0;
        }
        final Color color = MichaelColorCalculatorUtil.calcColor(barProgress, 28_800, getMinimum(), getMaximum());
        this.setString(color.toString() + " " + barProgress);
        this.setForeground(color);
        this.setValue(i);
    }
}
