package com.zlotran.happyhours.ui.bar;

import javax.swing.JProgressBar;

public class ThisMonthAverageBar extends JProgressBar {

    private static final int FROM = 0;
    private static final int TO = 1000;
    private static final int BAR_WIDTH = 350;
    private static final int BAR_HEIGHT = 20;

    public ThisMonthAverageBar(int frameHeight, int frameWidth) {
        super(FROM, TO);
        this.setBounds(frameWidth - (40 + BAR_WIDTH), (2 * (frameHeight / 3)) - (BAR_HEIGHT / 2) - ((frameHeight / 3) / 2), BAR_WIDTH, BAR_HEIGHT);
        this.setStringPainted(true);
        this.setValue(FROM);
    }

    public void changeLabel(String labelChange) {
        this.setString("This month average:\t" + labelChange);
    }
}
