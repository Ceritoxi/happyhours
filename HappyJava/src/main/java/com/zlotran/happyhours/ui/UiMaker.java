package com.zlotran.happyhours.ui;

import com.zlotran.happyhours.controller.RecordInsertionController;
import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.ui.refresher.AllTimeAverageLabelRefresher;
import com.zlotran.happyhours.ui.refresher.AllTimeTotalLabelRefresher;
import com.zlotran.happyhours.ui.refresher.ThisMonthAverageLabelRefresher;
import com.zlotran.happyhours.ui.refresher.ThisMonthTotalLabelRefresher;
import com.zlotran.happyhours.ui.refresher.TodaysTotalLabelRefresher;
import com.zlotran.happyhours.ui.bar.AllTimeAverageBar;
import com.zlotran.happyhours.ui.bar.AllTimeTotalBar;
import com.zlotran.happyhours.ui.bar.RefreshableBar;
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
    private RecordStatisticsController recordStatisticsController;
    private Screen screen;

    public UiMaker(RecordInsertionController recordInsertionController, RecordStatisticsController recordStatisticsController) {
        this.recordInsertionController = recordInsertionController;
        this.recordStatisticsController = recordStatisticsController;
    }

    public void drawUI() {
        screen = new Screen();
        placeComponentsOnScreen(screen);
        screen.revealScreen();
    }

    public void startRefreshing() {
        if (screen != null) {
            UIRefresher uiRefresher = new UIRefresher(screen);
            uiRefresher.start();
        }
    }

    private void placeComponentsOnScreen(Screen screen) {
        screen.add(new AllTimeAverageBar(screen.getHeight(), screen.getWidth(), new AllTimeAverageLabelRefresher(recordStatisticsController)));
        screen.add(new AllTimeTotalBar(screen.getHeight(), screen.getWidth(), new AllTimeTotalLabelRefresher(recordStatisticsController)));
        screen.add(new ThisMonthAverageBar(screen.getHeight(), screen.getWidth(), new ThisMonthAverageLabelRefresher(recordStatisticsController)));
        screen.add(new ThisMonthTotalBar(screen.getHeight(), screen.getWidth(), new ThisMonthTotalLabelRefresher(recordStatisticsController)));
        screen.add(new TodaysTotalBar(screen.getHeight(), screen.getWidth(), new TodaysTotalLabelRefresher(recordStatisticsController)));
        screen.add(new LogADayButton(screen.getHeight(), screen.getWidth(), e -> recordInsertionController.logADay()));
    }

    private static class UIRefresher extends Thread {

        private Screen screen;

        private UIRefresher(Screen screen) {
            super();
            this.screen = screen;
        }

        @Override public void run() {
            while (true) {
                refreshRefreshableBars();
            }
        }

        private void refreshRefreshableBars() {
            for (RefreshableBar bar : screen.getRefreshableBars()) {
                bar.refresh();
            }
        }
    }

}
