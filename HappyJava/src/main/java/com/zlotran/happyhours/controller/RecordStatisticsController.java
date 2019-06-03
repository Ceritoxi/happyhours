package com.zlotran.happyhours.controller;

import java.time.Month;
import java.util.List;

import com.zlotran.happyhours.service.RecordStatisticsService;

public class RecordStatisticsController {

    private RecordStatisticsService recordStatisticsService;

    public RecordStatisticsController(final RecordStatisticsService recordStatisticsService) {
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

    public String getMonthOfYearAverage(String year, Month month) {
        return recordStatisticsService.monthOfYearAverage(Integer.valueOf(year), month);
    }

    public int getMonthOfYearAverageInSec(String year, Month month) {
        return (int) recordStatisticsService.monthOfYearAverageInSec(Integer.valueOf(year), month);
    }

    public String getMonthOfYearTotal(String year, Month month) {
        return recordStatisticsService.monthOfYearTotal(Integer.valueOf(year), month);
    }

    public int getAllTimeAverageInSeconds() {
        return (int) recordStatisticsService.allTimeAverageInSeconds();
    }

    public int getThisMonthAverageInSeconds() {
        return (int) recordStatisticsService.averageOfCurrentMonthInSeconds();
    }

    public int getTodaySeconds() {
        return (int) recordStatisticsService.currentDayTotalInSeconds();
    }

    public List<String> getRecordedYears() {
        return recordStatisticsService.getRecordedYears();
    }

    public List<Month> getRecordedMonths(int year) {
        return recordStatisticsService.getRecordedMonthsOfYear(year);
    }

    public Month getLatestMonth() {
        return recordStatisticsService.getLatestMonth();
    }

    public String getLatestYear() {
        return recordStatisticsService.getLatestYear();
    }

    public List<Month> getRecordedMonths(String year) {
        return recordStatisticsService.getRecordedMonthsOfYear(Integer.valueOf(year));
    }

}
