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
import com.zlotran.happyhours.ui.bar.RefreshableBar;
import com.zlotran.happyhours.ui.bar.ThisMonthAverageBar;
import com.zlotran.happyhours.ui.bar.ThisMonthTotalBar;
import com.zlotran.happyhours.ui.bar.TodaysTotalBar;
import com.zlotran.happyhours.ui.button.LogADayButton;
import com.zlotran.happyhours.ui.refresher.AllTimeAverageLabelRefresher;
import com.zlotran.happyhours.ui.refresher.AllTimeTotalLabelRefresher;
import com.zlotran.happyhours.ui.refresher.ThisMonthAverageLabelRefresher;
import com.zlotran.happyhours.ui.refresher.ThisMonthTotalLabelRefresher;
import com.zlotran.happyhours.ui.refresher.TodaysTotalLabelRefresher;
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
        screen.add(new AllTimeAverageBar(new AllTimeAverageLabelRefresher(recordStatisticsController)));
        screen.add(new AllTimeTotalBar(new AllTimeTotalLabelRefresher(recordStatisticsController)));
        screen.add(new ThisMonthAverageBar(new ThisMonthAverageLabelRefresher(recordStatisticsController)));
        screen.add(new ThisMonthTotalBar(new ThisMonthTotalLabelRefresher(recordStatisticsController)));
        screen.add(new TodaysTotalBar(new TodaysTotalLabelRefresher(recordStatisticsController)));
        screen.add(new LogADayButton(e -> recordInsertionController.logADay()));
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
                final List<Config> outdatedConfigs = collectOutdatedConfigs();
                if (outdatedConfigs != null && !outdatedConfigs.isEmpty()) {
                    try {
                        sleep(500);//don't do this kids
                    } catch (InterruptedException e) {
                        System.err.println("Interrupted yoohoo");
                    }
                    outdatedConfigs.forEach(Config::resetConfig);
                }
            }
        }

        private List<Config> collectOutdatedConfigs() {
            return configs.stream().filter(config -> !config.isFresh()).collect(Collectors.toList());
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
                sleep(100);
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
