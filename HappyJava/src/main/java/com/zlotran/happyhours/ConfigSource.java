package com.zlotran.happyhours;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConfigSource {

    private volatile static ConfigSource configSource;

    private boolean configMapIsUpToDate;
    private Map<String, String> configMap;

    private ConfigSource() {
        this.configMapIsUpToDate = false;
        this.configMap = new HashMap<>();
    }

    public static ConfigSource getConfigSource() {
        if (configSource == null) {
            synchronized (ConfigSource.class) {
                if (configSource == null) {
                    configSource = new ConfigSource();
                }
            }
        }
        return configSource;
    }

    public void initConfig() {
        try {
            String rawConfigJSON = readInRawJSON();
            TypeReference<HashMap<String, String>> typeRef = new TypeReference<>() {
            };
            ObjectMapper objectMapper = new ObjectMapper();
            configMap = objectMapper.readValue(rawConfigJSON, typeRef);
            configMapIsUpToDate = true;
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Config file not found, falling back to default.");
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
