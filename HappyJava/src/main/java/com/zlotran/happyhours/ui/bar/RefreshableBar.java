package com.zlotran.happyhours.ui.bar;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public abstract class RefreshableBar extends JProgressBar {

    String label;
    int barProgress;
    private LabelRefresher labelRefresher;

    RefreshableBar(int min, int max, LabelRefresher labelRefresher) {
        super(min, max);
        this.labelRefresher = labelRefresher;
    }

    public void refresh() {
        label = labelRefresher.labelRefresh();
        barProgress = labelRefresher.progressRefresh();
    }
}
