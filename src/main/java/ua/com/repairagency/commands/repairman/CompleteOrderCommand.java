package ua.com.repairagency.commands.repairman;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ChangeLocalizationService;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.LoadListService.loadAcceptedApps;
import static ua.com.repairagency.services.ProcessAcceptedApplicationService.completeOrder;

/** Class for the complete order command. */
public class CompleteOrderCommand implements ICommand {

    private static final String PARAM_NAME_USER_ROLE = "user_type";
    private static final String PARAM_NAME_ACCEPTED_APP_ID = "accepted_app_id";

    /** Completes an order. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String userType = null;

        int id = Integer.parseInt(request.getParameter(PARAM_NAME_ACCEPTED_APP_ID));

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            // only the repairman can complete orders
            if ((userType != null) && (userType.equals("repairman"))) {
                completeOrder(id);
                loadAcceptedApps(request);
                page = config.getProperty(ConfigurationManager.ACCEPTED_APPS_PAGE);
            } else {
                request.setAttribute("error",
                        ChangeLocalizationService.getAttribute(session,"illegalAccessErrorMessage"));
                page = config.getProperty(ConfigurationManager.ERROR_PAGE);
            }
        } else {
            page = config.getProperty(ConfigurationManager.LOGIN_PAGE);
        }

        return page;
    }
}
