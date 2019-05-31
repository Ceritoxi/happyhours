package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class ThisMonthAverageLabelRefresher implements LabelRefresher {

    private RecordStatisticsController recordStatisticsController;

    public ThisMonthAverageLabelRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getThisMonthAverage();
    }

    @Override public int progressRefresh() {
        return recordStatisticsController.getThisMonthAverageInSeconds();
    }

}
