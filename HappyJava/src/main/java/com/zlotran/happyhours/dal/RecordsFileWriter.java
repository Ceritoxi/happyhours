package com.zlotran.happyhours.dal;

import java.io.FileWriter;
import java.io.IOException;

import com.zlotran.happyhours.Application;
import com.zlotran.happyhours.config.GeneralConfig;

public class RecordsFileWriter {

    /**
     * Appends a raw record to the default datafile.
     * Validity check is not enforced here.
     * @param record
     */
    void addRecord(final String record) {
        try {
            doAddRecord(record);
        } catch (IOException e) {
            System.err.println("Can't write the file for some reason");
        }
    }

    private void doAddRecord(final String record) throws IOException {
        final FileWriter writer = new FileWriter(GeneralConfig.getInstance().getConfig("datafile"), true);
        writer.write(record + "\n");
        writer.close();
    }
}
