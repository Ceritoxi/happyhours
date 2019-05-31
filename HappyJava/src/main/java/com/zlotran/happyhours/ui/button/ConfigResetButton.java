package com.zlotran.happyhours.ui.button;

import java.awt.event.ActionListener;

@Deprecated
public class ConfigResetButton extends Button {

    private static final String DEFAULT_BUTTON_NAME = "R";

    public ConfigResetButton(ActionListener configResetActionListener) {
        super(DEFAULT_BUTTON_NAME);
        this.setBounds((20 + BUTTON_WIDTH / 4)  + 400, (SCREEN_FRAME_HEIGHT / 3) - (BUTTON_HEIGHT / 2) - ((SCREEN_FRAME_HEIGHT / 3) / 2) - (BUTTON_HEIGHT * 4), BUTTON_WIDTH / 3,
            BUTTON_HEIGHT);
        this.addActionListener(configResetActionListener);
    }
}
