package com.zlotran.happyhours;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.WindowConstants;

import com.zlotran.happyhours.dal.RecordDao;
import com.zlotran.happyhours.dal.RecordsFileReader;
import com.zlotran.happyhours.dal.RecordsFileWriter;
import com.zlotran.happyhours.format.TimeFormatter;
import com.zlotran.happyhours.service.RecordStatisticsService;
import com.zlotran.happyhours.transform.RecordTransformer;
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
        TimeFormatter timeFormatter = new TimeFormatter();
        RecordStatisticsService recordStatisticsService = new RecordStatisticsService(recordDao, timeFormatter);

        frame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        allTimeTotal.setBounds(20, (FRAME_HEIGHT / 3) - (TEXT_BOX_HEIGHT / 2) - ((FRAME_HEIGHT / 3) / 2), TEXT_BOX_WIDTH, TEXT_BOX_HEIGHT);
        allTimeTotal.setStringPainted(true);
        allTimeTotal.setValue(1);
        frame.add(allTimeTotal);

        allTimeAverage.setBounds(FRAME_WIDTH - (40 + TEXT_BOX_WIDTH), (FRAME_HEIGHT / 3) - (TEXT_BOX_HEIGHT / 2) - ((FRAME_HEIGHT / 3) / 2), TEXT_BOX_WIDTH,
            TEXT_BOX_HEIGHT);
        allTimeAverage.setStringPainted(true);
        allTimeAverage.setValue(1);
        frame.add(allTimeAverage);

        thisMonthTotal.setBounds(20, (2 * (FRAME_HEIGHT / 3)) - (TEXT_BOX_HEIGHT / 2) - ((FRAME_HEIGHT / 3) / 2), TEXT_BOX_WIDTH, TEXT_BOX_HEIGHT);
        thisMonthTotal.setStringPainted(true);
        thisMonthTotal.setValue(1);
        frame.add(thisMonthTotal);

        thisMonthAverage
            .setBounds(FRAME_WIDTH - (40 + TEXT_BOX_WIDTH), (2 * (FRAME_HEIGHT / 3)) - (TEXT_BOX_HEIGHT / 2) - ((FRAME_HEIGHT / 3) / 2), TEXT_BOX_WIDTH,
                TEXT_BOX_HEIGHT);
        thisMonthAverage.setStringPainted(true);
        thisMonthAverage.setValue(0);
        frame.add(thisMonthAverage);

        todayTotal
            .setBounds((FRAME_WIDTH / 2) - (TEXT_BOX_WIDTH / 2), (3 * (FRAME_HEIGHT / 3)) - (TEXT_BOX_HEIGHT / 2) - ((FRAME_HEIGHT / 3) / 2), TEXT_BOX_WIDTH,
                TEXT_BOX_HEIGHT);
        todayTotal.setStringPainted(true);
        todayTotal.setValue(0);
        frame.add(todayTotal);

        JButton logADayButton = new JButton("Log a day");
        logADayButton.setBounds(20 + TEXT_BOX_WIDTH / 4, (FRAME_HEIGHT / 3) - (TEXT_BOX_HEIGHT / 2) - ((FRAME_HEIGHT / 3) / 2) - (TEXT_BOX_HEIGHT * 4), TEXT_BOX_WIDTH / 2,
            TEXT_BOX_HEIGHT * 3);
        logADayButton.addActionListener(e -> recordDao.addRecordOfCurrentDate());
        frame.add(logADayButton);

        frame.setLayout(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        Repeater repeater = new Repeater(recordStatisticsService);
        repeater.start();
    }

    private static class Repeater extends Thread {

        RecordStatisticsService recordStatisticsService;

        Repeater(RecordStatisticsService recordStatisticsService) {
            this.recordStatisticsService = recordStatisticsService;
        }

        @Override public void run() {
            while (true) {
                String allTimeTotalS = "All time total:\t\t" + recordStatisticsService.allTimeTotalFormatted();
                String allTimeAverageS = "All time average:\t" + recordStatisticsService.allTimeAverageFormatted();
                String thisMonthAverageS = "This month average:\t" + recordStatisticsService.averageOfCurrentMonthFormatted();
                String thisMonthTotalS = "This month total:\t" + recordStatisticsService.currentMonthTotalFormatted();
                String todayTotalS = "Today total:\t" + recordStatisticsService.currentDayTotalFormatted();
                long currentDayTotal = recordStatisticsService.currentDayTotalInSeconds();
                long currentMonthAverage = recordStatisticsService.averageOfCurrentMonthInSeconds();
                long allTimeAvarageSeconds = recordStatisticsService.allTimeAverageInSeconds();
                allTimeTotal.setString(allTimeTotalS);
                allTimeAverage.setString(allTimeAverageS);
                thisMonthTotal.setString(thisMonthTotalS);
                thisMonthAverage.setString(thisMonthAverageS);
                thisMonthAverage.setValue((int) (((double) currentMonthAverage / (double) allTimeAvarageSeconds) * 1000D));
                todayTotal.setString(todayTotalS);
                todayTotal.setValue((int) (((double) currentDayTotal / (double) currentMonthAverage) * 1000D));
            }
        }
    }
}
