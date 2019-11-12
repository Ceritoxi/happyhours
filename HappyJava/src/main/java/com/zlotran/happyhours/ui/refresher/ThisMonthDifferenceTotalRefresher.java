package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class ThisMonthDifferenceTotalRefresher implements Refresher {

    private RecordStatisticsController recordStatisticsController;

    public ThisMonthDifferenceTotalRefresher(RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override
    public String labelRefresh() {
        return recordStatisticsController.getMonthDifferenceFromEightThirty();
    }

    @Override
    public int progressRefresh() {
        return recordStatisticsController.getMonthDifferenceFromEightThirtyInSeconds() + 17*30*60;
    }
}
