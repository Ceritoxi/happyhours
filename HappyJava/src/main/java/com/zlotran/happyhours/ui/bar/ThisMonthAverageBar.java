package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public class ThisMonthAverageBar extends ColorChangerRefreshableBar {

    private static final int TO = 30600;
    private static final String LABEL_PREFIX = "This month average:\t";

    public ThisMonthAverageBar(LabelRefresher labelRefresher) {
        super(labelRefresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(SCREEN_FRAME_WIDTH - (40 + BAR_WIDTH), (2 * (SCREEN_FRAME_HEIGHT / 3)) - (BAR_HEIGHT / 2) - ((SCREEN_FRAME_HEIGHT / 3) / 2),
            BAR_WIDTH,
            BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(FROM);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(LABEL_PREFIX + label);
        this.setValue(barProgress);
    }
}
