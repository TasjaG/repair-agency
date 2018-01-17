package ua.com.repairagency.commands.loading;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.LoadListService.loadComments;

/** Class for the load comments command. */
public class LoadCommentsCommand implements ICommand {

    private static final String PARAM_NAME_USER_ROLE = "user_type";

    /** Loads comments. */
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

            if (userType != null) {

                // TODO this should be done differently
                loadComments(request);
                page = config.getProperty(ConfigurationManagerService.COMMENTS_PAGE);
            } else {
                request.setAttribute("error",
                        messages.getProperty(MessageManagerService.ILLEGAL_ACCESS_ERROR_MESSAGE));
                page = config.getProperty(ConfigurationManagerService.ERROR_PAGE);
            }
        } else {
            page = config.getProperty(ConfigurationManagerService.LOGIN_PAGE);
        }

        return page;
    }
}
