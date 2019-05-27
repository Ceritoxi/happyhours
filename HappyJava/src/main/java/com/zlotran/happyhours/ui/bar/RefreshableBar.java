package com.zlotran.happyhours.ui.bar;

import javax.swing.JProgressBar;

import com.zlotran.happyhours.LabelRefresher;

public class RefreshableBar extends JProgressBar {

    protected String label;
    private LabelRefresher labelRefresher;

    public RefreshableBar(int min, int max, LabelRefresher labelRefresher) {
        super(min, max);
        this.labelRefresher = labelRefresher;
    }

    public void refreshLabel() {
        label = labelRefresher.refresh();
    }

}
