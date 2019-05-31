package com.zlotran.happyhours.validation;

import java.util.List;
import java.util.stream.Collectors;

public class RecordValidator {

    public boolean isValid(final String record) {
        return record.matches("[0-9]{4} [0-9]{1,2} [0-9]{1,2} [0-9]{1,2} [0-9]{1,2} [0-9]{1,2} (START|END)");
    }

    public List<String> filterOutIllegalRecords(final List<String> records) {
        return records.stream().filter(this::isValid).collect(Collectors.toList());
    }
}
