package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.config.BarConfig;
import com.zlotran.happyhours.ui.refresher.Refresher;

public class TodaysTotalBar extends ColorChangerRefreshableBar {

    private static final int TO = BarConfig.getInstance().getNumericConfig("max.insec");
    private static final String LABEL_PREFIX = "Today total:\t";
    private static final int BAR_WIDTH = 740;
    private static final int BAR_HEIGHT = 40;

    public TodaysTotalBar(final Refresher refresher) {
        super(refresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(SCREEN_FRAME_WIDTH / 2 - BAR_WIDTH / 2 - 10, (SCREEN_FRAME_HEIGHT / 3) - BAR_HEIGHT / 2 - ((SCREEN_FRAME_HEIGHT / 3) / 2),
            BAR_WIDTH,
            BAR_HEIGHT);
        this.setValue(FROM);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(LABEL_PREFIX + label);
        this.setValue(barProgress);
    }
}
