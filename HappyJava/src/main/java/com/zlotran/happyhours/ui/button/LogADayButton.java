package com.zlotran.happyhours.ui.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LogADayButton extends JButton {

    private static final String BUTTON_NAME = "Log a day";
    private static final int BUTTON_WIDTH = 350 / 2;
    private static final int BUTTON_HEIGHT = 20 * 3;

    public LogADayButton(int frameHeight, int frameWidth, ActionListener logADayActionListener) {
        super(BUTTON_NAME);
        this.setBounds(20 + BUTTON_WIDTH / 4, (frameHeight / 3) - (BUTTON_HEIGHT / 2) - ((frameHeight / 3) / 2) - (BUTTON_HEIGHT * 4), BUTTON_WIDTH,
            BUTTON_HEIGHT);
        this.addActionListener(logADayActionListener);
    }
}
