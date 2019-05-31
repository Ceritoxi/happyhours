package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;
import com.zlotran.happyhours.ui.util.ColorCalculatorUtil;

public abstract class ColorChangerRefreshableBar extends RefreshableBar {

    protected static final int PEAK_YELLOW = 28800;

    ColorChangerRefreshableBar(LabelRefresher labelRefresher) {
        super(labelRefresher);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setForeground(ColorCalculatorUtil.calcColor(barProgress, PEAK_YELLOW, getMinimum(), getMaximum()));
    }
}
