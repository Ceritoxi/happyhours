package com.zlotran.happyhours.ui.combobox;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import com.zlotran.happyhours.controller.RecordStatisticsController;
import com.zlotran.happyhours.ui.bar.MonthAverageBar;
import com.zlotran.happyhours.ui.bar.MonthTotalBar;
import com.zlotran.happyhours.ui.refresher.MonthAverageRefresher;
import com.zlotran.happyhours.ui.refresher.MonthTotalRefresher;

public class YearsBox extends Box<String> {

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
        removeArrow();
        this.setBounds(20, 130, 100, 25);
        this.setItems();
        setSelectedItem(recordStatisticsController.getLatestYear());
        this.addActionListener(e -> action());
    }

    public void setMonthsBox(MonthsBox monthsBox) {
        this.monthsBox = monthsBox;
    }

    public void action() {
        monthAverageBar.setRefresher(new MonthAverageRefresher(recordStatisticsController, (Month)monthsBox.getSelectedItem(), (String)getSelectedItem()));
        monthTotalBar.setRefresher(new MonthTotalRefresher(recordStatisticsController, (Month)monthsBox.getSelectedItem(), (String)getSelectedItem()));
        monthsBox.resetItems();
    }

    @Override
    public void refresh() {
        List<String> recordedYears = this.recordStatisticsController.getRecordedYears();
        if (!recordedYears.equals(this.getItems())) {
            this.removeAllItems();
            recordedYears.forEach(this::addItem);
            action();
        }
    }

    private List<String> getItems() {
        List<String> items = new ArrayList<>();
        for (int i = 0; i < this.getItemCount(); i++) {
            items.add(this.getItemAt(i));
        }
        return items;
    }

    void setItems() {
        this.recordStatisticsController.getRecordedYears().forEach(this::addItem);
    }

    @Override public Object getSelectedItem() {
        return super.getSelectedItem();
    }



}
