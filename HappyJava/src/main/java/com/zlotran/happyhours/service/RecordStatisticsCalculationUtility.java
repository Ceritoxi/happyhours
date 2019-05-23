package com.zlotran.happyhours.service;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;

public class RecordStatisticsCalculationUtility {

    public long calculateAverage(List<Record> records) {
        long averageDivisor = records.stream().map(record -> record.getDate().toLocalDate()).distinct().count();
        if (averageDivisor > 0) {
            return calculateTotalInSecond(records) / averageDivisor;
        }
        return 0;
    }

    public long calculateTotalInSecond(List<Record> records) {
        long startEpochSum = 0;
        long endEpochSum = 0;
        for (Record record : records) {
            if (record.getState().equals(State.START)) {
                startEpochSum += record.getEpoch();
            } else if (record.getState().equals(State.END)){
                endEpochSum += record.getEpoch();
            }
        }
        if (!lastState(records).equals(State.END)) {
            endEpochSum += LocalDateTime.now().toEpochSecond(ZoneOffset.UTC);
        }
        return endEpochSum - startEpochSum;
    }

    private State lastState(List<Record> records) {
        if (records.size() > 0) {
            return records.get(records.size() - 1).getState();
        } else {
            return State.END;
        }
    }

}
