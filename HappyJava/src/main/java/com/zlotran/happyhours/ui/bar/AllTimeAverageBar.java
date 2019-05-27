package com.zlotran.happyhours.ui.bar;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.LabelRefresher;

public class AllTimeAverageBar extends RefreshableBar {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int BAR_WIDTH = 350;
    private static final int BAR_HEIGHT = 20;

    public AllTimeAverageBar(int frameHeight, int frameWidth, LabelRefresher labelRefresher) {
        super(FROM, TO, labelRefresher);
        this.setBounds(frameWidth - (40 + BAR_WIDTH), (frameHeight / 3) - (BAR_HEIGHT / 2) - ((frameHeight / 3) / 2), BAR_WIDTH,
            BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(TO);
    }

    public void changeLabel() {
        this.setString("All time average:\t" + label);
    }
}
