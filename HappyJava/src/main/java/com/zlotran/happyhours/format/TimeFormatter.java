package com.zlotran.happyhours.format;

/**
 * Incredible amount of logic repetition for good measures.
 */
public class TimeFormatter {

    private static final String YEARS_TEXT = " Years, ";
    private static final String YEAR_TEXT = " Year, ";
    private static final String DAYS_TEXT = " Days, ";
    private static final String DAY_TEXT = " Day, ";
    private static final String EXTRA_ZERO_DIGIT = "0";
    private static final String COLON = ":";

    private TimeFormatterUtil timeFormatterUtil;

    public TimeFormatter(TimeFormatterUtil timeFormatterUtil) {
        this.timeFormatterUtil = timeFormatterUtil;
    }

    public String formatTimeFromSeconds(long seconds) {
        StringBuilder result = new StringBuilder();
        if (timeFormatterUtil.isAtLeastAYear(seconds)) {
            appendYears(seconds, result);
        }
        if (timeFormatterUtil.isAtLeastOneDay(seconds)) {
            appendDays(seconds, result);
        }
        appendHours(seconds, result);
        appendMinutes(seconds, result);
        appendSeconds(seconds, result);
        return result.toString();
    }

    private void appendYears(long seconds, StringBuilder result) {
        result.append(timeFormatterUtil.years(seconds));
        if (timeFormatterUtil.isMoreThanOneYear(seconds)) {
            result.append(YEARS_TEXT);
        } else {
            result.append(YEAR_TEXT);
        }
    }

    private void appendDays(long seconds, StringBuilder result) {
        result.append(timeFormatterUtil.shownDays(seconds));
        if (timeFormatterUtil.isMoreThanOneDay(seconds)) {
            result.append(DAYS_TEXT);
        } else {
            result.append(DAY_TEXT);
        }
    }

    private void appendHours(long seconds, StringBuilder result) {
        if (timeFormatterUtil.isLessThanTwoHoursDigits(seconds)) {
            result.append(EXTRA_ZERO_DIGIT).append(timeFormatterUtil.shownHours(seconds)).append(COLON);
        } else {
            result.append(timeFormatterUtil.shownHours(seconds)).append(COLON);
        }
    }

    private void appendMinutes(long seconds, StringBuilder result) {
        if (timeFormatterUtil.isLessThanTwoMinuteDigits(seconds)) {
            result.append(EXTRA_ZERO_DIGIT).append(timeFormatterUtil.shownMinutes(seconds)).append(COLON);
        } else {
            result.append(timeFormatterUtil.shownMinutes(seconds)).append(COLON);
        }
    }

    private void appendSeconds(long seconds, StringBuilder result) {
        if (timeFormatterUtil.isLessThanTwoSecondDigits(seconds)) {
            result.append(EXTRA_ZERO_DIGIT).append(timeFormatterUtil.shownSeconds(seconds));
        } else {
            result.append(timeFormatterUtil.shownSeconds(seconds));
        }
    }

}
