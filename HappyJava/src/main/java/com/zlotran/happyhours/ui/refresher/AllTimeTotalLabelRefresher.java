package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class AllTimeTotalLabelRefresher implements LabelRefresher {

    private static final int FULL = 1;
    private RecordStatisticsController recordStatisticsController;

    public AllTimeTotalLabelRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getAllTimeTotal();
    }

    @Override public int progressRefresh() {
        return FULL;
    }

}
