package ua.com.repairagency.services;

import ua.com.repairagency.properties.LocalizationManager;

import javax.servlet.http.HttpSession;

/** Service class for changing locale. */
public class ChangeLocalizationService {

    // Acronyms used for languages, these have to match throughout the project.
    private static final String LOCALE_ACRONYM_EN = "EN";
    private static final String LOCALE_ACRONYM_UK = "UK";

    /** Changes locale to specified. */
    public static void setLocale(HttpSession session, String locale) {

        LocalizationManager localizationService = null;

        // changes locale to Ukrainian is locale equals LOCALE_ACRONYM_UK, otherwise to English
        if (LOCALE_ACRONYM_UK.equalsIgnoreCase(locale)) {
            session.setAttribute("locale", LOCALE_ACRONYM_UK);
            localizationService = LocalizationManager.getInstance(LOCALE_ACRONYM_UK);
        } else {
            session.setAttribute("locale", LOCALE_ACRONYM_EN);  // default locale
            localizationService = LocalizationManager.getInstance(LOCALE_ACRONYM_EN);
        }

        // common
        session.setAttribute("localeEN",
                localizationService.getProperty(localizationService.LOCALE_EN_LINK));
        session.setAttribute("localeUK",
                localizationService.getProperty(localizationService.LOCALE_UK_LINK));
        session.setAttribute("logoutLink",
                localizationService.getProperty(localizationService.LOGOUT_LINK));
        session.setAttribute("helloUserLabel",
                localizationService.getProperty(localizationService.HELLO_USER_LABEL));

        // login.jsp
        session.setAttribute("loginTitle",
                localizationService.getProperty(localizationService.LOGIN_TITLE));
        session.setAttribute("loginLabelLogin",
                localizationService.getProperty(localizationService.LOGIN_LABEL_LOGIN));
        session.setAttribute("passwordLabelLogin",
                localizationService.getProperty(localizationService.PASSWORD_LABEL_LOGIN));
        session.setAttribute("loginButton",
                localizationService.getProperty(localizationService.LOGIN_BUTTON));
        session.setAttribute("registerButton",
                localizationService.getProperty(localizationService.REGISTER_BUTTON));

        // register.jsp
        session.setAttribute("registerTitle",
                localizationService.getProperty(localizationService.REGISTER_TITLE));
        session.setAttribute("loginLabelRegister",
                localizationService.getProperty(localizationService.LOGIN_LABEL_REGISTER));
        session.setAttribute("passwordLabelRegister",
                localizationService.getProperty(localizationService.PASSWORD_LABEL_REGISTER));
        session.setAttribute("repeatPasswordLabel",
                localizationService.getProperty(localizationService.REPEAT_PASSWORD_LABEL));
        session.setAttribute("firstNameLabel",
                localizationService.getProperty(localizationService.FIRST_NAME_LABEL));
        session.setAttribute("middleNameLabel",
                localizationService.getProperty(localizationService.MIDDLE_NAME_LABEL));
        session.setAttribute("lastNameLabel",
                localizationService.getProperty(localizationService.LAST_NAME_LABEL));
        session.setAttribute("emailLabel",
                localizationService.getProperty(localizationService.EMAIL_LABEL));
        session.setAttribute("phoneNumberLabel",
                localizationService.getProperty(localizationService.PHONE_NUMBER_LABEL));
        session.setAttribute("submitButtonRegister",
                localizationService.getProperty(localizationService.SUBMIT_BUTTON_REGISTER));

        // main.jsp
        session.setAttribute("mainTitle",
                localizationService.getProperty(localizationService.MAIN_TITLE));
        session.setAttribute("welcome_label",
                localizationService.getProperty(localizationService.WELCOME_LABEL));
        session.setAttribute("mainLink",
                localizationService.getProperty(localizationService.MAIN_LINK));
        session.setAttribute("commentsLink",
                localizationService.getProperty(localizationService.COMMENTS_LINK));
        session.setAttribute("leaveRequestLink",
                localizationService.getProperty(localizationService.LEAVE_REQUEST_LINK));
        session.setAttribute("applicationsLink",
                localizationService.getProperty(localizationService.APPLICATIONS_LINK));
        session.setAttribute("requestsLink",
                localizationService.getProperty(localizationService.REQUESTS_LINK));

        // comments.jsp
        session.setAttribute("commentsTitle",
                localizationService.getProperty(localizationService.COMMENTS_TITLE));
        session.setAttribute("userIdTableHeaderComments",
                localizationService.getProperty(localizationService.USER_ID_TABLE_HEADER_COMMENTS));
        session.setAttribute("commentTableHeader",
                localizationService.getProperty(localizationService.COMMENT_TABLE_HEADER));

        session.setAttribute("enterCommentLabel",
                localizationService.getProperty(localizationService.ENTER_COMMENT_LABEL));
        session.setAttribute("commentButton",
                localizationService.getProperty(localizationService.COMMENT_BUTTON));

        // leave_request.jsp
        session.setAttribute("leaveRequestTitle",
                localizationService.getProperty(localizationService.LEAVE_REQUEST_TITLE));
        session.setAttribute("productNameLabel",
                localizationService.getProperty(localizationService.PRODUCT_NAME_LABEL));
        session.setAttribute("additionalInfoLabel",
                localizationService.getProperty(localizationService.ADDITIONAL_INFO_LABEL));
        session.setAttribute("submitButtonLeaveRequest",
                localizationService.getProperty(localizationService.SUBMIT_BUTTON_LEAVE_REQUEST));

        // applications.jsp
        session.setAttribute("applicationTitle",
                localizationService.getProperty(localizationService.APPLICATIONS_TITLE));
        session.setAttribute("userIdTableHeaderApplications",
                localizationService.getProperty(localizationService.USER_ID_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("productNameTableHeaderApplications",
                localizationService.getProperty(localizationService.PRODUCT_NAME_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("userCommentTableHeaderApplications",
                localizationService.getProperty(localizationService.USER_COMMENT_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("dateAddedTableHeader",
                localizationService.getProperty(localizationService.DATE_ADDED_TABLE_HEADER));
        session.setAttribute("statusTableHeaderApplications",
                localizationService.getProperty(localizationService.STATUS_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("reasonRejectedTableHeader",
                localizationService.getProperty(localizationService.REASON_REJECTED_TABLE_HEADER));
        session.setAttribute("dateProcessedTableHeader",
                localizationService.getProperty(localizationService.DATE_PROCESSED_TABLE_HEADER));
        session.setAttribute("rejectTableHeader",
                localizationService.getProperty(localizationService.REJECT_TABLE_HEADER));
        session.setAttribute("acceptTableHeader",
                localizationService.getProperty(localizationService.ACCEPT_TABLE_HEADER));
        session.setAttribute("rejectButton",
                localizationService.getProperty(localizationService.REJECT_BUTTON));
        session.setAttribute("acceptButton",
                localizationService.getProperty(localizationService.ACCEPT_BUTTON));

        // accepted_application.jsp
        session.setAttribute("requestsTitle",
                localizationService.getProperty(localizationService.REQUESTS_TITLE));
        session.setAttribute("userIdTableHeaderAcceptedApps",
                localizationService.getProperty(localizationService.USER_ID_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("userCommentTableHeaderAcceptedApps",
                localizationService.getProperty(localizationService.USER_COMMENT_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("productNameTableHeaderAcceptedApps",
                localizationService.getProperty(localizationService.PRODUCT_NAME_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("userCommentTableHeaderAcceptedApps",
                localizationService.getProperty(localizationService.USER_COMMENT_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("priceTableHeader",
                localizationService.getProperty(localizationService.PRICE_TABLE_HEADER));
        session.setAttribute("statusTableHeaderAcceptedApps",
                localizationService.getProperty(localizationService.STATUS_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("dateCompletedTableHeader",
                localizationService.getProperty(localizationService.DATE_COMPLETED_TABLE_HEADER));
        session.setAttribute("completeTableHeader",
                localizationService.getProperty(localizationService.COMPLETE_TABLE_HEADER));
        session.setAttribute("completeButton",
                localizationService.getProperty(localizationService.COMPLETE_BUTTON));

        // error.jsp
        session.setAttribute("errorTitle",
                localizationService.getProperty(localizationService.ERROR_TITLE));
        session.setAttribute("servletExceptionMessage",
                localizationService.getProperty(localizationService.SERVLET_EXCEPTION_MESSAGE));
        session.setAttribute("IOExceptionMessage",
                localizationService.getProperty(localizationService.IO_EXCEPTION_MESSAGE));
        session.setAttribute("loginErrorMessage",
                localizationService.getProperty(localizationService.LOGIN_ERROR_MESSAGE));
        session.setAttribute("usernameUnavailableMessage",
                localizationService.getProperty(localizationService.USERNAME_UNAVAILABLE_MESSAGE));
        session.setAttribute("passwordsDoNotMatchMessage",
                localizationService.getProperty(localizationService.PASSWORDS_DO_NOT_MATCH_MESSAGE));
        session.setAttribute("illegalAccessErrorMessage",
                localizationService.getProperty(localizationService.ILLEGAL_ACCESS_ERROR_MESSAGE));
    }

    //TODO commands errors & commands and jsp localization & UK localization & JS

    /** Retrieves the specified attribute from user's current session.
     *
     * @param session user's current session
     * @param attribute the attribute to retrieve
     * @return the value of the specified attribute
     */
    public static String getAttribute(HttpSession session, String attribute) {
        return (String) session.getAttribute(attribute);
    }
}
