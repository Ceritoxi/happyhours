package com.zlotran.happyhours.ui.bar;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.refresher.LabelRefresher;

public class RefreshableBar extends JProgressBar {

    protected String label;
    protected int barProgress;
    private LabelRefresher labelRefresher;

    public RefreshableBar(int min, int max, LabelRefresher labelRefresher) {
        super(min, max);
        this.labelRefresher = labelRefresher;
    }

    public void refresh() {
        label = labelRefresher.labelRefresh();
        barProgress = labelRefresher.progressRefresh();
    }

}
