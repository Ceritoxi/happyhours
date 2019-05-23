package com.zlotran.happyhours.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.format.TimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class RecordStatisticsServiceTest {

    private RecordDao recordDao;
    private TimeFormatter timeFormatter;
    private RecordStatisticsCalculationUtility recordStatisticsCalculationUtility;

    @BeforeEach
    void setUp() {
        RecordStatisticsService recordStatisticsService = new RecordStatisticsService(recordDao, timeFormatter, recordStatisticsCalculationUtility);
    }

    @Test
    void currentMonthTotalFormatted() {
    }

    @Test
    void currentMonthTotalInSeconds() {
    }

    @Test
    void currentDayTotalFormatted() {
    }

    @Test
    void currentDayTotalInSeconds() {
    }

    @Test
    void allTimeTotalFormatted() {
    }

    @Test
    void allTimeTotalInSeconds() {
    }

    @Test
    void averageOfCurrentMonthFormatted() {
    }

    @Test
    void averageOfCurrentMonthInSeconds() {
    }

    @Test
    void allTimeAverageFormatted() {
    }

    @Test
    void allTimeAverageInSeconds() {
    }
}
