package com.zlotran.happyhours.service;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.format.TimeFormatter;

public class RecordStatisticsService {

    private RecordDao recordDao;
    private TimeFormatter timeFormatter;

    public RecordStatisticsService(RecordDao recordDao, TimeFormatter timeFormatter) {
        this.recordDao = recordDao;
        this.timeFormatter = timeFormatter;
    }

    public String currentMonthTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(calculateTotalInSecond(recordDao.getRecordsForCurrentMonth()));
    }

    public long currentMonthTotalInSeconds() {
        return calculateTotalInSecond(recordDao.getRecordsForCurrentMonth());
    }

    public String currentDayTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(calculateTotalInSecond(recordDao.getRecordsForCurrentDay()));
    }

    public long currentDayTotalInSeconds() {
        return calculateTotalInSecond(recordDao.getRecordsForCurrentDay());
    }

    public String allTimeTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(calculateTotalInSecond(recordDao.getRecords()));
    }

    public long allTimeTotalInSeconds() {
        return calculateTotalInSecond(recordDao.getRecords());
    }

    public String averageOfCurrentMonthFormatted() {
        return timeFormatter.formatTimeFromSeconds(calculateAverage(recordDao.getRecordsForCurrentMonth()));
    }

    public long averageOfCurrentMonthInSeconds() {
        return calculateAverage(recordDao.getRecordsForCurrentMonth());
    }

    public String allTimeAverageFormatted() {
        return timeFormatter.formatTimeFromSeconds(calculateAverage(recordDao.getRecords()));
    }

    public long allTimeAverageInSeconds() {
        return calculateAverage(recordDao.getRecords());
    }

    private long calculateAverage(List<Record> records) {
        long averageDivisor = records.stream().map(record -> record.getDate().toLocalDate()).distinct().count();
        if (averageDivisor > 0) {
            return calculateTotalInSecond(records) / averageDivisor;
        }
        return 0;
    }

    private long calculateTotalInSecond(List<Record> records) {
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
