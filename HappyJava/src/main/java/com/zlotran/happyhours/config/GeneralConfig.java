package com.zlotran.happyhours.config;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlotran.happyhours.App;

public class GeneralConfig {

    private volatile static GeneralConfig generalConfig;

    private boolean configMapIsUpToDate;
    private Map<String, String> configMap;

    private GeneralConfig() {
        this.configMapIsUpToDate = false;
        this.configMap = new HashMap<>();
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
            configMapIsUpToDate = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Config file not found, falling back to default.");
            configMap = FallbackGeneralConfig.getFallbackGeneralConfig();
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
     * @param key
     * @return
     */
    public Integer getNumericConfig(String key) {
        if (!configMapIsUpToDate) {
            initConfig();
        }
        return Integer.valueOf(configMap.get(key));
    }

    private String readInRawJSON() throws IOException {
        FileReader reader = new FileReader(App.CONFIG_FILE_NAME);
        StringBuilder rawConfigJSONBuilder = new StringBuilder();
        int inputChar;
        while ((inputChar = reader.read()) != -1) {
            rawConfigJSONBuilder.append((char) inputChar);
        }
        return rawConfigJSONBuilder.toString();
    }
}
