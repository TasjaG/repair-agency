package ua.com.repairagency.properties;

import java.util.ResourceBundle;

/** Singleton class for retrieving data from the "messages.properties" file. */
public class MessageManager {

    private static MessageManager instance;

    private ResourceBundle resource;
    //private static final String BUNDLE_NAME = "ua.com.repairagency.properties.messages";
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXCEPTION_MESSAGE = "SERVLET_EXCEPTION_MESSAGE";
    public static final String IO_EXCEPTION_MESSAGE = "IO_EXCEPTION_MESSAGE";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";

    private MessageManager() {

    }

    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
