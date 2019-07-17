package com.zlotran.happyhours;

import com.zlotran.happyhours.controller.RecordInsertionController;
import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.dal.RecordsFileReader;
import com.zlotran.happyhours.dal.RecordsFileWriter;
import com.zlotran.happyhours.format.TimeFormatter;
import com.zlotran.happyhours.format.TimeFormatterUtil;
import com.zlotran.happyhours.service.RecordInsertionService;
import com.zlotran.happyhours.service.RecordStatisticsCalculationUtility;
import com.zlotran.happyhours.service.RecordStatisticsService;
import com.zlotran.happyhours.supplier.CalendarSupplier;
import com.zlotran.happyhours.transform.RecordTransformer;
import com.zlotran.happyhours.ui.UserInterface;
import com.zlotran.happyhours.validation.RecordValidator;

public final class Application {

    private Application() {
    }

    public static void main(final String[] args) {
        final CalendarSupplier calendarSupplier = new CalendarSupplier();
        final RecordsFileReader recordsFileReader = new RecordsFileReader();
        final RecordsFileWriter recordsFileWriter = new RecordsFileWriter();
        final RecordValidator recordValidator = new RecordValidator();
        final RecordTransformer recordTransformer = new RecordTransformer(recordValidator);
        final RecordDao recordDao = new RecordDao(recordsFileReader, recordsFileWriter, recordValidator, recordTransformer, calendarSupplier);
        final TimeFormatterUtil timeFormatterUtil = new TimeFormatterUtil();
        final TimeFormatter timeFormatter = new TimeFormatter(timeFormatterUtil);
        final RecordStatisticsCalculationUtility recordStatisticsCalculationUtility = new RecordStatisticsCalculationUtility(calendarSupplier);
        final RecordStatisticsService recordStatisticsService = new RecordStatisticsService(recordDao, timeFormatter, recordStatisticsCalculationUtility);
        final RecordInsertionService recordInsertionService = new RecordInsertionService(recordDao);
        final RecordInsertionController recordInsertionController = new RecordInsertionController(recordInsertionService);
        final RecordStatisticsController recordStatisticsController = new RecordStatisticsController(recordStatisticsService);
        final UserInterface userInterface = new UserInterface(recordInsertionController, recordStatisticsController);
        userInterface.drawUI();
        userInterface.startRefreshing();
    }

}
