package ua.com.repairagency.services;

import javax.servlet.http.HttpSession;

/** Service class for changing locale. */
public class ChangeLocalizationService {

    // Acronyms used for languages, these have to match throughout the project.
    private static final String LOCALE_ACRONYM_EN = "EN";
    private static final String LOCALE_ACRONYM_UK = "UK";

    /** Changes locale to specified. */
    public static void setLocale(HttpSession session, String locale) {

        LocalizationManagerService localizationService = null;

        // changes locale to Ukrainian is locale equals LOCALE_ACRONYM_UK, otherwise to English
        if (LOCALE_ACRONYM_UK.equalsIgnoreCase(locale)) {
            session.setAttribute("locale", LOCALE_ACRONYM_UK);
            localizationService = LocalizationManagerService.getInstance(LOCALE_ACRONYM_UK);
        } else {
            session.setAttribute("locale", LOCALE_ACRONYM_EN);  // default locale
            localizationService = LocalizationManagerService.getInstance(LOCALE_ACRONYM_EN);
        }

//TODO set the values + commands errors & commands and jsp localization
    }
}
