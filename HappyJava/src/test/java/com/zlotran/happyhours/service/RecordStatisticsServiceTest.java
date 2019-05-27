package com.zlotran.happyhours.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.format.TimeFormatter;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class RecordStatisticsServiceTest {

    @InjectMocks
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
    }

    @Test
    void currentMonthTotalFormatted() {
        //GIVEN
        //WHEN
        underTest.currentMonthTotalFormatted();
        //THEN
        verify(recordDao, times(1)).getRecordsForCurrentMonth();
        verify(recordStatisticsCalculationUtility, times(1)).calculateTotalInSecond(Matchers.anyListOf(Record.class));
        verify(timeFormatter, times(1)).formatTimeFromSeconds(Matchers.anyLong());
    }

    @Test
    void currentMonthTotalInSeconds() {
        //GIVEN
        //WHEN
        underTest.currentMonthTotalInSeconds();
        //THEN
        verify(recordDao, times(1)).getRecordsForCurrentMonth();
        verify(recordStatisticsCalculationUtility, times(1)).calculateTotalInSecond(Matchers.anyListOf(Record.class));
    }

    @Test
    void currentDayTotalFormatted() {
        //GIVEN
        //WHEN
        underTest.currentDayTotalFormatted();
        //THEN
        verify(recordDao, times(1)).getRecordsForCurrentDay();
        verify(recordStatisticsCalculationUtility, times(1)).calculateTotalInSecond(Matchers.anyListOf(Record.class));
        verify(timeFormatter, times(1)).formatTimeFromSeconds(Matchers.anyLong());
    }

    @Test
    void currentDayTotalInSeconds() {
        //GIVEN
        //WHEN
        underTest.currentDayTotalInSeconds();
        //THEN
        verify(recordDao, times(1)).getRecordsForCurrentDay();
        verify(recordStatisticsCalculationUtility, times(1)).calculateTotalInSecond(Matchers.anyListOf(Record.class));
    }

    @Test
    void allTimeTotalFormatted() {
        //GIVEN
        //WHEN
        underTest.allTimeTotalFormatted();
        //THEN
        verify(recordDao, times(1)).getRecords();
        verify(recordStatisticsCalculationUtility, times(1)).calculateTotalInSecond(Matchers.anyListOf(Record.class));
        verify(timeFormatter, times(1)).formatTimeFromSeconds(Matchers.anyLong());
    }

    @Test
    void allTimeTotalInSeconds() {
        //GIVEN
        //WHEN
        underTest.allTimeTotalInSeconds();
        //THEN
        verify(recordDao, times(1)).getRecords();
        verify(recordStatisticsCalculationUtility, times(1)).calculateTotalInSecond(Matchers.anyListOf(Record.class));
    }

    @Test
    void averageOfCurrentMonthFormatted() {
        //GIVEN
        //WHEN
        underTest.averageOfCurrentMonthFormatted();
        //THEN
        verify(recordDao, times(1)).getRecordsForCurrentMonth();
        verify(recordStatisticsCalculationUtility, times(1)).calculateAverageInSeconds(Matchers.anyListOf(Record.class));
        verify(timeFormatter, times(1)).formatTimeFromSeconds(Matchers.anyLong());
    }

    @Test
    void averageOfCurrentMonthInSeconds() {
        //GIVEN
        //WHEN
        underTest.averageOfCurrentMonthInSeconds();
        //THEN
        verify(recordDao, times(1)).getRecordsForCurrentMonth();
        verify(recordStatisticsCalculationUtility, times(1)).calculateAverageInSeconds(Matchers.anyListOf(Record.class));
    }

    @Test
    void allTimeAverageFormatted() {
        //GIVEN
        //WHEN
        underTest.allTimeAverageFormatted();
        //THEN
        verify(recordDao, times(1)).getRecords();
        verify(recordStatisticsCalculationUtility, times(1)).calculateAverageInSeconds(Matchers.anyListOf(Record.class));
        verify(timeFormatter, times(1)).formatTimeFromSeconds(Matchers.anyLong());
    }

    @Test
    void allTimeAverageInSeconds() {
        //GIVEN
        //WHEN
        underTest.allTimeAverageInSeconds();
        //THEN
        verify(recordDao, times(1)).getRecords();
        verify(recordStatisticsCalculationUtility, times(1)).calculateAverageInSeconds(Matchers.anyListOf(Record.class));
    }
}
