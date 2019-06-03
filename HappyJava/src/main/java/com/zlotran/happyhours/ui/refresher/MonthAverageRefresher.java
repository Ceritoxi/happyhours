package com.zlotran.happyhours.ui.refresher;

import java.time.Month;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class MonthAverageRefresher implements Refresher {

    private RecordStatisticsController recordStatisticsController;
    private Month month;
    private String year;

    public MonthAverageRefresher(RecordStatisticsController recordStatisticsController, Month month, String year) {
        this.recordStatisticsController = recordStatisticsController;
        this.month = month;
        this.year = year;
    }

    @Override
    public String labelRefresh() {
        return recordStatisticsController.getMonthOfYearAverage(year, month);
    }

    @Override
    public int progressRefresh() {
        return recordStatisticsController.getMonthOfYearAverageInSec(year, month);
    }
}
