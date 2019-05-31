package com.zlotran.happyhours.format;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TimeFormatterUtilTest {

    private static final long MORE_THAN_A_YEAR = 100_000_000;
    private static final long MORE_THAN_A_YEAR_YEARS = 3;
    private static final long MORE_THAN_A_YEAR_DAYS = 1157;
    private static final long MORE_THAN_A_YEAR_SHOWN_DAYS = 62;
    private static final long MORE_THAN_A_YEAR_SHOWN_HOURS = 9;
    private static final long MORE_THAN_A_YEAR_SHOWN_MINUTES = 46;
    private static final long MORE_THAN_A_YEAR_SHOWN_SECONDS = 40;
    private static final long LESS_THAN_A_MINUTE = 10;
    private static final long MORE_THAN_TWO_DIGIT = 11 * 60 * 60 + 11 * 60 + 11;
    private static final long LESS_THAN_TWO_DIGITS = 9 * 60 * 60 + 9 * 60 + 9;
    private TimeFormatterUtil underTest;

    @BeforeEach
    void setUp() {
        underTest = new TimeFormatterUtil();
    }

    @Test
    void isAtLeastAYearIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isAtLeastAYear(MORE_THAN_A_YEAR);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isAtLeastAYearIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isAtLeastAYear(LESS_THAN_A_MINUTE);
        //THEN
        assertFalse(actual);
    }

    @Test
    void isAtLeastOneDayIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isAtLeastOneDay(MORE_THAN_A_YEAR);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isAtLeastOneDayIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isAtLeastOneDay(LESS_THAN_A_MINUTE);
        //THEN
        assertFalse(actual);
    }

    @Test
    void isMoreThanOneYearIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isMoreThanOneYear(MORE_THAN_A_YEAR);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isMoreThanOneYearIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isMoreThanOneYear(LESS_THAN_A_MINUTE);
        //THEN
        assertFalse(actual);
    }

    @Test
    void isMoreThanOneDayIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isMoreThanOneDay(MORE_THAN_A_YEAR);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isMoreThanOneDayIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isMoreThanOneDay(LESS_THAN_A_MINUTE);
        //THEN
        assertFalse(actual);
    }

    @Test
    void isLessThanTwoHoursDigitsIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isLessThanTwoHoursDigits(LESS_THAN_TWO_DIGITS);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isLessThanTwoHoursDigitsIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isLessThanTwoHoursDigits(MORE_THAN_TWO_DIGIT);
        //THEN
        assertFalse(actual);
    }

    @Test
    void isLessThanTwoMinuteDigitsIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isLessThanTwoMinuteDigits(LESS_THAN_TWO_DIGITS);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isLessThanTwoMinuteDigitsIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isLessThanTwoMinuteDigits(MORE_THAN_TWO_DIGIT);
        //THEN
        assertFalse(actual);
    }

    @Test
    void isLessThanTwoSecondDigitsIsTrue() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isLessThanTwoSecondDigits(LESS_THAN_TWO_DIGITS);
        //THEN
        assertTrue(actual);
    }

    @Test
    void isLessThanTwoSecondDigitsIsFalse() {
        //GIVEN
        //WHEN
        final boolean actual = underTest.isLessThanTwoSecondDigits(MORE_THAN_TWO_DIGIT);
        //THEN
        assertFalse(actual);
    }

    @Test
    void years() {
        //GIVEN
        //WHEN
        final long actual = underTest.years(MORE_THAN_A_YEAR);
        //THEN
        assertEquals(MORE_THAN_A_YEAR_YEARS, actual);
    }

    @Test
    void days() {
        //GIVEN
        //WHEN
        final long actual = underTest.days(MORE_THAN_A_YEAR);
        //THEN
        assertEquals(MORE_THAN_A_YEAR_DAYS, actual);
    }

    @Test
    void shownDays() {
        //GIVEN
        //WHEN
        final long actual = underTest.shownDays(MORE_THAN_A_YEAR);
        //THEN
        assertEquals(MORE_THAN_A_YEAR_SHOWN_DAYS, actual);
    }

    @Test
    void shownHours() {
        //GIVEN
        //WHEN
        final long actual = underTest.shownHours(MORE_THAN_A_YEAR);
        //THEN
        assertEquals(MORE_THAN_A_YEAR_SHOWN_HOURS, actual);
    }

    @Test
    void shownMinutes() {
        //GIVEN
        //WHEN
        final long actual = underTest.shownMinutes(MORE_THAN_A_YEAR);
        //THEN
        assertEquals(MORE_THAN_A_YEAR_SHOWN_MINUTES, actual);
    }

    @Test
    void shownSeconds() {
        //GIVEN
        //WHEN
        final long actual = underTest.shownSeconds(MORE_THAN_A_YEAR);
        //THEN
        assertEquals(MORE_THAN_A_YEAR_SHOWN_SECONDS, actual);
    }
}
