package com.zlotran.happyhours.format;

public class TimeFormatter {

    public String formatTimeFromSeconds(long seconds) {
        StringBuilder result = new StringBuilder();
        if (seconds / 60 / 60 / 24 / 365 > 0) {
            if (seconds / 60 / 60 / 24 / 365 > 1) {
                result.append(seconds / 60 / 60 / 24 / 365).append(" Years, ");
            } else {
                result.append(seconds / 60 / 60 / 24 / 365).append(" Year, ");
            }
        }
        if (seconds / 60 / 60 / 24 > 0) {
            if (seconds / 60 / 60 / 24 > 1) {
                result.append(seconds / 60 / 60 / 24 % 365).append(" Days, ");
            } else {
                result.append(seconds / 60 / 60 / 24 % 365).append(" Day, ");
            }
        }
        if (seconds / 60 / 60 % 24 < 10) {
            result.append("0").append(seconds / 60 / 60 % 24).append(":");
        } else {
            result.append(seconds / 60 / 60 % 24).append(":");
        }
        if (seconds / 60 % 60 < 10) {
            result.append("0").append(seconds / 60 % 60).append(":");
        } else {
            result.append(seconds / 60 % 60).append(":");
        }
        if (seconds % 60 < 10) {
            result.append("0").append(seconds % 60);
        } else {
            result.append(seconds % 60);
        }
        return result.toString();
    }
}
