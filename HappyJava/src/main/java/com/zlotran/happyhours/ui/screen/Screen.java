package com.zlotran.happyhours.ui.screen;

import java.awt.LayoutManager;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.zlotran.happyhours.config.GeneralConfig;
import com.zlotran.happyhours.ui.bar.RefreshableBar;

public class Screen extends JFrame {

    private static final LayoutManager NO_LAYOUT = null;
    private static final boolean VISIBLE = true;
    private Set<RefreshableBar> refreshableBars;

    public Screen() {
        super();
        this.setSize(getNumericConfig("screen.width"), getNumericConfig("screen.height"));
        this.setTitle(getConfig("screen.title"));
        this.refreshableBars = new HashSet<>();
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(NO_LAYOUT);
    }

    public void revealScreen() {
        this.setVisible(VISIBLE);
    }

    /**
     * TODO: this might need an other solution
     */
    public void refresh() {
        this.setTitle(getConfig("screen.title"));
    }

    public Set<RefreshableBar> getRefreshableBars() {
        return refreshableBars;
    }

    public void add(final RefreshableBar refreshableBar) {
        super.add(refreshableBar);
        refreshableBars.add(refreshableBar);
    }

    private Integer getNumericConfig(final String config) {
        return GeneralConfig.getInstance().getNumericConfig(config);
    }

    private String getConfig(final String config) {
        return GeneralConfig.getInstance().getConfig(config);
    }
}
