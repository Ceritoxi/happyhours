package com.zlotran.happyhours.format;

public class TimeFormatterUtil {

    private static final int DAYS_REMAINDER = 365;
    private static final int HOURS_REMAINDER = 24;
    private static final int MINUTES_REMAINDER = 60;
    private static final int SECOND_REMAINDER = 60;

    private static final int YEARS = 365;
    private static final int DAYS = 24;
    private static final int HOURS = 60;
    private static final int MINUTES = 60;

    private static final int TWO_DIGITS = 10;
    private static final int ONE = 1;
    private static final int ZERO = 0;

    public boolean isAtLeastAYear(long seconds) {
        return years(seconds) > 0;
    }

    public boolean isAtLeastOneDay(long seconds) {
        return days(seconds) > ZERO;
    }

    public boolean isMoreThanOneYear(long seconds) {
        return years(seconds) > ONE;
    }

    public boolean isMoreThanOneDay(long seconds) {
        return days(seconds) > ONE;
    }

    public boolean isLessThanTwoHoursDigits(long seconds) {
        return shownHours(seconds) < TWO_DIGITS;
    }

    public boolean isLessThanTwoMinuteDigits(long seconds) {
        return shownMinutes(seconds) < TWO_DIGITS;
    }

    public boolean isLessThanTwoSecondDigits(long seconds) {
        return shownSeconds(seconds) < TWO_DIGITS;
    }

    public long years(long seconds) {
        return seconds / MINUTES / HOURS / DAYS / YEARS;
    }

    public long days(long seconds) {
        return seconds / MINUTES / HOURS / DAYS;
    }

    public long shownDays(long seconds) {
        return days(seconds) % DAYS_REMAINDER;
    }

    public long shownHours(long seconds) {
        return seconds / MINUTES / HOURS % HOURS_REMAINDER;
    }

    public long shownMinutes(long seconds) {
        return seconds / MINUTES % MINUTES_REMAINDER;
    }

    public long shownSeconds(long seconds) {
        return seconds % SECOND_REMAINDER;
    }
}
