package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public class AllTimeAverageBar extends RefreshableBar {

    private static final int TO = 30600;

    private static final String LABEL_PREFIX = "All time average:\t";

    public AllTimeAverageBar(LabelRefresher labelRefresher) {
        super(labelRefresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(SCREEN_FRAME_WIDTH - (40 + BAR_WIDTH), (SCREEN_FRAME_HEIGHT / 3) - (BAR_HEIGHT / 2) - ((SCREEN_FRAME_HEIGHT / 3) / 2), BAR_WIDTH,
            BAR_HEIGHT);
        this.setValue(TO);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(LABEL_PREFIX + label);
        this.setValue(barProgress++);
    }
}
