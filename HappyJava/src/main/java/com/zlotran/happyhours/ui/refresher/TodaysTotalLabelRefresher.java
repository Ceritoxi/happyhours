package com.zlotran.happyhours.ui.refresher;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class TodaysTotalLabelRefresher implements LabelRefresher {

    private RecordStatisticsController recordStatisticsController;

    public TodaysTotalLabelRefresher(final RecordStatisticsController recordStatisticsController) {
        this.recordStatisticsController = recordStatisticsController;
    }

    @Override public String labelRefresh() {
        return recordStatisticsController.getToday();
    }

    @Override public int progressRefresh() {
        return recordStatisticsController.getTodaySeconds();
    }

}
