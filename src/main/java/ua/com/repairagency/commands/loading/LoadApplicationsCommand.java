package ua.com.repairagency.commands.loading;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

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

        ConfigurationManagerService config = ConfigurationManagerService.getInstance();
        MessageManagerService messages = MessageManagerService.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            // only the manager can see applications
            if ((userType != null) && (userType.equals("manager"))) {
                loadApplications(request);
                page = config.getProperty(ConfigurationManagerService.APPLICATIONS_PAGE);
            } else {
                request.setAttribute("error",
                        messages.getProperty(MessageManagerService.ILLEGAL_ACCESS_ERROR_MESSAGE));
                page = config.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
            }
        } else {
            page = config.getProperty(ConfigurationManagerService.LOGIN_PAGE);
        }

        return page;
    }
}
