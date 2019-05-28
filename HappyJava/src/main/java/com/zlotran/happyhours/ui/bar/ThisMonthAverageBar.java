package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.refresher.LabelRefresher;

public class ThisMonthAverageBar extends RefreshableBar {

    private static final int FROM = 0;
    private static final int TO = 30600;
    private static final int BAR_WIDTH = 350;
    private static final int BAR_HEIGHT = 20;

    public ThisMonthAverageBar(int frameHeight, int frameWidth, LabelRefresher labelRefresher) {
        super(FROM, TO, labelRefresher);
        this.setBounds(frameWidth - (40 + BAR_WIDTH), (2 * (frameHeight / 3)) - (BAR_HEIGHT / 2) - ((frameHeight / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(FROM);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString("This month average:\t" + label);
        this.setValue(barProgress);
    }
}
