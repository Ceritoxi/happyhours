package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class ThisMonthTotalLabelRefresher implements LabelRefresher {

    private static final int FULL = 1;
    private RecordStatisticsController recordStatisticsController;

    public ThisMonthTotalLabelRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getThisMonthTotal();
    }

    @Override public int progressRefresh() {
        return FULL;
    }

}
