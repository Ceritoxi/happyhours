package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.config.BarConfig;
import com.zlotran.happyhours.ui.refresher.Refresher;

public class MonthAverageBar extends ColorChangerRefreshableBar {

    private static final int TO = BarConfig.getInstance().getNumericConfig("max.insec");
    private static final String LABEL_PREFIX = "Month average:\t";

    public MonthAverageBar(final Refresher refresher) {
        super(refresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(SCREEN_FRAME_WIDTH - (40 + BAR_WIDTH), 2 * (SCREEN_FRAME_HEIGHT / 3) - BAR_HEIGHT / 2 - ((SCREEN_FRAME_HEIGHT / 3) / 2),
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
