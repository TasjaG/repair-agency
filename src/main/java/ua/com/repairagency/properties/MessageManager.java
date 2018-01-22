package ua.com.repairagency.properties;

import java.util.ResourceBundle;

/**
 * Redundant after localization.
 *
 * Singleton class for retrieving data from the "messages.properties" file.
 */
public class MessageManager {

    private static MessageManager instance;

    private ResourceBundle resource;
    private static final String BUNDLE_NAME = "messages";
    public static final String SERVLET_EXCEPTION_MESSAGE = "SERVLET_EXCEPTION_MESSAGE";
    public static final String IO_EXCEPTION_MESSAGE = "IO_EXCEPTION_MESSAGE";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String USERNAME_UNAVAILABLE_MESSAGE = "USERNAME_UNAVAILABLE_MESSAGE";
    public static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "PASSWORDS_DO_NOT_MATCH_MESSAGE";
    public static final String ILLEGAL_ACCESS_ERROR_MESSAGE = "ILLEGAL_ACCESS_ERROR_MESSAGE";

    private MessageManager() {

    }

    /** Redundant after localization. */
    public static MessageManager getInstance() {
        if (instance == null) {
            instance = new MessageManager();
            instance.resource = ResourceBundle.getBundle(BUNDLE_NAME);
        }
        return instance;
    }

    /** Redundant after localization. */
    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}