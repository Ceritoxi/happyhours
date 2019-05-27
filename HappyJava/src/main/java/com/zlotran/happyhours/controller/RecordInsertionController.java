package com.zlotran.happyhours.controller;

import com.zlotran.happyhours.service.RecordInsertionService;

public class RecordInsertionController {

    private RecordInsertionService recordInsertionService;

    public RecordInsertionController(RecordInsertionService recordInsertionService) {
        this.recordInsertionService = recordInsertionService;
    }

    public void logADay() {
        recordInsertionService.addCurrentTimestamp();
    }
}
