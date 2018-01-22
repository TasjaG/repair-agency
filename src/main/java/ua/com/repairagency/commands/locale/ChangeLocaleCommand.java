package ua.com.repairagency.commands.locale;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.ChangeLocalizationService.setLocale;

/** Class for the change locale command. */
public class ChangeLocaleCommand implements ICommand {

    private static final String PARAM_NAME_LOCALE = "locale";

    /** Changes locale. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        String locale = request.getParameter(PARAM_NAME_LOCALE);

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            setLocale(session, locale);

            // the page gets reloaded after changing locale
            page = request.getRequestURI();
        } else {
            page = config.getProperty(ConfigurationManager.LOGIN_PAGE);
        }

        return page;
    }
}
