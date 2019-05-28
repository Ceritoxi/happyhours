package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.refresher.LabelRefresher;

public class ThisMonthTotalBar extends RefreshableBar {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int BAR_WIDTH = 350;
    private static final int BAR_HEIGHT = 20;

    public ThisMonthTotalBar(int frameHeight, int frameWidth, LabelRefresher labelRefresher) {
        super(FROM, TO, labelRefresher);
        this.setBounds(20, (2 * (frameHeight / 3)) - (BAR_HEIGHT / 2) - ((frameHeight / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(TO);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString("This month total:\t" + label);
        this.setValue(barProgress);
    }
}
