package com.zlotran.happyhours.controller;

import com.zlotran.happyhours.service.RecordStatisticsService;

public class RecordStatisticsController {

    private RecordStatisticsService recordStatisticsService;

    public RecordStatisticsController(RecordStatisticsService recordStatisticsService) {
        this.recordStatisticsService = recordStatisticsService;
    }

    public String getAllTimeAverage() {
        return recordStatisticsService.allTimeAverageFormatted();
    }

    public String getAllTimeTotal() {
        return recordStatisticsService.allTimeTotalFormatted();
    }

    public String getThisMonthAverage() {
        return recordStatisticsService.averageOfCurrentMonthFormatted();
    }

    public String getThisMonthTotal() {
        return recordStatisticsService.currentMonthTotalFormatted();
    }

    public String getToday() {
        return recordStatisticsService.currentDayTotalFormatted();
    }

    public int getThisMonthAverageInSeconds() {
        return (int) recordStatisticsService.averageOfCurrentMonthInSeconds();
    }

    public int getTodaySeconds() {
        return (int) recordStatisticsService.currentDayTotalInSeconds();
    }
}
