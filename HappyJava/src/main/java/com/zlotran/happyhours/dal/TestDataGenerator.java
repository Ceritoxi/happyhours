package com.zlotran.happyhours.dal;

import java.time.LocalDateTime;

import com.zlotran.happyhours.domain.Record;
import com.zlotran.happyhours.domain.State;

public class TestDataGenerator {

    private RecordDao recordDao;

    public TestDataGenerator(RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public void generateLargeDatafile() {
        for (int i = 0; i < 10000; i++) {
            Record record1 = new Record();
            Record record2 = new Record();
            record1.setState(State.START);
            record2.setState(State.END);
            record1.setDate(LocalDateTime.of(2019,6,4,9,0).minusDays(i));
            record2.setDate(LocalDateTime.of(2019,6,4,17,0).minusDays(i).plusMinutes((int)(Math.random() * 60)));
            recordDao.addRecord(record1);
            recordDao.addRecord(record2);
        }
    }
}
