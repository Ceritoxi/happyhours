package com.zlotran.happyhours.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zlotran.happyhours.config.BarColorConfig;
import com.zlotran.happyhours.config.BarConfig;
import com.zlotran.happyhours.config.Config;
import com.zlotran.happyhours.config.GeneralConfig;
import com.zlotran.happyhours.controller.RecordInsertionController;
import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.ui.bar.AllTimeAverageBar;
import com.zlotran.happyhours.ui.bar.AllTimeTotalBar;
import com.zlotran.happyhours.ui.bar.MonthAverageBar;
import com.zlotran.happyhours.ui.bar.MonthTotalBar;
import com.zlotran.happyhours.ui.bar.RefreshableBar;
import com.zlotran.happyhours.ui.bar.TodaysTotalBar;
import com.zlotran.happyhours.ui.button.LogADayButton;
import com.zlotran.happyhours.ui.combobox.MonthsBox;
import com.zlotran.happyhours.ui.combobox.YearsBox;
import com.zlotran.happyhours.ui.refresher.AllTimeAverageRefresher;
import com.zlotran.happyhours.ui.refresher.AllTimeTotalRefresher;
import com.zlotran.happyhours.ui.refresher.ThisMonthAverageRefresher;
import com.zlotran.happyhours.ui.refresher.ThisMonthTotalRefresher;
import com.zlotran.happyhours.ui.refresher.TodaysTotalRefresher;
import com.zlotran.happyhours.ui.screen.Screen;

/**
 * I honestly can't come up with a better name.
 */
public class UserInterface {

    private RecordInsertionController recordInsertionController;
    private RecordStatisticsController recordStatisticsController;
    private Screen screen;
    private List<Config> configs;

    public UserInterface(final RecordInsertionController recordInsertionController, final RecordStatisticsController recordStatisticsController) {
        this.recordInsertionController = recordInsertionController;
        this.recordStatisticsController = recordStatisticsController;
        setUpConfigs();
    }

    public void drawUI() {
        screen = new Screen();
        placeComponentsOnScreen(screen);
        screen.revealScreen();
    }

    public void startRefreshing() {
        if (screen != null) {
            final UIRefresher uiRefresher = new UIRefresher(screen);
            final ConfigRefresher configRefresher = new ConfigRefresher(screen, configs);
            uiRefresher.start();
            configRefresher.start();
        }
    }

    private void placeComponentsOnScreen(final Screen screen) {
        MonthAverageBar monthAverageBar = new MonthAverageBar(new ThisMonthAverageRefresher(recordStatisticsController));
        MonthTotalBar monthTotalBar = new MonthTotalBar(new ThisMonthTotalRefresher(recordStatisticsController));
        YearsBox yearsBox = new YearsBox(new MonthsBox(), recordStatisticsController, monthAverageBar, monthTotalBar);
        MonthsBox monthsBox = new MonthsBox(yearsBox, recordStatisticsController, monthAverageBar, monthTotalBar);
        yearsBox.setMonthsBox(monthsBox);
        screen.add(new AllTimeAverageBar(new AllTimeAverageRefresher(recordStatisticsController)));
        screen.add(new AllTimeTotalBar(new AllTimeTotalRefresher(recordStatisticsController)));
        screen.add(monthAverageBar);
        screen.add(monthTotalBar);
        screen.add(new TodaysTotalBar(new TodaysTotalRefresher(recordStatisticsController)));
        screen.add(new LogADayButton(e -> recordInsertionController.logADay()));
        screen.add(yearsBox);
        screen.add(monthsBox);
    }

    private void setUpConfigs() {
        configs = new ArrayList<>();
        configs.add(GeneralConfig.getInstance());
        configs.add(BarColorConfig.getInstance());
        configs.add(BarConfig.getInstance());
    }

    /**
     * some clusterfuck
     */
    private static class ConfigRefresher extends Thread {

        private Screen screen;
        private List<Config> configs;

        private ConfigRefresher(final Screen screen, final List<Config> configs) {
            super();
            this.screen = screen;
            this.configs = configs;
        }

        @Override public void run() {
            while (screen != null) {
                List<Config> outdatedConfigs = collectOutdatedConfigs();
                if (outdatedConfigs != null && !outdatedConfigs.isEmpty()) {
                    try {
                        sleep(500);//don't do this kids
                    } catch (InterruptedException e) {
                        System.err.println("Interrupted yoohoo");
                    }
                    outdatedConfigs.forEach(Config::resetConfig);
                }
                eyySlowDown();
            }
        }

        private List<Config> collectOutdatedConfigs() {
            return configs.stream().filter(config -> !config.isFresh()).collect(Collectors.toList());
        }

        private void eyySlowDown() {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                System.err.println("Interrupted yoohoo");
            }
        }
    }

    private static class UIRefresher extends Thread {

        private Screen screen;

        private UIRefresher(final Screen screen) {
            super();
            this.screen = screen;
        }

        @Override public void run() {
            while (screen != null) {
                screen.refresh();
                refreshRefreshableBars();
                eyySlowDown();
            }
        }

        private void eyySlowDown() {
            try {
                sleep(200);
            } catch (InterruptedException e) {
                System.err.println("Interrupted yoohoo");
            }
        }

        private void refreshRefreshableBars() {
            for (final RefreshableBar bar : screen.getRefreshableBars()) {
                bar.refresh();
            }
        }
    }

}
