package com.zlotran.happyhours.ui;

import java.time.Month;

import javax.swing.JComboBox;

import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.ui.bar.MonthAverageBar;
import com.zlotran.happyhours.ui.bar.MonthTotalBar;
import com.zlotran.happyhours.ui.refresher.MonthAverageRefresher;
import com.zlotran.happyhours.ui.refresher.MonthTotalRefresher;

public class MonthsBox extends JComboBox<Month> {

    private YearsBox yearsBox;
    private RecordStatisticsController recordStatisticsController;
    private MonthAverageBar monthAverageBar;
    private MonthTotalBar monthTotalBar;

    public MonthsBox(YearsBox yearsBox, RecordStatisticsController recordStatisticsController, MonthAverageBar monthAverageBar, MonthTotalBar monthTotalBar) {
        super();
        this.yearsBox = yearsBox;
        this.recordStatisticsController = recordStatisticsController;
        this.monthAverageBar = monthAverageBar;
        this.monthTotalBar = monthTotalBar;
        this.setBounds(60, 160, 100,50);
        setItems();
        setSelectedItem(recordStatisticsController.getLatestMonth());
        this.addActionListener(e -> action());
    }

    public MonthsBox() {
    }

    private void action() {
        monthAverageBar.setRefresher(new MonthAverageRefresher(recordStatisticsController, (Month)getSelectedItem(), (String)yearsBox.getSelectedItem()));
        monthTotalBar.setRefresher(new MonthTotalRefresher(recordStatisticsController, (Month)getSelectedItem(), (String)yearsBox.getSelectedItem()));
    }

    private void setItems() {
        this.recordStatisticsController.getRecordedMonths((String)yearsBox.getSelectedItem()).forEach(this::addItem);
    }

    void resetItems() {
        this.removeAllItems();
        this.recordStatisticsController.getRecordedMonths((String)yearsBox.getSelectedItem()).forEach(this::addItem);
    }
}
