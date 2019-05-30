package com.zlotran.happyhours.ui.bar;

import java.awt.Color;

import com.zlotran.happyhours.ui.refresher.LabelRefresher;

public class ColorChangerRefreshableBar extends RefreshableBar {

    private static final double PEAK_YELLOW = 27000d;

    ColorChangerRefreshableBar(LabelRefresher labelRefresher) {
        super(labelRefresher);
    }

    @Override
    public void refresh() {
        super.refresh();
        this.setForeground(calcColor());
    }

    private Color calcColor() {
        Color color;
        if (barProgress < PEAK_YELLOW) {
            color = new Color(255, (int) (((barProgress - FROM) / (PEAK_YELLOW - FROM)) * (255 - 50)), 0);
        } else if (barProgress <= TO) {
            color = new Color(255 - (int) (((barProgress - PEAK_YELLOW) / (TO - PEAK_YELLOW)) * 255), 255 - 50, 0);
        } else {
            color = new Color(0, 255 - 50, 0);
        }
        return color;
    }
}
