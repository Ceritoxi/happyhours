package com.zlotran.happyhours.service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.domain.Month;
import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.format.TimeFormatter;

public class RecordStatisticsService {

    private RecordDao recordDao;
    private TimeFormatter timeFormatter;
    private RecordStatisticsCalculationUtility recordStatisticsCalculationUtility;

    public RecordStatisticsService(final RecordDao recordDao, final TimeFormatter timeFormatter, final RecordStatisticsCalculationUtility recordStatisticsCalculationUtility) {
        this.recordDao = recordDao;
        this.timeFormatter = timeFormatter;
        this.recordStatisticsCalculationUtility = recordStatisticsCalculationUtility;
    }

    public String currentMonthTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentMonth()));
    }

    public long currentMonthTotalInSeconds() {
        return recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentMonth());
    }

    public String currentDayTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentDay()));
    }

    public long currentDayTotalInSeconds() {
        return recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForCurrentDay());
    }

    public String allTimeTotalFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecords()));
    }

    public long allTimeTotalInSeconds() {
        return recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecords());
    }

    public String averageOfCurrentMonthFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecordsForCurrentMonth()));
    }

    public long averageOfCurrentMonthInSeconds() {
        return recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecordsForCurrentMonth());
    }

    public String allTimeAverageFormatted() {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecords()));
    }

    public long allTimeAverageInSeconds() {
        return recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecords());
    }

    public List<String> getRecordedYears() {
        return recordDao.getRecords().stream().map(Record::getYear).distinct().map(i -> i + "").collect(Collectors.toList());
    }

    public List<Month> getRecordedMonthsOfYear(int year) {
        return recordDao.getRecords().stream().filter(r -> r.getYear() == year).map(Record::getMonth).distinct().sorted().collect(Collectors.toList());
    }

    public String monthOfYearAverage(int year, Month month) {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecordsForMonthInYear(year, month)));
    }

    public String monthOfYearTotal(int year, Month month) {
        return timeFormatter.formatTimeFromSeconds(recordStatisticsCalculationUtility.calculateTotalInSecond(recordDao.getRecordsForMonthInYear(year, month)));
    }

    public long monthOfYearAverageInSec(int year, Month month) {
        return recordStatisticsCalculationUtility.calculateAverageInSeconds(recordDao.getRecordsForMonthInYear(year, month));
    }

    public Month getLatestMonth() {
        return getRecordedMonthsOfYear(Integer
            .valueOf(getLatestYear()))
            .stream()
            .max(Enum::compareTo)
            .orElse(null);

    }

    public String getLatestYear() {
        return getRecordedYears().stream().map(Integer::valueOf).max(Comparator.comparing(Integer::valueOf)).map(Object::toString).orElse("-1");
    }
}
