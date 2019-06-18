package com.zlotran.happyhours.supplier;

import java.util.Calendar;
import java.util.function.Supplier;

import com.zlotran.happyhours.domain.Month;

public class CalendarSupplier implements Supplier<Calendar> {

    @Override public Calendar get() {
        return Calendar.getInstance();
    }

    public Month getCurrentMonth() {
        return Month.values()[get().get(Calendar.MONTH)];
    }

    public int getCurrentYear() {
        return get().get(Calendar.YEAR);
    }

    public int getCurrentDay() {
        return get().get(Calendar.DAY_OF_MONTH);
    }
}
