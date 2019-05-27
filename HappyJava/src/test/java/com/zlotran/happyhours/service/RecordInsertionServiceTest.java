package com.zlotran.happyhours.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.zlotran.happyhours.dal.RecordDao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class RecordInsertionServiceTest {

    @InjectMocks
    private RecordInsertionService underTest;

    @Mock
    private RecordDao recordDao;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addCurrentTimestampCallsDao() {
        //GIVEN
        //WHEN
        underTest.addCurrentTimestamp();
        //THEN
        verify(recordDao, times(1)).addRecordOfCurrentDate();
    }
}
