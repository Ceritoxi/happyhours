package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class AllTimeAverageRefresher implements Refresher {

    private RecordStatisticsController recordStatisticsController;

    public AllTimeAverageRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getAllTimeAverage();
    }

    @Override public int progressRefresh() {
        return recordStatisticsController.getAllTimeAverageInSeconds();
    }

}
