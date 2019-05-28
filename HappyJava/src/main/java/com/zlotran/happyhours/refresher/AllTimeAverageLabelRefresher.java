package com.zlotran.happyhours.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class AllTimeAverageLabelRefresher implements LabelRefresher {

    private static final int FULL = 1;
    private RecordStatisticsController recordStatisticsController;

    public AllTimeAverageLabelRefresher(RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getAllTimeAverage();
    }

    @Override public int progressRefresh() {
        return FULL;
    }

}
