package com.zlotran.happyhours.ui.combobox;

import java.time.Month;

import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.ui.bar.MonthAverageBar;
import com.zlotran.happyhours.ui.bar.MonthTotalBar;
import com.zlotran.happyhours.ui.refresher.MonthAverageRefresher;
import com.zlotran.happyhours.ui.refresher.MonthTotalRefresher;

public class MonthsBox extends Box<Month> {

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
        removeArrow();
        this.setBounds(130, 130, 100,25);
        setItems();
        setSelectedItem(recordStatisticsController.getLatestMonth());
        this.addActionListener(e -> action());
    }

    public MonthsBox() {
    }

    public void action() {
        monthAverageBar.setRefresher(new MonthAverageRefresher(recordStatisticsController, (Month)getSelectedItem(), (String)yearsBox.getSelectedItem()));
        monthTotalBar.setRefresher(new MonthTotalRefresher(recordStatisticsController, (Month)getSelectedItem(), (String)yearsBox.getSelectedItem()));
    }

    @Override public void refresh() {

    }

    void setItems() {
        this.recordStatisticsController.getRecordedMonths((String)yearsBox.getSelectedItem()).forEach(this::addItem);
    }

    void resetItems() {
        Object selectedItem = this.getSelectedItem();
        this.removeAllItems();
        this.recordStatisticsController.getRecordedMonths((String)yearsBox.getSelectedItem()).forEach(this::addItem);
        if (selectedItem != null) {
            this.setSelectedItem(selectedItem);
        }
    }
}
