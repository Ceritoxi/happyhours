package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class AllTimeTotalRefresher implements Refresher {

    private static final int FULL = 1;
    private RecordStatisticsController recordStatisticsController;

    public AllTimeTotalRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getAllTimeTotal();
    }

    @Override public int progressRefresh() {
        return FULL;
    }

}
