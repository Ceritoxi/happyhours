package com.zlotran.happyhours.config;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlotran.happyhours.App;

public class GeneralConfig {

    private volatile static GeneralConfig generalConfig;
    private static final String CONFIG_FILE_NAME = "config.cfg";
    private File configFile;
    private boolean configMapIsUpToDate;
    private Map<String, String> configMap;

    private GeneralConfig() {
        this.configMapIsUpToDate = false;
        this.configMap = new HashMap<>();
        this.configFile = new File(CONFIG_FILE_NAME);
    }

    public static GeneralConfig getInstance() {
        if (generalConfig == null) {
            synchronized (GeneralConfig.class) {
                if (generalConfig == null) {
                    generalConfig = new GeneralConfig();
                }
            }
        }
        return generalConfig;
    }

    private void initConfig() {
        try {
            String rawConfigJSON = readInRawJSON();
            TypeReference<HashMap<String, String>> typeRef = new TypeReference<>() {
            };
            ObjectMapper objectMapper = new ObjectMapper();
            configMap = objectMapper.readValue(rawConfigJSON, typeRef);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Config file not found, falling back to default.");
            configMap = FallbackGeneralConfig.getFallbackGeneralConfig();
        } finally {
            configMapIsUpToDate = true;
        }
    }

    public String getConfig(String key) {
        if (!configMapIsUpToDate) {
            initConfig();
        }
        return configMap.get(key);
    }

    /**
     * Pretty high chance of death
     */
    public Integer getNumericConfig(String key) {
        if (!configMapIsUpToDate) {
            initConfig();
        }
        return Integer.valueOf(configMap.get(key));
    }

    private String readInRawJSON() throws IOException {
        FileReader reader = new FileReader(configFile);
        StringBuilder rawConfigJSONBuilder = new StringBuilder();
        int inputChar;
        while ((inputChar = reader.read()) != -1) {
            rawConfigJSONBuilder.append((char) inputChar);
        }
        reader.close();
        return rawConfigJSONBuilder.toString();
    }
}
