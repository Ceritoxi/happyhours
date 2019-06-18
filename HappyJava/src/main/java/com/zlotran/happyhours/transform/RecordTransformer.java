package com.zlotran.happyhours.transform;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.validation.RecordValidator;

public class RecordTransformer {

    private static final int YEAR_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int DAY_INDEX = 2;
    private static final int HOUR_INDEX = 3;
    private static final int MINUTE_INDEX = 4;
    private static final int SECOND_INDEX = 5;
    private static final int STATE_INDEX = 6;

    private RecordValidator recordValidator;

    public RecordTransformer(final RecordValidator recordValidator) {
        this.recordValidator = recordValidator;
    }

    public List<Record> convertToRecords(final List<String> rawRecords) {
        return rawRecords.stream().filter(rawRecord -> recordValidator.isValid(rawRecord)).map(this::convertToRecord).collect(Collectors.toList());
    }

    private Record convertToRecord(final String rawRecord) {
        final Record result = new Record();
        final String[] recordPieces = rawRecord.split(" ");

        Calendar calendar = Calendar.getInstance();
        calendar.set(Integer.parseInt(recordPieces[YEAR_INDEX]), Integer.parseInt(recordPieces[MONTH_INDEX]) - 1, Integer.parseInt(recordPieces[DAY_INDEX]),
            Integer.parseInt(recordPieces[HOUR_INDEX]), Integer.parseInt(recordPieces[MINUTE_INDEX]), Integer.parseInt(recordPieces[SECOND_INDEX]));
        result.setDate(calendar);
        result.setState(State.valueOf(recordPieces[STATE_INDEX]));
        return result;
    }

    public String convertToRawRecord(final Record record) {
        return record.getYear() + " "
            + record.getMonthValue() + " "
            + record.getDay() + " "
            + record.getHour() + " "
            + record.getMinute() + " "
            + record.getSecond() + " "
            + record.getState();
    }
}
