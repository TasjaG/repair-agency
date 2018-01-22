package ua.com.repairagency.services;

import java.util.ResourceBundle;

/**
 * Manager service class for getting values from localization properties files.
 * Not a Singleton, however only two instances of ths class are created,
 * one for each language currently implemented.
 */
public class LocalizationManagerService {

    // Acronyms used for languages, these have to match throughout the project.
    private static final String LOCALE_ACRONYM_EN = "EN";
    private static final String LOCALE_ACRONYM_UK = "UK";

    /** Instance for English locale. */
    private static LocalizationManagerService instanceEN;

    /** Instance for Ukrainian locale. */
    private static LocalizationManagerService instanceUK;

    private static final String BUNDLE_NAME_EN = "localization_EN";
    private static final String BUNDLE_NAME_UK = "localization_UK";

    private ResourceBundle resource;

    // common
    public static final String LOCALE_EN = "LOCALE_EN";
    public static final String LOCALE_UK = "LOCALE_UK";
    public static final String LOGOUT_LINK = "LOGOUT_LINK";
    public static final String HELLO_USER_LABEL = "HELLO_USER_LABEL";

    // login.jsp
    public static final String LOGIN_TITLE = "LOGIN_TITLE";
    public static final String LOGIN_LABEL_LOGIN = "LOGIN_LABEL_LOGIN";
    public static final String PASSWORD_LABEL_LOGIN = "PASSWORD_LABEL_LOGIN";
    public static final String LOGIN_BUTTON = "LOGIN_BUTTON";
    public static final String REGISTER_BUTTON = "REGISTER_BUTTON";

    // register.jsp
    public static final String REGISTER_TITLE = "REGISTER_TITLE";
    public static final String LOGIN_LABEL_REGISTER = "LOGIN_LABEL_REGISTER";
    public static final String PASSWORD_LABEL_REGISTER = "PASSWORD_LABEL_REGISTER";
    public static final String REPEAT_PASSWORD_LABEL = "REPEAT_PASSWORD_LABEL";
    public static final String FIRST_NAME_LABEL = "FIRST_NAME_LABEL";
    public static final String MIDDLE_NAME_LABEL = "MIDDLE_NAME_LABEL";
    public static final String LAST_NAME_LABEL = "LAST_NAME_LABEL";
    public static final String EMAIL_LABEL = "EMAIL_LABEL";
    public static final String PHONE_NUMBER_LABEL = "PHONE_NUMBER_LABEL";
    public static final String SUBMIT_BUTTON_REGISTER = "SUBMIT_BUTTON_REGISTER";

    // main.jsp
    public static final String MAIN_TITLE = "MAIN_TITLE";
    public static final String WELCOME_LABEL = "WELCOME_LABEL";
    public static final String MAIN_LINK = "MAIN_LINK";
    public static final String COMMENTS_LINK = "COMMENTS_LINK";
    public static final String LEAVE_REQUEST_LINK = "LEAVE_REQUEST_LINK";
    public static final String APPLICATIONS_LINK = "APPLICATIONS_LINK";
    public static final String REQUESTS_LINK = "REQUESTS_LINK";

    // comments.jsp
    public static final String COMMENTS_TITLE = "COMMENTS_TITLE";
    public static final String USER_ID_TABLE_HEADER_COMMENTS = "USER_ID_TABLE_HEADER_COMMENTS";
    public static final String COMMENT_TABLE_HEADER = "COMMENT_TABLE_HEADER";
    public static final String ENTER_COMMENT_LABEL = "ENTER_COMMENT_LABEL";
    public static final String COMMENT_BUTTON = "COMMENT_BUTTON";
    public static final String LEAVE_REQUEST_TITLE = "LEAVE_REQUEST_TITLE";
    public static final String PRODUCT_NAME_LABEL = "PRODUCT_NAME_LABEL";
    public static final String ADDITIONAL_INFO_LABEL = "ADDITIONAL_INFO_LABEL";
    public static final String SUBMIT_BUTTON_LEAVE_REQUEST = "SUBMIT_BUTTON_LEAVE_REQUEST";

    // applications.jsp
    public static final String APPLICATIONS_TITLE = "APPLICATIONS_TITLE";
    public static final String USER_ID_TABLE_HEADER_APPLICATIONS = "USER_ID_TABLE_HEADER_APPLICATIONS";
    public static final String PRODUCT_NAME_TABLE_HEADER_APPLICATIONS = "PRODUCT_NAME_TABLE_HEADER_APPLICATIONS";
    public static final String USER_COMMENT_TABLE_HEADER_APPLICATIONS = "USER_COMMENT_TABLE_HEADER_APPLICATIONS";
    public static final String DATE_ADDED_TABLE_HEADER = "DATE_ADDED_TABLE_HEADER";
    public static final String STATUS_TABLE_HEADER_APPLICATIONS = "STATUS_TABLE_HEADER_APPLICATIONS";
    public static final String REASON_REJECTED_TABLE_HEADER = "REASON_REJECTED_TABLE_HEADER";
    public static final String DATE_PROCESSED_TABLE_HEADER = "DATE_PROCESSED_TABLE_HEADER";
    public static final String REJECT_TABLE_HEADER = "REJECT_TABLE_HEADER";
    public static final String ACCEPT_TABLE_HEADER = "ACCEPT_TABLE_HEADER";
    public static final String REJECT_BUTTON = "REJECT_BUTTON";
    public static final String ACCEPT_BUTTON = "ACCEPT_BUTTON";

    // accepted_applications.jsp
    public static final String REQUESTS_TITLE = "REQUESTS_TITLE";
    public static final String USER_ID_TABLE_HEADER_ACCEPTED_APPS = "USER_ID_TABLE_HEADER_ACCEPTED_APPS";
    public static final String PRODUCT_NAME_TABLE_HEADER_ACCEPTED_APPS = "PRODUCT_NAME_TABLE_HEADER_ACCEPTED_APPS";
    public static final String USER_COMMENT_TABLE_HEADER_ACCEPTED_APPS = "USER_COMMENT_TABLE_HEADER_ACCEPTED_APPS";
    public static final String PRICE_TABLE_HEADER = "PRICE_TABLE_HEADER";
    public static final String STATUS_TABLE_HEADER_ACCEPTED_APPS = "STATUS_TABLE_HEADER_ACCEPTED_APPS";
    public static final String DATE_COMPLETED_TABLE_HEADER = "DATE_COMPLETED_TABLE_HEADER";
    public static final String COMPLETE_TABLE_HEADER = "COMPLETE_TABLE_HEADER";
    public static final String COMPLETE_BUTTON = "COMPLETE_BUTTON";
    public static final String ERROR_TITLE = "ERROR_TITLE";

    // error_jsp
    public static final String SERVLET_EXCEPTION_MESSAGE = "SERVLET_EXCEPTION_MESSAGE";
    public static final String IO_EXCEPTION_MESSAGE = "IO_EXCEPTION_MESSAGE";
    public static final String LOGIN_ERROR_MESSAGE = "LOGIN_ERROR_MESSAGE";
    public static final String USERNAME_UNAVAILABLE_MESSAGE = "USERNAME_UNAVAILABLE_MESSAGE";
    public static final String PASSWORDS_DO_NOT_MATCH_MESSAGE = "PASSWORDS_DO_NOT_MATCH_MESSAGE";
    public static final String ILLEGAL_ACCESS_ERROR_MESSAGE = "ILLEGAL_ACCESS_ERROR_MESSAGE";

    /** Empty private constructor. */
    private LocalizationManagerService() {

    }

    /**
     * Parametrized getInstance method.
     *
     * @param locale instance's bundle's language
     * @return the instance for the specified locale
     */
    public static LocalizationManagerService getInstance(String locale) {

        /*
         * returns the instance for Ukrainian language if locale equals the acronym
         * for the Ukrainian language, otherwise returns the instance for English.
         */
        if (LOCALE_ACRONYM_UK.equalsIgnoreCase(locale)) {

            if (instanceUK == null) {
                instanceUK = new LocalizationManagerService();
                instanceUK.resource = ResourceBundle.getBundle(BUNDLE_NAME_UK);
            }
            return instanceUK;
        } else {

            if (instanceEN == null) {
                instanceEN = new LocalizationManagerService();
                instanceEN.resource = ResourceBundle.getBundle(BUNDLE_NAME_EN);
            }
            return instanceEN;
        }
    }

    /**
     * Retrieves property by name.
     *
     * @return the specified property
     */
    public String getProperty(String key) {
        return (String) resource.getObject(key);
    }
}
