package ua.com.repairagency.properties;

import java.util.ResourceBundle;

/** Singleton class for retrieving data from the "config.properties" file. */
public class ConfigurationManager {

    private static ConfigurationManager instance;

    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "ua.com.repairagency.properties.config";

    public static final String DRIVER = "DRIVER";
    public static final String URL = "URL";
    public static final String USERNAME = "USERNAME";
    public static final String PASSWORD = "PASSWORD";
    public static final String DATASOURCE = "DATASOURCE";
    public static final String TOMCAT_JNDI_NAME = "TOMCAT_JNDI_NAME";
    public static final String MAIN_PAGE = "MAIN_PAGE";
    public static final String ERROR_PAGE = "ERROR_PAGE";
    public static final String LOGIN_PAGE = "LOGIN_PAGE";

    private ConfigurationManager() {

    }

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
