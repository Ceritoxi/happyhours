package com.zlotran.happyhours.ui;

import java.time.Month;

import javax.swing.JComboBox;

import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.ui.bar.MonthAverageBar;
import com.zlotran.happyhours.ui.bar.MonthTotalBar;
import com.zlotran.happyhours.ui.refresher.MonthAverageRefresher;
import com.zlotran.happyhours.ui.refresher.MonthTotalRefresher;

public class YearsBox extends JComboBox<String> {

    private MonthsBox monthsBox;
    private RecordStatisticsController recordStatisticsController;
    private MonthAverageBar monthAverageBar;
    private MonthTotalBar monthTotalBar;

    public YearsBox(MonthsBox monthsBox, RecordStatisticsController recordStatisticsController, MonthAverageBar monthAverageBar, MonthTotalBar monthTotalBar) {
        super();
        this.monthsBox = monthsBox;
        this.recordStatisticsController = recordStatisticsController;
        this.monthAverageBar = monthAverageBar;
        this.monthTotalBar = monthTotalBar;
        this.setBounds(400, 160, 100, 50);
        setItems();
        setSelectedItem(recordStatisticsController.getLatestYear());
        this.addActionListener(e -> action());
    }

    public void setMonthsBox(MonthsBox monthsBox) {
        this.monthsBox = monthsBox;
    }

    private void action() {
        monthAverageBar.setRefresher(new MonthAverageRefresher(recordStatisticsController, (Month)monthsBox.getSelectedItem(), (String)getSelectedItem()));
        monthTotalBar.setRefresher(new MonthTotalRefresher(recordStatisticsController, (Month)monthsBox.getSelectedItem(), (String)getSelectedItem()));
        monthsBox.resetItems();
    }

    @Override public Object getSelectedItem() {
        return super.getSelectedItem();
    }

    private void setItems() {
        this.recordStatisticsController.getRecordedYears().forEach(this::addItem);
    }

}
