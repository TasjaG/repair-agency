package ua.com.repairagency.commands.user;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ChangeLocalizationService;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.SubmitFormService.submitApplication;

/** Class for the submit application command. */
public class SubmitApplicationCommand implements ICommand {

    private static final String PARAM_NAME_USER_NAME = "user";
    private static final String PARAM_NAME_USER_ROLE = "user_type";
    private static final String PARAM_NAME_PRODUCT_NAME = "product_name";
    private static final String PARAM_NAME_PRODUCT_COMMENT = "product_comment";

    /** Submits user's application for repairs. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String userName = null;
        String userType = null;

        String productName = request.getParameter(PARAM_NAME_PRODUCT_NAME);
        String productComment = request.getParameter(PARAM_NAME_PRODUCT_COMMENT);

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userName = (String) session.getAttribute(PARAM_NAME_USER_NAME);
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            // only a user can submit applications
            if ((userType != null) && (userType.equals("user"))) {
                submitApplication(productName, productComment, userName);
                page = config.getProperty(ConfigurationManager.MAIN_PAGE);
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
