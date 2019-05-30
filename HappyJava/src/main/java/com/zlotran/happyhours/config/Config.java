package com.zlotran.happyhours.config;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zlotran.happyhours.config.fallback.FallbackGeneralConfig;

public abstract class Config {

    static final String CONFIG_ROOT = "configs/";
    private final Map<String, String> fallbackMap;

    private File configFile;
    private boolean configMapIsUpToDate;
    private Map<String, String> configMap;

    Config(String configFileName, Map<String, String> fallbackMap) {
        this.configMap = new HashMap<>();
        this.configMapIsUpToDate = false;
        this.configFile = new File(configFileName);
        this.fallbackMap = fallbackMap;
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
            writeOutFallbackToFile();
            configMap = FallbackGeneralConfig.getFallbackGeneralConfig();
        } finally {
            configMapIsUpToDate = true;
        }
    }

    public void reload() {
        initConfig();
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

    private void writeOutFallbackToFile() {
        ObjectMapper objectMapper = new ObjectMapper();
        new File(CONFIG_ROOT).mkdir();
        try(FileWriter writer = new FileWriter(configFile)) {
            String jsonResult = objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(fallbackMap);
            writer.write(jsonResult);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
