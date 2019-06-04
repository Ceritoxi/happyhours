package com.zlotran.happyhours.ui.button;

import java.awt.event.ActionListener;

public class LogADayButton extends Button {

    private static final String DEFAULT_BUTTON_NAME = "Log a day";

    public LogADayButton(final ActionListener logADayActionListener) {
        super(DEFAULT_BUTTON_NAME);
        this.setBounds(585, 130, BUTTON_WIDTH
                / 2,
            BUTTON_HEIGHT * 3);
        this.addActionListener(logADayActionListener);
    }
}
