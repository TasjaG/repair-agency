package ua.com.repairagency.commands.manager;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.LoadListService.loadApplications;
import static ua.com.repairagency.services.ProcessApplicationService.acceptApplication;

/** Class for the accept application command. */
public class AcceptApplicationCommand implements ICommand {

    private static final String PARAM_NAME_USER_ROLE = "user_type";
    private static final String PARAM_NAME_APPLICATION_ID = "application_id";
    private static final String PARAM_NAME_PRICE = "price";

    /** Accepts an application, while setting the price of the repair work. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String userType = null;

        int id = Integer.parseInt(request.getParameter(PARAM_NAME_APPLICATION_ID));
        double price = Double.parseDouble(request.getParameter(PARAM_NAME_PRICE));

        ConfigurationManagerService config = ConfigurationManagerService.getInstance();
        MessageManagerService messages = MessageManagerService.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            // only the manager can accept applications
            if ((userType != null) && (userType.equals("manager"))) {
                acceptApplication(id, price);

                // TODO do it differently
                loadApplications(request);
                page = config.getProperty(ConfigurationManagerService.APPLICATIONS_PAGE);
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
