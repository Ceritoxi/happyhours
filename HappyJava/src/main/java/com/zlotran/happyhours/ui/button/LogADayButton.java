package com.zlotran.happyhours.ui.button;

import java.awt.event.ActionListener;

import javax.swing.JButton;

public class LogADayButton extends JButton {

    private static final String DEFAULT_BUTTON_NAME = "Log a day";
    private static final int DEFAULT_BUTTON_WIDTH = 350;
    private static final int DEFAULT_BUTTON_HEIGHT = 20;

    public LogADayButton(int frameHeight, int frameWidth, ActionListener logADayActionListener) {
        super(DEFAULT_BUTTON_NAME);
        this.setBounds(20 + DEFAULT_BUTTON_WIDTH / 4, (frameHeight / 3) - (DEFAULT_BUTTON_HEIGHT / 2) - ((frameHeight / 3) / 2) - (DEFAULT_BUTTON_HEIGHT * 4), DEFAULT_BUTTON_WIDTH
                / 2,
            DEFAULT_BUTTON_HEIGHT * 3);
        this.addActionListener(logADayActionListener);
    }
}
