package ua.com.repairagency.services;

import ua.com.repairagency.properties.LocalizationManager;

import javax.servlet.http.HttpSession;

/** Service class for changing locale. */
public class ChangeLocalizationService {

    // Acronyms used for languages, these have to match throughout the project.
    private static final String LOCALE_ACRONYM_EN = "EN";
    private static final String LOCALE_ACRONYM_UK = "UK";

    /** Changes locale to specified. */
    public static void setLocale(HttpSession session, String newLocale) {

        LocalizationManager localizationManager = null;

        // changes locale to Ukrainian is locale equals LOCALE_ACRONYM_UK, otherwise to English
        if (LOCALE_ACRONYM_UK.equalsIgnoreCase(newLocale)) {
            session.setAttribute("locale", LOCALE_ACRONYM_UK);
            localizationManager = LocalizationManager.getInstance(LOCALE_ACRONYM_UK);
        } else {
            session.setAttribute("locale", LOCALE_ACRONYM_EN);  // default locale
            localizationManager = LocalizationManager.getInstance(LOCALE_ACRONYM_EN);
        }

        // common
        session.setAttribute("localeENLink",
                localizationManager.getProperty(LocalizationManager.LOCALE_EN_LINK));
        session.setAttribute("localeUKLink",
                localizationManager.getProperty(LocalizationManager.LOCALE_UK_LINK));
        session.setAttribute("logoutLink",
                localizationManager.getProperty(LocalizationManager.LOGOUT_LINK));
        session.setAttribute("helloUserLabel",
                localizationManager.getProperty(LocalizationManager.HELLO_USER_LABEL));

        // login.jsp
        session.setAttribute("loginTitle",
                localizationManager.getProperty(LocalizationManager.LOGIN_TITLE));
        session.setAttribute("loginLabelLogin",
                localizationManager.getProperty(LocalizationManager.LOGIN_LABEL_LOGIN));
        session.setAttribute("passwordLabelLogin",
                localizationManager.getProperty(LocalizationManager.PASSWORD_LABEL_LOGIN));
        session.setAttribute("loginButton",
                localizationManager.getProperty(LocalizationManager.LOGIN_BUTTON));
        session.setAttribute("registerButton",
                localizationManager.getProperty(LocalizationManager.REGISTER_BUTTON));

        // register.jsp
        session.setAttribute("registerTitle",
                localizationManager.getProperty(LocalizationManager.REGISTER_TITLE));
        session.setAttribute("loginLabelRegister",
                localizationManager.getProperty(LocalizationManager.LOGIN_LABEL_REGISTER));
        session.setAttribute("passwordLabelRegister",
                localizationManager.getProperty(LocalizationManager.PASSWORD_LABEL_REGISTER));
        session.setAttribute("repeatPasswordLabel",
                localizationManager.getProperty(LocalizationManager.REPEAT_PASSWORD_LABEL));
        session.setAttribute("firstNameLabel",
                localizationManager.getProperty(LocalizationManager.FIRST_NAME_LABEL));
        session.setAttribute("middleNameLabel",
                localizationManager.getProperty(LocalizationManager.MIDDLE_NAME_LABEL));
        session.setAttribute("lastNameLabel",
                localizationManager.getProperty(LocalizationManager.LAST_NAME_LABEL));
        session.setAttribute("emailLabel",
                localizationManager.getProperty(LocalizationManager.EMAIL_LABEL));
        session.setAttribute("phoneNumberLabel",
                localizationManager.getProperty(LocalizationManager.PHONE_NUMBER_LABEL));
        session.setAttribute("submitButtonRegister",
                localizationManager.getProperty(LocalizationManager.SUBMIT_BUTTON_REGISTER));

        // main.jsp
        session.setAttribute("mainTitle",
                localizationManager.getProperty(LocalizationManager.MAIN_TITLE));
        session.setAttribute("welcome_label",
                localizationManager.getProperty(LocalizationManager.WELCOME_LABEL));
        session.setAttribute("mainLink",
                localizationManager.getProperty(LocalizationManager.MAIN_LINK));
        session.setAttribute("commentsLink",
                localizationManager.getProperty(LocalizationManager.COMMENTS_LINK));
        session.setAttribute("leaveRequestLink",
                localizationManager.getProperty(LocalizationManager.LEAVE_REQUEST_LINK));
        session.setAttribute("applicationsLink",
                localizationManager.getProperty(LocalizationManager.APPLICATIONS_LINK));
        session.setAttribute("requestsLink",
                localizationManager.getProperty(LocalizationManager.REQUESTS_LINK));

        // comments.jsp
        session.setAttribute("commentsTitle",
                localizationManager.getProperty(LocalizationManager.COMMENTS_TITLE));
        session.setAttribute("userIdTableHeaderComments",
                localizationManager.getProperty(LocalizationManager.USER_ID_TABLE_HEADER_COMMENTS));
        session.setAttribute("commentTableHeader",
                localizationManager.getProperty(LocalizationManager.COMMENT_TABLE_HEADER));

        session.setAttribute("enterCommentLabel",
                localizationManager.getProperty(LocalizationManager.ENTER_COMMENT_LABEL));
        session.setAttribute("commentButton",
                localizationManager.getProperty(LocalizationManager.COMMENT_BUTTON));

        // leave_request.jsp
        session.setAttribute("leaveRequestTitle",
                localizationManager.getProperty(LocalizationManager.LEAVE_REQUEST_TITLE));
        session.setAttribute("productNameLabel",
                localizationManager.getProperty(LocalizationManager.PRODUCT_NAME_LABEL));
        session.setAttribute("additionalInfoLabel",
                localizationManager.getProperty(LocalizationManager.ADDITIONAL_INFO_LABEL));
        session.setAttribute("submitButtonLeaveRequest",
                localizationManager.getProperty(LocalizationManager.SUBMIT_BUTTON_LEAVE_REQUEST));

        // applications.jsp
        session.setAttribute("applicationTitle",
                localizationManager.getProperty(LocalizationManager.APPLICATIONS_TITLE));
        session.setAttribute("userIdTableHeaderApplications",
                localizationManager.getProperty(LocalizationManager.USER_ID_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("productNameTableHeaderApplications",
                localizationManager.getProperty(LocalizationManager.PRODUCT_NAME_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("userCommentTableHeaderApplications",
                localizationManager.getProperty(LocalizationManager.USER_COMMENT_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("dateAddedTableHeader",
                localizationManager.getProperty(LocalizationManager.DATE_ADDED_TABLE_HEADER));
        session.setAttribute("statusTableHeaderApplications",
                localizationManager.getProperty(LocalizationManager.STATUS_TABLE_HEADER_APPLICATIONS));
        session.setAttribute("reasonRejectedTableHeader",
                localizationManager.getProperty(LocalizationManager.REASON_REJECTED_TABLE_HEADER));
        session.setAttribute("dateProcessedTableHeader",
                localizationManager.getProperty(LocalizationManager.DATE_PROCESSED_TABLE_HEADER));
        session.setAttribute("rejectTableHeader",
                localizationManager.getProperty(LocalizationManager.REJECT_TABLE_HEADER));
        session.setAttribute("acceptTableHeader",
                localizationManager.getProperty(LocalizationManager.ACCEPT_TABLE_HEADER));
        session.setAttribute("rejectButton",
                localizationManager.getProperty(LocalizationManager.REJECT_BUTTON));
        session.setAttribute("acceptButton",
                localizationManager.getProperty(LocalizationManager.ACCEPT_BUTTON));

        // accepted_application.jsp
        session.setAttribute("requestsTitle",
                localizationManager.getProperty(LocalizationManager.REQUESTS_TITLE));
        session.setAttribute("userIdTableHeaderAcceptedApps",
                localizationManager.getProperty(LocalizationManager.USER_ID_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("userCommentTableHeaderAcceptedApps",
                localizationManager.getProperty(LocalizationManager.USER_COMMENT_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("productNameTableHeaderAcceptedApps",
                localizationManager.getProperty(LocalizationManager.PRODUCT_NAME_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("userCommentTableHeaderAcceptedApps",
                localizationManager.getProperty(LocalizationManager.USER_COMMENT_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("priceTableHeader",
                localizationManager.getProperty(LocalizationManager.PRICE_TABLE_HEADER));
        session.setAttribute("statusTableHeaderAcceptedApps",
                localizationManager.getProperty(LocalizationManager.STATUS_TABLE_HEADER_ACCEPTED_APPS));
        session.setAttribute("dateCompletedTableHeader",
                localizationManager.getProperty(LocalizationManager.DATE_COMPLETED_TABLE_HEADER));
        session.setAttribute("completeTableHeader",
                localizationManager.getProperty(LocalizationManager.COMPLETE_TABLE_HEADER));
        session.setAttribute("completeButton",
                localizationManager.getProperty(LocalizationManager.COMPLETE_BUTTON));

        // error.jsp
        session.setAttribute("errorTitle",
                localizationManager.getProperty(LocalizationManager.ERROR_TITLE));
        session.setAttribute("servletExceptionMessage",
                localizationManager.getProperty(LocalizationManager.SERVLET_EXCEPTION_MESSAGE));
        session.setAttribute("IOExceptionMessage",
                localizationManager.getProperty(LocalizationManager.IO_EXCEPTION_MESSAGE));
        session.setAttribute("loginErrorMessage",
                localizationManager.getProperty(LocalizationManager.LOGIN_ERROR_MESSAGE));
        session.setAttribute("usernameUnavailableMessage",
                localizationManager.getProperty(LocalizationManager.USERNAME_UNAVAILABLE_MESSAGE));
        session.setAttribute("passwordsDoNotMatchMessage",
                localizationManager.getProperty(LocalizationManager.PASSWORDS_DO_NOT_MATCH_MESSAGE));
        session.setAttribute("illegalAccessErrorMessage",
                localizationManager.getProperty(LocalizationManager.ILLEGAL_ACCESS_ERROR_MESSAGE));
    }

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