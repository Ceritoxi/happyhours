package com.zlotran.happyhours.supplier;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class LocalDateTimeSupplier implements Supplier<LocalDateTime> {

    @Override public LocalDateTime get() {
        return LocalDateTime.now();
    }
}
