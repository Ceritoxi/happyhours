package com.zlotran.happyhours.transform;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.zlotran.happyhours.validation.RecordValidator;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.domain.Record;

public class RecordTransformer {

    RecordValidator recordValidator;

    public RecordTransformer(RecordValidator recordValidator) {
        this.recordValidator = recordValidator;
    }

    private static final int YEAR_INDEX = 0;
    private static final int MONTH_INDEX = 1;
    private static final int DAY_INDEX = 2;
    private static final int HOUR_INDEX = 3;
    private static final int MINUTE_INDEX = 4;
    private static final int SECOND_INDEX = 5;
    private static final int STATE_INDEX = 6;

    public List<Record> convertToRecords(List<String> rawRecords) {
        return rawRecords.stream().filter(rawRecord -> recordValidator.isValid(rawRecord)).map(this::convertToRecord).collect(Collectors.toList());
    }

    private Record convertToRecord(String rawRecord) {
        Record result = new Record();
        String[] recordPieces = rawRecord.split(" ");
        result.setDate(LocalDateTime
            .of(Integer.parseInt(recordPieces[YEAR_INDEX]), Integer.parseInt(recordPieces[MONTH_INDEX]), Integer.parseInt(recordPieces[DAY_INDEX]),
                Integer.parseInt(recordPieces[HOUR_INDEX]), Integer.parseInt(recordPieces[MINUTE_INDEX]), Integer.parseInt(recordPieces[SECOND_INDEX])));
        result.setState(State.valueOf(recordPieces[STATE_INDEX]));
        return result;
    }

    public String convertToRawRecord(Record record) {
        return record.getDate().getYear() + " "
            + record.getDate().getMonthValue() + " "
            + record.getDate().getDayOfMonth() + " "
            + record.getDate().getHour() + " "
            + record.getDate().getMinute() + " "
            + record.getDate().getSecond() + " "
            + record.getState();
    }
}
