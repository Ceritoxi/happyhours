package com.zlotran.happyhours.service;

import com.zlotran.happyhours.dal.RecordDao;

public class RecordInsertionService {

    private RecordDao recordDao;

    public RecordInsertionService(final RecordDao recordDao) {
        this.recordDao = recordDao;
    }

    public void addCurrentTimestamp() {
        recordDao.addRecordOfCurrentDate();
    }
}
