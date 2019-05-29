package com.zlotran.happyhours.ui.button;

import java.awt.event.ActionListener;

public class LogADayButton extends Button {

    private static final String DEFAULT_BUTTON_NAME = "Log a day";

    public LogADayButton(ActionListener logADayActionListener) {
        super(DEFAULT_BUTTON_NAME);
        this.setBounds(20 + BUTTON_WIDTH / 4, (SCREEN_FRAME_HEIGHT / 3) - (BUTTON_HEIGHT / 2) - ((SCREEN_FRAME_HEIGHT / 3) / 2) - (BUTTON_HEIGHT * 4), BUTTON_WIDTH
                / 2,
            BUTTON_HEIGHT * 3);
        this.addActionListener(logADayActionListener);
    }
}
