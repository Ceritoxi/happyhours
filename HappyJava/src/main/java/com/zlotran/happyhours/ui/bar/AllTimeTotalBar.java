package com.zlotran.happyhours.ui.bar;

import javax.swing.JProgressBar;

public class AllTimeTotalBar extends JProgressBar {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int BAR_WIDTH = 350;
    private static final int BAR_HEIGHT = 20;

    public AllTimeTotalBar(int frameHeight, int frameWidth) {
        super(FROM, TO);
        this.setBounds(20, (frameHeight / 3) - (BAR_HEIGHT / 2) - ((frameWidth / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(TO);
    }

    public void changeLabel(String labelChange) {
        this.setString("All time total:\t\t" + labelChange);
    }
}
