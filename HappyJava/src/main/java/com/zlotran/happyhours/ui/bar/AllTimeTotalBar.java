package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.Refresher;

public class AllTimeTotalBar extends RefreshableBar {

    private static final String LABEL_PREFIX = "All time total:\t\t";

    public AllTimeTotalBar(final Refresher refresher) {
        super(refresher);
        this.setMinimum(FROM);
        this.setMaximum(TO);
        this.setBounds(20, SCREEN_FRAME_HEIGHT / 3 - BAR_HEIGHT / 2 - ((SCREEN_FRAME_HEIGHT / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setValue(TO);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setString(LABEL_PREFIX + label);
        this.setValue(barProgress);
    }


}
