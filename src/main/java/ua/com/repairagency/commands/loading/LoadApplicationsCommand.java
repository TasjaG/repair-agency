package ua.com.repairagency.commands.loading;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ChangeLocalizationService;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.LoadListService.loadApplications;

/** Class for the load applications command. */
public class LoadApplicationsCommand implements ICommand {

    private static final String PARAM_NAME_USER_ROLE = "user_type";

    /** Loads applications. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String userType = null;

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            // only the manager can see applications
            if ((userType != null) && (userType.equals("manager"))) {
                loadApplications(request);
                page = config.getProperty(ConfigurationManager.APPLICATIONS_PAGE);
            } else {
                request.setAttribute("error",
                        ChangeLocalizationService.getAttribute(session,"illegalAccessErrorMessage"));
                page = config.getInstance().getProperty(ConfigurationManager.ERROR_PAGE);
            }
        } else {
            page = config.getProperty(ConfigurationManager.LOGIN_PAGE);
        }

        return page;
    }
}
