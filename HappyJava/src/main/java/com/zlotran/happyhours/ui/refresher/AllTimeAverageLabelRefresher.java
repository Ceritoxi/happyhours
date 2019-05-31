package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class AllTimeAverageLabelRefresher implements LabelRefresher {

    private RecordStatisticsController recordStatisticsController;

    public AllTimeAverageLabelRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getAllTimeAverage();
    }

    @Override public int progressRefresh() {
        return recordStatisticsController.getAllTimeAverageInSeconds();
    }

}
