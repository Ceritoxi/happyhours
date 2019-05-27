package com.zlotran.happyhours.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class TimeFormatterTest {

    private static final long SECONDS_INPUT = 100000000;
    private static final String EXPECTED_TRANSFORMATION = "3 Years, 62 Days, 09:46:40";
    private static final long YEAR = 3L;
    private static final long DAYS = 1157L;
    private static final long DAYS_SHOWN = 62L;
    private static final long HOURS_SHOWN = 9L;
    private static final long MINUTES_SHOWN = 46L;
    private static final long SECONDS_SHOWN = 40L;

    @InjectMocks
    private TimeFormatter underTest;

    @Mock
    private TimeFormatterUtil timeFormatterUtil;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void formatTimeFromSeconds() {
        //GIVEN
        //WHEN
        when(timeFormatterUtil.isMoreThanOneYear(SECONDS_INPUT)).thenReturn(true);
        when(timeFormatterUtil.isAtLeastAYear(SECONDS_INPUT)).thenReturn(true);
        when(timeFormatterUtil.isMoreThanOneDay(SECONDS_INPUT)).thenReturn(true);
        when(timeFormatterUtil.isAtLeastOneDay(SECONDS_INPUT)).thenReturn(true);
        when(timeFormatterUtil.isLessThanTwoHoursDigits(SECONDS_INPUT)).thenReturn(true);
        when(timeFormatterUtil.isLessThanTwoMinuteDigits(SECONDS_INPUT)).thenReturn(false);
        when(timeFormatterUtil.isLessThanTwoSecondDigits(SECONDS_INPUT)).thenReturn(false);
        when(timeFormatterUtil.years(SECONDS_INPUT)).thenReturn(YEAR);
        when(timeFormatterUtil.days(SECONDS_INPUT)).thenReturn(DAYS);
        when(timeFormatterUtil.shownDays(SECONDS_INPUT)).thenReturn(DAYS_SHOWN);
        when(timeFormatterUtil.shownHours(SECONDS_INPUT)).thenReturn(HOURS_SHOWN);
        when(timeFormatterUtil.shownMinutes(SECONDS_INPUT)).thenReturn(MINUTES_SHOWN);
        when(timeFormatterUtil.shownSeconds(SECONDS_INPUT)).thenReturn(SECONDS_SHOWN);
        String actual = underTest.formatTimeFromSeconds(SECONDS_INPUT);
        //THEN
        assertEquals(EXPECTED_TRANSFORMATION, actual);
    }
}
