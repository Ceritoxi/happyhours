package com.zlotran.happyhours.ui.screen;

import java.awt.LayoutManager;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.zlotran.happyhours.config.GeneralConfig;
import com.zlotran.happyhours.ui.bar.RefreshableBar;

public class Screen extends JFrame {

    private static final int FRAME_WIDTH = GeneralConfig.getInstance().getNumericConfig("screen.width");
    private static final int FRAME_HEIGHT = GeneralConfig.getInstance().getNumericConfig("screen.height");
    private static final String DEFAULT_TITLE = GeneralConfig.getInstance().getConfig("screen.title");
    private static final LayoutManager NO_LAYOUT = null;
    private static final boolean VISIBLE = true;
    private Set<RefreshableBar> refreshableBars;

    public Screen() {
        super();
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setTitle(DEFAULT_TITLE);
        this.refreshableBars = new HashSet<>();
    }

    public void revealScreen() {
        this.setLayout(NO_LAYOUT);
        this.setVisible(VISIBLE);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public Set<RefreshableBar> getRefreshableBars() {
        return refreshableBars;
    }

    public void add(RefreshableBar refreshableBar) {
        super.add(refreshableBar);
        refreshableBars.add(refreshableBar);
    }
}
