package com.zlotran.happyhours.ui.bar;

import com.zlotran.happyhours.config.BarConfig;
import com.zlotran.happyhours.ui.refresher.Refresher;
import com.zlotran.happyhours.ui.util.MichaelColorCalculatorUtil;

public abstract class ColorChangerRefreshableBar extends RefreshableBar {



    ColorChangerRefreshableBar(final Refresher refresher) {
        super(refresher);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setForeground(MichaelColorCalculatorUtil.calcColor(barProgress, BarConfig.getInstance().getNumericConfig("acceptable.min.insec"), getMinimum(), getMaximum()));
    }


}
