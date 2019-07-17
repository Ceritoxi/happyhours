package com.zlotran.happyhours.dal;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zlotran.happyhours.Application;
import com.zlotran.happyhours.config.Config;
import com.zlotran.happyhours.config.GeneralConfig;

public class RecordsFileReader {

    /**
     * Read in raw records from the default datafile, without validity checking.
     * If the file doesn't exist it will fail to do so and return with an empty collection.
     * @return
     */
    List<String> readInRecords() {
        List<String> result = new ArrayList<>();
        try {
            result = doReadInRecords();
        } catch (IOException e) {
            System.err.println("Couldn't find records, the datafile probably does not exist");
        }
        return result;
    }

    private List<String> doReadInRecords() throws IOException {
        final FileReader reader = new FileReader(GeneralConfig.getInstance().getConfig("datafile"));
        final List<String> result = readTroughFile(reader);
        reader.close();
        return result;
    }

    private List<String> readTroughFile(final FileReader reader) throws IOException {
        final List<String> result = new ArrayList<>();
        StringBuilder recordBuilder = new StringBuilder();
        int inputChar;
        while ((inputChar = reader.read()) != -1) {
            recordBuilder = processChar(result, recordBuilder, (char) inputChar);
        }
        return result;
    }

    private StringBuilder processChar(final List<String> result, final StringBuilder recordBuilder, final char inputChar) {
        StringBuilder builder = recordBuilder;
        if (isStillInCurrentRow(inputChar)) {
            builder.append(inputChar);
        } else {
            result.add(builder.toString());
            builder = new StringBuilder();
        }
        return builder;
    }

    private boolean isStillInCurrentRow(final char inputChar) {
        return inputChar != '\n';
    }
}
