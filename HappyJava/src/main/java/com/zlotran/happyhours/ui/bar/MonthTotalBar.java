package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.Refresher;

public class MonthTotalBar extends RefreshableBar {

    private static final String LABEL_PREFIX = "Month total:\t";

    public MonthTotalBar(final Refresher refresher) {
        super(refresher);
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
