package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public class ThisMonthTotalBar extends RefreshableBar {

    private static final String LABEL_PREFIX = "This month total:\t";

    public ThisMonthTotalBar(LabelRefresher labelRefresher) {
        super(FROM, TO, labelRefresher);
        this.setBounds(20, 2 * (SCREEN_FRAME_HEIGHT / 3) - (BAR_HEIGHT / 2) - ((SCREEN_FRAME_HEIGHT / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(TO);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(LABEL_PREFIX + label);
        this.setValue(barProgress);
        this.setForeground(color);
    }
}
