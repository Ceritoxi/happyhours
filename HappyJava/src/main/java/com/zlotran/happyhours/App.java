package com.zlotran.happyhours;

import javax.swing.JFrame;
import javax.swing.JProgressBar;

import com.zlotran.happyhours.controller.RecordInsertionController;
import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.dal.RecordsFileReader;
import com.zlotran.happyhours.dal.RecordsFileWriter;
import com.zlotran.happyhours.format.TimeFormatter;
import com.zlotran.happyhours.format.TimeFormatterUtil;
import com.zlotran.happyhours.supplier.LocalDateTimeSupplier;
import com.zlotran.happyhours.service.RecordInsertionService;
import com.zlotran.happyhours.service.RecordStatisticsCalculationUtility;
import com.zlotran.happyhours.service.RecordStatisticsService;
import com.zlotran.happyhours.transform.RecordTransformer;
import com.zlotran.happyhours.ui.UiMaker;
import com.zlotran.happyhours.validation.RecordValidator;

public class App {
    public static final String DATA_FILE_NAME = "datafile";
    public static final String CONFIG_FILE_NAME = "config.cfg";

    public static void main(String[] args) {
        RecordsFileReader recordsFileReader = new RecordsFileReader();
        RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
        RecordValidator recordValidator = new RecordValidator();
        RecordTransformer recordTransformer = new RecordTransformer(recordValidator);
        RecordDao recordDao = new RecordDao(recordsFileReader, recordsFileWriter, recordValidator, recordTransformer);
        TimeFormatterUtil timeFormatterUtil = new TimeFormatterUtil();
        TimeFormatter timeFormatter = new TimeFormatter(timeFormatterUtil);
        LocalDateTimeSupplier localDateTimeSupplier = new LocalDateTimeSupplier();
        RecordStatisticsCalculationUtility recordStatisticsCalculationUtility = new RecordStatisticsCalculationUtility(localDateTimeSupplier);
        RecordStatisticsService recordStatisticsService = new RecordStatisticsService(recordDao, timeFormatter, recordStatisticsCalculationUtility);
        RecordInsertionService recordInsertionService = new RecordInsertionService(recordDao);
        RecordInsertionController recordInsertionController = new RecordInsertionController(recordInsertionService);
        RecordStatisticsController recordStatisticsController = new RecordStatisticsController(recordStatisticsService);
        UiMaker uiMaker = new UiMaker(recordInsertionController, recordStatisticsController);
        uiMaker.drawUI();
        uiMaker.startRefreshing();
    }

}
