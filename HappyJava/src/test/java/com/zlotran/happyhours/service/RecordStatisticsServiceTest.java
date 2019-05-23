package com.zlotran.happyhours.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.format.TimeFormatter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RecordStatisticsServiceTest {

    private static final String EXPECTED_FORMATTED_TIME = "06:59:59";
    private static final long TOTAL_IN_SEC_FOR_CURRENT_MONTH = 20000;
    private Record testRecord1;
    private Record testRecord2;
    Record testRecord3;

    private RecordStatisticsService underTest;

    @Mock
    private RecordDao recordDao;

    @Mock
    private TimeFormatter timeFormatter;

    @Mock
    private RecordStatisticsCalculationUtility recordStatisticsCalculationUtility;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        underTest = new RecordStatisticsService(recordDao, timeFormatter, recordStatisticsCalculationUtility);
    }

    @Test
    void currentMonthTotalFormatted() {
        //GIVEN
        String expected = EXPECTED_FORMATTED_TIME;

        //WHEN
        when(recordStatisticsCalculationUtility.calculateTotalInSecond(testRecordsOfCurrentMonth())).thenReturn(TOTAL_IN_SEC_FOR_CURRENT_MONTH);
        String actual = underTest.currentMonthTotalFormatted();
        //THEN
        assertEquals(expected, actual);

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

    private List<Record> testRecordsOfCurrentMonth() {
        List<Record> records = new ArrayList<>();
        records.add(testRecord1);
        records.add(testRecord2);
        return records;
    }
}
