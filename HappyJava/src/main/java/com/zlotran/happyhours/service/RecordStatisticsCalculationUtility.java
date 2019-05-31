package com.zlotran.happyhours.service;

import java.time.ZoneOffset;
import java.util.List;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.supplier.LocalDateTimeSupplier;

public class RecordStatisticsCalculationUtility {

    private static final long ZERO = 0;

    private LocalDateTimeSupplier localDateTimeSupplier;

    public RecordStatisticsCalculationUtility(final LocalDateTimeSupplier localDateTimeSupplier) {
        this.localDateTimeSupplier = localDateTimeSupplier;
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
        return records.stream().map(record -> record.getDate().toLocalDate()).distinct().count();
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
        return lastState(records).equals(State.START) ? localDateTimeSupplier.get().toEpochSecond(ZoneOffset.UTC) : ZERO;
    }

    private State lastState(final List<Record> records) {
        if (records.isEmpty()) {
            return State.END;
        } else {
            return records.get(records.size() - 1).getState();
        }
    }

}
