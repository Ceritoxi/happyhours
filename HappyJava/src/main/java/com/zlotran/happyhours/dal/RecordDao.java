package com.zlotran.happyhours.dal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.exception.InvalidRecordException;
import com.zlotran.happyhours.transform.RecordTransformer;
import com.zlotran.happyhours.validation.RecordValidator;

public class RecordDao {

    private RecordsFileReader recordsFileReader;
    private RecordsFileWriter recordsFileWriter;
    private RecordValidator recordValidator;
    private RecordTransformer recordTransformer;
    private List<Record> recordCache;
    private boolean recordCacheIsValid;

    public RecordDao(final RecordsFileReader recordsFileReader, final RecordsFileWriter recordsFileWriter, final RecordValidator recordValidator, final RecordTransformer recordTransformer) {
        this.recordsFileReader = recordsFileReader;
        this.recordsFileWriter = recordsFileWriter;
        this.recordValidator = recordValidator;
        this.recordTransformer = recordTransformer;
        this.recordCache = new ArrayList<>();
        this.recordCacheIsValid = false;
    }

    public void addRecordOfCurrentDate() {
        addRecord(new Record(LocalDateTime.now(), nextState()));
    }

    private State nextState() {
        if (lastRecordedState().equals(State.END)) {
            return State.START;
        } else {
            return State.END;
        }
    }

    /**
     * Adds a new record to the database.
     * @param record
     */
    public void addRecord(final Record record) {
        if (record.getState().equals(nextState())) {
            recordsFileWriter.addRecord(recordTransformer.convertToRawRecord(record));
            recordCacheIsValid = false;
        } else {
            throw new InvalidRecordException("Record is invalid, previous status is the same as this one");
        }
    }

    /**
     * Adds a raw record to the database.
     * Invalid records cause an {@link InvalidRecordException} to occur.
     * @param rawRecord
     */
    private void addRecord(final String rawRecord) {
        if (recordValidator.isValid(rawRecord)) {
            recordsFileWriter.addRecord(rawRecord);
            recordCacheIsValid = false;
        } else {
            throw new InvalidRecordException("Record is invalid, can't write it");
        }
    }

    /**
     * Get all the records from the database.
     * Records are cached, so consequent calling of the method will likely not cause performance issues.
     * @return every {@link Record} stored in the database.
     */
    public List<Record> getRecords() {
        if (recordCacheIsValid) {
            return recordCache;
        } else {
            recordCache = recordTransformer.convertToRecords(recordsFileReader.readInRecords());
            recordCacheIsValid = true;
            return recordCache;
        }
    }

    public List<Record> getRecordsForMonth(final Month month) {
        return getRecords().stream().filter(record -> record.getDate().getMonth().equals(month)).collect(Collectors.toList());
    }

    public List<Record> getRecordsForMonth(final int monthIndex) {
        return getRecords().stream().filter(record -> record.getDate().getMonthValue() == monthIndex).collect(Collectors.toList());
    }

    public List<Record> getRecordsForYear(final int year) {
        return getRecords().stream().filter(record -> record.getDate().getYear() == year).collect(Collectors.toList());
    }

    public List<Record> getRecordsForMonthInYear(final int year, final Month month) {
        return getRecords().stream().filter(record -> record.getDate().getYear() == year && record.getDate().getMonth().equals(month)).collect(Collectors.toList());
    }

    public List<Record> getRecordsForMonthInYear(final int year, final int monthIndex) {
        return getRecords().stream().filter(record -> record.getDate().getYear() == year && record.getDate().getMonthValue() == monthIndex ).collect(Collectors.toList());
    }

    public List<Record> getRecordsForCurrentMonth() {
        return getRecords().stream().filter(record -> record.getDate().getMonth().equals(LocalDate.now().getMonth()) && record.getDate().getYear() == LocalDate.now().getYear()).collect(Collectors.toList());
    }

    public List<Record> getRecordsForCurrentDay() {
        return getRecords().stream().filter(record -> record.getDate().getDayOfMonth() == LocalDateTime.now().getDayOfMonth() && record.getDate().getMonth().equals(LocalDate.now().getMonth()) && record.getDate().getYear() == LocalDate.now().getYear()).collect(Collectors.toList());
    }

    private State lastRecordedState() {
        if (getRecords().isEmpty()) {
            return State.END;
        } else {
            return getRecords().get(getRecords().size() - 1).getState();
        }
    }

    public List<Record> getRecordsForCurrentMonthWithoutToday() {
        return getRecordsForCurrentMonth().stream().filter(record -> record.getDate().getDayOfMonth() != LocalDateTime.now().getDayOfMonth()).collect(Collectors.toList());
    }
}
