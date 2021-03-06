package com.zlotran.happyhours.transform;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Matchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;
import com.zlotran.happyhours.validation.RecordValidator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class RecordTransformerTest {

    private static final LocalDateTime FIRST_OF_JANUARY_NINE_O_CLOCK = LocalDateTime.of(2019, Month.JANUARY, 1, 9, 0, 0);
    private static final String EXPECTED_RAW_FORMAT_OF_FIRST_OF_JANUARY_NINE_O_CLOCK_START = "2019 1 1 9 0 0 START";

    private static final String PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_START = "2019 1 1 9 0 0 START";
    private static final String PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_END = "2019 1 1 9 0 0 END";
    private static final String FAULTY_RAW_INPUT = "faulty";


    @InjectMocks
    private RecordTransformer underTest;

    @Mock
    RecordValidator recordValidator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    void canConvertToRecordFromProperRawInput() {
        final List<Record> expectedRecords = expectedRecordsOfOneProperRawRecord();
        //GIVEN
        final List<String> record = oneProperRawRecord();
        //WHEN
        when(recordValidator.isValid(Matchers.anyString())).thenReturn(true);
        final List<Record> actual = underTest.convertToRecords(record);
        //THEN
        assertEquals(expectedRecords, actual);
    }

    @Test
    void canConvertToRecordsFromMultipleProperRawInputs() {
        final List<Record> expectedRecords = expectedRecordsOfTwoProperRawRecords();
        //GIVEN
        final List<String> records = twoProperRawRecords();
        //WHEN
        when(recordValidator.isValid(Matchers.anyString())).thenReturn(true);
        final List<Record> actual = underTest.convertToRecords(records);
        //THEN
        assertEquals(expectedRecords, actual);
    }


    @Test
    void convertToRecordIgnoresFaultyRawInput() {
        final List<Record> expectedRecords = expectedRecordsOfOneProperAndOneFaultyRawRecord();
        //GIVEN
        final List<String> records = oneProperAndOneFaultyRawRecord();
        //WHEN
        when(recordValidator.isValid(FAULTY_RAW_INPUT)).thenReturn(false);
        when(recordValidator.isValid(PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_START)).thenReturn(true);
        final List<Record> actual = underTest.convertToRecords(records);
        //THEN
        assertEquals(expectedRecords, actual);
    }

    @Test
    void canConvertToRawRecord() {
        //GIVEN
        final Record testRecord = testRecord();
        //WHEN
        final String actual = underTest.convertToRawRecord(testRecord);
        //THEN
        assertEquals(EXPECTED_RAW_FORMAT_OF_FIRST_OF_JANUARY_NINE_O_CLOCK_START, actual);
    }

    private Record testRecord() {
        final Record record = new Record();
        record.setDate(FIRST_OF_JANUARY_NINE_O_CLOCK);
        record.setState(State.START);
        return record;
    }

    private List<String> oneProperRawRecord() {
        final List<String> records = new ArrayList<>();
        records.add(PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_START);
        return records;
    }

    private List<String> twoProperRawRecords() {
        final List<String> records = new ArrayList<>();
        records.add(PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_START);
        records.add(PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_END);
        return records;
    }

    private List<String> oneProperAndOneFaultyRawRecord() {
        final List<String> records = new ArrayList<>();
        records.add(PROPER_RAW_INPUT_FIRST_OF_JANUARY_NINE_O_CLOCK_START);
        records.add(FAULTY_RAW_INPUT);
        return records;
    }

    private List<Record> expectedRecordsOfOneProperRawRecord() {
        final List<Record> records = new ArrayList<>();
        final Record record = new Record();
        record.setDate(FIRST_OF_JANUARY_NINE_O_CLOCK);
        record.setState(State.START);
        records.add(record);
        return records;
    }

    private List<Record> expectedRecordsOfTwoProperRawRecords() {
        final List<Record> records = new ArrayList<>();
        final Record record1 = new Record();
        record1.setDate(FIRST_OF_JANUARY_NINE_O_CLOCK);
        record1.setState(State.START);
        final Record record2 = new Record();
        record2.setDate(FIRST_OF_JANUARY_NINE_O_CLOCK);
        record2.setState(State.END);
        records.add(record1);
        records.add(record2);
        return records;
    }

    private List<Record> expectedRecordsOfOneProperAndOneFaultyRawRecord() {
        final List<Record> records = new ArrayList<>();
        final Record record = new Record();
        record.setDate(FIRST_OF_JANUARY_NINE_O_CLOCK);
        record.setState(State.START);
        records.add(record);
        return records;
    }
}
