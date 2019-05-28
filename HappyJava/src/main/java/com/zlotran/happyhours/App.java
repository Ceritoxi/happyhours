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
    private static final int FRAME_WIDTH = 800;
    private static final int FRAME_HEIGHT = 600;
    private static final int TEXT_BOX_WIDTH = 350;
    private static final int TEXT_BOX_HEIGHT = 20;
    private static JFrame frame = new JFrame();
    private static final JProgressBar allTimeTotal = new JProgressBar(0, 1);
    private static final JProgressBar allTimeAverage = new JProgressBar(0, 1);
    private static final JProgressBar thisMonthAverage = new JProgressBar(0, 1000);
    private static final JProgressBar thisMonthTotal = new JProgressBar(0, 1);
    private static final JProgressBar todayTotal = new JProgressBar(0, 1000);

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
