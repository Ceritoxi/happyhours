package com.zlotran.happyhours.controller;

import com.zlotran.happyhours.service.RecordStatisticsService;

public class RecordStatisticsController {

    private RecordStatisticsService recordStatisticsService;

    public RecordStatisticsController(RecordStatisticsService recordStatisticsService) {
        this.recordStatisticsService = recordStatisticsService;
    }

    public String getAllTimeAverage() {
        return recordStatisticsService.allTimeAverageFormatted();
    }
}
