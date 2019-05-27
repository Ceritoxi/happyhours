package com.zlotran.happyhours.ui;

import javax.swing.JProgressBar;

public class ThisMonthTotalBar extends JProgressBar {

    private static final int FROM = 0;
    private static final int TO = 1;
    private static final int BAR_WIDTH = 350;
    private static final int BAR_HEIGHT = 20;

    public ThisMonthTotalBar(int frameHeight, int frameWidth) {
        super(FROM, TO);
        this.setBounds(20, (2 * (frameHeight / 3)) - (BAR_HEIGHT / 2) - ((frameHeight / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(TO);
    }
}
