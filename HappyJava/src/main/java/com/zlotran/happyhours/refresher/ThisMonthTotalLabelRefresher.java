package com.zlotran.happyhours.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class ThisMonthTotalLabelRefresher implements LabelRefresher {

    private static final int FULL = 1;
    private RecordStatisticsController recordStatisticsController;

    public ThisMonthTotalLabelRefresher(RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getThisMonthTotal();
    }

    @Override public int progressRefresh() {
        return FULL;
    }

}
