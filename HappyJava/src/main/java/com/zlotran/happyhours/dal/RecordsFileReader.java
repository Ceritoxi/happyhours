package com.zlotran.happyhours.dal;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.zlotran.happyhours.App;

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
            e.printStackTrace();
        }
        return result;
    }

    private List<String> doReadInRecords() throws IOException {
        FileReader reader = new FileReader(App.DATA_FILE_NAME);
        List<String> result = readTroughFile(reader);
        reader.close();
        return result;
    }

    private List<String> readTroughFile(FileReader reader) throws IOException {
        List<String> result = new ArrayList<>();
        StringBuilder recordBuilder = new StringBuilder();
        int inputChar;
        while ((inputChar = reader.read()) != -1) {
            recordBuilder = processChar(result, recordBuilder, (char) inputChar);
        }
        return result;
    }

    private StringBuilder processChar(List<String> result, StringBuilder recordBuilder, char inputChar) {
        if (isStillInCurrentRow(inputChar)) {
            recordBuilder.append(inputChar);
        } else {
            result.add(recordBuilder.toString());
            recordBuilder = new StringBuilder();
        }
        return recordBuilder;
    }

    private boolean isStillInCurrentRow(char inputChar) {
        return inputChar != '\n';
    }
}
