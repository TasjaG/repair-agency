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

    private static final String PARAM_NAME_NEW_LOCALE = "newLocale";
    private static final String PARAM_NAME_CURRENT_PAGE = "currentPage";

    /** Changes locale. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        String newLocale = request.getParameter(PARAM_NAME_NEW_LOCALE);
        String currentPage = request.getParameter(PARAM_NAME_CURRENT_PAGE);

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            setLocale(session, newLocale);

            // the page gets reloaded after changing locale
            //String uri = request.getRequestURI();
            //page = uri.substring(uri.lastIndexOf("/"));

            // check to prevent looping
            //if (page.equalsIgnoreCase("/Controller")) {
            //    page = config.getProperty(ConfigurationManager.LOGIN_PAGE);
            //}
            page = "/"+currentPage;
        } else {
            page = config.getProperty(ConfigurationManager.LOGIN_PAGE);
        }

        return page;
    }
}