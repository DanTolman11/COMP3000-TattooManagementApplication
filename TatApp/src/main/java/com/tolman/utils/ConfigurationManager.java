package com.tolman.utils;

import com.tolman.model.database.DBConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Properties;

public class ConfigurationManager {
    private static ConfigurationManager INSTANCE;
    Properties properties;

    private ConfigurationManager() throws IOException, URISyntaxException {
        properties = new Properties();
        URL url = DBConnection.class.getResource("/conf/application.properties");
        properties.load(new FileInputStream(new File(url.toURI()).getAbsolutePath()));
    }

    public String getStringProperty(String propertyName){
        return properties.getProperty(propertyName);
    }

    public int getIntProperty(String propertyName) {
        try {
            return Integer.parseInt(properties.getProperty(propertyName));
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public boolean getBoolProperty(String propertyName) {
            return Boolean.parseBoolean(properties.getProperty(propertyName));
    }


    public static ConfigurationManager getInstance() {
        if(INSTANCE == null) {
            try {
                INSTANCE = new ConfigurationManager();
            } catch (IOException | URISyntaxException e) {
                e.printStackTrace();
                System.exit(1);
            }
        }

        return INSTANCE;
    }
}
