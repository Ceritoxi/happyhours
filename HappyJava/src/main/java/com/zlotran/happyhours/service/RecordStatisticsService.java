package com.zlotran.happyhours.service;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.format.TimeFormatter;

public class RecordStatisticsService {

    private RecordDao recordDao;
    private TimeFormatter timeFormatter;
    private RecordStatisticsCalculationUtility recordStatisticsCalculationUtility;

    public RecordStatisticsService(RecordDao recordDao, TimeFormatter timeFormatter, RecordStatisticsCalculationUtility recordStatisticsCalculationUtility) {
        this.recordDao = recordDao;
        this.timeFormatter = timeFormatter;
        this.recordStatisticsCalculationUtility = recordStatisticsCalculationUtility;
    }

    public String currentMonthTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentMonth()));
    }

    public long currentMonthTotalInSeconds() {
        return recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentMonth());
    }

    public String currentDayTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentDay()));
    }

    public long currentDayTotalInSeconds() {
        return recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentDay());
    }

    public String allTimeTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecords()));
    }

    public long allTimeTotalInSeconds() {
        return recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecords());
    }

    public String averageOfCurrentMonthFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecordsForCurrentMonth()));
    }

    public long averageOfCurrentMonthInSeconds() {
        return recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecordsForCurrentMonth());
    }

    public String allTimeAverageFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecords()));
    }

    public long allTimeAverageInSeconds() {
        return recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecords());
    }

}
