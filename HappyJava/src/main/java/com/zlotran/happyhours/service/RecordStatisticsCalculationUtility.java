package com.zlotran.happyhours.service;

import java.time.ZoneOffset;
import java.util.List;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.supplier.LocalDateTimeSupplier;

public class RecordStatisticsCalculationUtility {

    private static final long ZERO = 0;

    private LocalDateTimeSupplier localDateTimeSupplier;

    public RecordStatisticsCalculationUtility(LocalDateTimeSupplier localDateTimeSupplier) {
        this.localDateTimeSupplier = localDateTimeSupplier;
    }

    public long calculateAverageInSeconds(List<Record> records) {
        long averageDivisor = calculateAverageDivisor(records);
        if (averageDivisor > 0) {
            return calculateTotalInSecond(records) / averageDivisor;
        } else {
            return ZERO;
        }
    }

    private long calculateAverageDivisor(List<Record> records) {
        return records.stream().map(record -> record.getDate().toLocalDate()).distinct().count();
    }

    public long calculateTotalInSecond(List<Record> records) {
        long startEpochSum = ZERO;
        long endEpochSum = ZERO;
        for (Record record : records) {
            if (record.getState().equals(State.START)) {
                startEpochSum += record.getEpoch();
            } else if (record.getState().equals(State.END)){
                endEpochSum += record.getEpoch();
            }
        }
        endEpochSum += plusTimeInCaseOfOngoingRecord(records);
        return endEpochSum - startEpochSum;
    }

    private long plusTimeInCaseOfOngoingRecord(List<Record> records) {
        return lastState(records).equals(State.START) ? localDateTimeSupplier.get().toEpochSecond(ZoneOffset.UTC) : ZERO;
    }

    private State lastState(List<Record> records) {
        if (records.size() > 0) {
            return records.get(records.size() - 1).getState();
        } else {
            return State.END;
        }
    }

}
