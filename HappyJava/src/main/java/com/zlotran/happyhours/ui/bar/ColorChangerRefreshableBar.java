package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;
import com.zlotran.happyhours.ui.util.ColorCalculatorUtil;

public class ColorChangerRefreshableBar extends RefreshableBar {

    private static final int PEAK_YELLOW = 27000;

    ColorChangerRefreshableBar(LabelRefresher labelRefresher) {
        super(labelRefresher);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setForeground(ColorCalculatorUtil.calcColor(barProgress, PEAK_YELLOW, FROM, TO));
    }


}
