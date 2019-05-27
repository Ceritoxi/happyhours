package com.zlotran.happyhours.ui;

import com.zlotran.happyhours.controller.RecordInsertionController;
import com.zlotran.happyhours.service.RecordStatisticsService;
import com.zlotran.happyhours.ui.bar.AllTimeAverageBar;
import com.zlotran.happyhours.ui.bar.AllTimeTotalBar;
import com.zlotran.happyhours.ui.bar.ThisMonthAverageBar;
import com.zlotran.happyhours.ui.bar.ThisMonthTotalBar;
import com.zlotran.happyhours.ui.bar.TodaysTotalBar;
import com.zlotran.happyhours.ui.button.LogADayButton;
import com.zlotran.happyhours.ui.screen.Screen;

/**
 * I honestly can't come up with a better name.
 */
public class UiMaker {

    private RecordInsertionController recordInsertionController;
    private RecordStatisticsService recordStatisticsService;

    public UiMaker(RecordInsertionController recordInsertionController, RecordStatisticsService recordStatisticsService) {
        this.recordInsertionController = recordInsertionController;
        this.recordStatisticsService = recordStatisticsService;
    }

    public void drawUI() {
        Screen screen = new Screen();

        AllTimeAverageBar allTimeAverageBar = new AllTimeAverageBar(screen.getHeight(), screen.getWidth());
        AllTimeTotalBar allTimeTotalBar = new AllTimeTotalBar(screen.getHeight(), screen.getWidth());
        ThisMonthAverageBar thisMonthAverageBar = new ThisMonthAverageBar(screen.getHeight(), screen.getWidth());
        ThisMonthTotalBar thisMonthTotalBar = new ThisMonthTotalBar(screen.getHeight(), screen.getWidth());
        TodaysTotalBar todaysTotalBar = new TodaysTotalBar(screen.getHeight(), screen.getWidth());

        LogADayButton logADayButton = new LogADayButton(screen.getHeight(), screen.getWidth(), e -> recordInsertionController.logADay());

        screen.showMe();
    }


    /*private static class UIRefresher extends Thread {

        RecordStatisticsService recordStatisticsService;

        UIRefresher(RecordStatisticsService recordStatisticsService) {
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
                long allTimeAverageSeconds = recordStatisticsService.allTimeAverageInSeconds();
                allTimeTotal.setString(allTimeTotalS);
                allTimeAverage.setString(allTimeAverageS);
                thisMonthTotal.setString(thisMonthTotalS);
                thisMonthAverage.setString(thisMonthAverageS);
                thisMonthAverage.setValue((int) (((double) currentMonthAverage / (double) allTimeAverageSeconds) * 1000D));
                todayTotal.setString(todayTotalS);
                todayTotal.setValue((int) (((double) currentDayTotal / (double) currentMonthAverage) * 1000D));
            }
        }
    }*/

}
