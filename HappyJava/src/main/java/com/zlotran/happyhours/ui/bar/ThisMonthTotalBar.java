package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public class ThisMonthTotalBar extends RefreshableBar {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int DEFAULT_BAR_WIDTH = 350;
    private static final int DEFAULT_BAR_HEIGHT = 20;
    private static final String DEFAULT_LABEL_PREFIX = "This month total:\t";

    public ThisMonthTotalBar(int frameHeight, int frameWidth, LabelRefresher labelRefresher) {
        super(FROM, TO, labelRefresher);
        this.setBounds(20, (2 * (frameHeight / 3)) - (DEFAULT_BAR_HEIGHT / 2) - ((frameHeight / 3) / 2), DEFAULT_BAR_WIDTH, DEFAULT_BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(TO);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(DEFAULT_LABEL_PREFIX + label);
        this.setValue(barProgress);
    }
}
