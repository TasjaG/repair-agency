package ua.com.repairagency.services;

import java.util.ResourceBundle;

/** Singleton class for retrieving data from the "messages.properties" file. */
public class MessageManagerService {

    private static MessageManagerService instance;

    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXCEPTION_MESSAGE = "SERVLET_EXCEPTION_MESSAGE";
    public static final String IO_EXCEPTION_MESSAGE = "IO_EXCEPTION_MESSAGE";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String ILLEGAL_ACCESS_ERROR_MESSAGE = "ILLEGAL_ACCESS_ERROR_MESSAGE";

    private MessageManagerService() {

    }

    public static MessageManagerService getInstance() {
        if (instance == null) {
            instance = new MessageManagerService();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
