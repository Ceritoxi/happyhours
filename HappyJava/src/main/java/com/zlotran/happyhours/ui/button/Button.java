package com.zlotran.happyhours.ui.button;

import javax.swing.JButton;

import com.zlotran.happyhours.config.GeneralConfig;

public abstract class Button extends JButton {

    static final int SCREEN_FRAME_HEIGHT = GeneralConfig.getInstance().getNumericConfig("screen.height");
    static final int BUTTON_WIDTH = 350;
    static final int BUTTON_HEIGHT = 20;
    static final String BUTTON_NAME = "Change me";

    public Button(String text) {
        super(text);
    }
}
