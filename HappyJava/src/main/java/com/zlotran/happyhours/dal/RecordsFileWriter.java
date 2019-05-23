package com.zlotran.happyhours.dal;

import java.io.FileWriter;
import java.io.IOException;

import com.zlotran.happyhours.App;

public class RecordsFileWriter {

    /**
     * Appends a raw record to the default datafile.
     * Validity check is not enforced here.
     * @param record
     */
    void addRecord(String record) {
        try {
            doAddRecord(record);
        } catch (IOException e) {
            System.err.println("Can't write the file for some reason");
            e.printStackTrace();
        }
    }

    private void doAddRecord(String record) throws IOException {
        FileWriter writer = new FileWriter(App.DATA_FILE_NAME, true);
        writer.write(record + "\n");
        writer.close();
    }
}
