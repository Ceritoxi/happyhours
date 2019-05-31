package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public class ThisMonthTotalBar extends RefreshableBar {

    private static final String LABEL_PREFIX = "This month total:\t";

    public ThisMonthTotalBar(final LabelRefresher labelRefresher) {
        super(labelRefresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(20, 2 * (SCREEN_FRAME_HEIGHT / 3) - BAR_HEIGHT / 2 - ((SCREEN_FRAME_HEIGHT / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setValue(TO);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(LABEL_PREFIX + label);
        this.setValue(barProgress);
    }
}
