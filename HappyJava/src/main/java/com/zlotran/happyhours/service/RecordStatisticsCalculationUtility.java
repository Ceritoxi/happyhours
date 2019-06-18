package com.zlotran.happyhours.service;

import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.supplier.CalendarSupplier;

public class RecordStatisticsCalculationUtility {

    private static final long ZERO = 0;

    private CalendarSupplier calendarSupplier;

    public RecordStatisticsCalculationUtility(final CalendarSupplier calendarSupplier) {
        this.calendarSupplier = calendarSupplier;
    }

    public long calculateAverageInSeconds(final List<Record> records) {
        final long averageDivisor = calculateAverageDivisor(records);
        if (averageDivisor > 0) {
            return calculateTotalInSecond(records) / averageDivisor;
        } else {
            return ZERO;
        }
    }

    private long calculateAverageDivisor(final List<Record> records) {
        List<Record> distinctResults = new ArrayList<>();
        for (Record record : records) {
            boolean foundDistinct = true;
            for (Record distinctResult : distinctResults) {
                if (distinctResult.onSameDay(record)) {
                    foundDistinct = false;
                }
            }
            if (foundDistinct) {
                distinctResults.add(record);
            }
        }
        return distinctResults.size();
    }

    public long calculateTotalInSecond(final List<Record> records) {
        long startEpochSum = ZERO;
        long endEpochSum = ZERO;
        for (final Record record : records) {
            if (record.getState().equals(State.START)) {
                startEpochSum += record.getEpoch();
            } else if (record.getState().equals(State.END)) {
                endEpochSum += record.getEpoch();
            }
        }
        endEpochSum += plusTimeInCaseOfOngoingRecord(records);
        return endEpochSum - startEpochSum;
    }

    private long plusTimeInCaseOfOngoingRecord(final List<Record> records) {
        return lastState(records).equals(State.START) ? calendarSupplier.get().getTimeInMillis() / 1000 : ZERO;
    }

    private State lastState(final List<Record> records) {
        if (records.isEmpty()) {
            return State.END;
        } else {
            return records.get(records.size() - 1).getState();
        }
    }

}
