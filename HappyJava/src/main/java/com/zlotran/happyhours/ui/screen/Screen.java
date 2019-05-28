package com.zlotran.happyhours.ui.screen;

import java.awt.LayoutManager;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.zlotran.happyhours.ConfigSource;
import com.zlotran.happyhours.ui.bar.RefreshableBar;

public class Screen extends JFrame {

    private static final int DEFAULT_FRAME_WIDTH = ConfigSource.getConfigSource().getNumericConfig("screen.width");
    private static final int DEFAULT_FRAME_HEIGHT = ConfigSource.getConfigSource().getNumericConfig("screen.height");
    private static final String DEFAULT_TITLE = ConfigSource.getConfigSource().getConfig("screen.title");
    private static final LayoutManager NO_LAYOUT = null;
    private static final boolean VISIBLE = true;
    private Set<RefreshableBar> refreshableBars;

    public Screen() {
        super();
        this.setSize(DEFAULT_FRAME_WIDTH, DEFAULT_FRAME_HEIGHT);
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
