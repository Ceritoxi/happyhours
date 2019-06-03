package com.zlotran.happyhours.ui.refresher;

import java.time.Month;

import com.zlotran.happyhours.controller.RecordStatisticsController;

public class MonthTotalRefresher implements Refresher{

    private static final int FULL = 1;

    private RecordStatisticsController recordStatisticsController;
    private Month month;
    private String year;

    public MonthTotalRefresher(RecordStatisticsController recordStatisticsController, Month month, String year) {
        this.recordStatisticsController = recordStatisticsController;
        this.month = month;
        this.year = year;
    }

    @Override
    public String labelRefresh() {
        return recordStatisticsController.getMonthOfYearTotal(year, month);
    }

    @Override
    public int progressRefresh() {
        return FULL;
    }
}
