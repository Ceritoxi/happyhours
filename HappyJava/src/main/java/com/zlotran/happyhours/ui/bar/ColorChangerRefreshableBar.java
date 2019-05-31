package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;
import com.zlotran.happyhours.ui.util.MichaelColorCalculatorUtil;

public abstract class ColorChangerRefreshableBar extends RefreshableBar {



    ColorChangerRefreshableBar(LabelRefresher labelRefresher) {
        super(labelRefresher);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setForeground(MichaelColorCalculatorUtil.calcColor(barProgress, 28800, getMinimum(), getMaximum()));
    }


}
