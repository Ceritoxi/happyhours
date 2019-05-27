package com.zlotran.happyhours.ui.screen;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Screen extends JFrame {

    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;

    public Screen() {
        super();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle("HAPPY HOURS");
    }

    public void showMe() {
        this.setLayout(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
