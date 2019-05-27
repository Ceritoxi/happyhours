package com.zlotran.happyhours.service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.when;

class RecordStatisticsCalculationUtilityTest {

    private Record testRecordFirstOfJanuaryTenOClockStart;
    private Record testRecordFirstOfJanuarySixteenOClockEnd;
    private Record testRecordSecondOfJanuaryNineOClockStart;
    private Record testRecordSecondOfJanuaryNineteenOClockEnd;
    private Record testRecordThirdOfJanuaryNineOClockStart;

    private static final long EXPECTED_AVERAGE_EIGHT_HOURS = 8 * 60 * 60;
    private static final long EXPECTED_TOTAL_SIXTEEN_HOURS = (6 + 10) * 60 * 60;
    private static final long EXPECTED_TOTAL_TWENTY_TWO_HOURS = (6 + 10 + 8) * 60 * 60;
    private static final LocalDateTime THIRD_OF_JANUARY_SEVENTEEN_O_CLOCK = LocalDateTime.of(2019, Month.JANUARY, 3, 17, 0, 0);

    @InjectMocks
    private RecordStatisticsCalculationUtility underTest;

    @Mock
    private LocalDateTimeSupplier localDateTimeSupplier;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        setUpRecords();
    }

    @Test
    void calculateAverageInSecondsWithEndingLastRecord() {
        //GIVEN
        List<Record> records = testRecordsWithEndingLastRecord();
        //WHEN
        long actual = underTest.calculateAverageInSeconds(records);
        //THEN
        assertEquals(EXPECTED_AVERAGE_EIGHT_HOURS, actual);
    }

    @Test
    void calculateTotalInSecondsWithEndingLastRecord() {
        //GIVEN
        List<Record> records = testRecordsWithEndingLastRecord();
        //WHEN
        long actual = underTest.calculateTotalInSecond(records);
        //THEN
        assertEquals(EXPECTED_TOTAL_SIXTEEN_HOURS, actual);
    }

    @Test
    void calculateTotalInSecondsWithStartingLastRecord() {
        //GIVEN
        List<Record> records = testRecordsWithStartingLastRecord();
        //WHEN
        when(localDateTimeSupplier.get()).thenReturn(THIRD_OF_JANUARY_SEVENTEEN_O_CLOCK);
        long actual = underTest.calculateTotalInSecond(records);
        //THEN
        assertEquals(EXPECTED_TOTAL_TWENTY_TWO_HOURS, actual);
    }

    @Test
    void calculateAverageInSecondsWithStartingLastRecord() {
        //GIVEN
        List<Record> records = testRecordsWithStartingLastRecord();
        //WHEN
        when(localDateTimeSupplier.get()).thenReturn(THIRD_OF_JANUARY_SEVENTEEN_O_CLOCK);
        long actual = underTest.calculateAverageInSeconds(records);
        //THEN
        assertEquals(EXPECTED_AVERAGE_EIGHT_HOURS, actual);
    }

    private List<Record> testRecordsWithEndingLastRecord() {
        List<Record> records = new ArrayList<>();
        records.add(testRecordFirstOfJanuaryTenOClockStart);
        records.add(testRecordFirstOfJanuarySixteenOClockEnd);
        records.add(testRecordSecondOfJanuaryNineOClockStart);
        records.add(testRecordSecondOfJanuaryNineteenOClockEnd);
        return records;
    }

    private List<Record> testRecordsWithStartingLastRecord() {
        List<Record> records = new ArrayList<>();
        records.add(testRecordFirstOfJanuaryTenOClockStart);
        records.add(testRecordFirstOfJanuarySixteenOClockEnd);
        records.add(testRecordSecondOfJanuaryNineOClockStart);
        records.add(testRecordSecondOfJanuaryNineteenOClockEnd);
        records.add(testRecordThirdOfJanuaryNineOClockStart);
        return records;
    }

    private void setUpRecords() {
        testRecordFirstOfJanuaryTenOClockStart = new Record();
        testRecordFirstOfJanuaryTenOClockStart.setDate(LocalDateTime.of(2019, Month.JANUARY, 1, 10, 0, 0));
        testRecordFirstOfJanuaryTenOClockStart.setState(State.START);
        testRecordFirstOfJanuarySixteenOClockEnd = new Record();
        testRecordFirstOfJanuarySixteenOClockEnd.setDate(LocalDateTime.of(2019, Month.JANUARY, 1, 16, 0, 0));
        testRecordFirstOfJanuarySixteenOClockEnd.setState(State.END);
        testRecordSecondOfJanuaryNineOClockStart = new Record();
        testRecordSecondOfJanuaryNineOClockStart.setDate(LocalDateTime.of(2019, Month.JANUARY, 2, 9, 0, 0));
        testRecordSecondOfJanuaryNineOClockStart.setState(State.START);
        testRecordSecondOfJanuaryNineteenOClockEnd = new Record();
        testRecordSecondOfJanuaryNineteenOClockEnd.setDate(LocalDateTime.of(2019, Month.JANUARY, 2, 19, 0, 0));
        testRecordSecondOfJanuaryNineteenOClockEnd.setState(State.END);
        testRecordThirdOfJanuaryNineOClockStart = new Record();
        testRecordThirdOfJanuaryNineOClockStart.setDate(LocalDateTime.of(2019, Month.JANUARY, 3, 9, 0, 0));
        testRecordThirdOfJanuaryNineOClockStart.setState(State.START);
    }

}

