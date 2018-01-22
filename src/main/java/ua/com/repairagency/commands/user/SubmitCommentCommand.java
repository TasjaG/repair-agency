package ua.com.repairagency.commands.user;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ChangeLocalizationService;
import ua.com.repairagency.properties.ConfigurationManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.LoadListService.loadComments;
import static ua.com.repairagency.services.SubmitFormService.submitComment;

/** Class for the submit comment command. */
public class SubmitCommentCommand implements ICommand {

    private static final String PARAM_NAME_USER_NAME = "user";
    private static final String PARAM_NAME_USER_ROLE = "user_type";
    private static final String PARAM_NAME_COMMENT_TEXT = "comment_text";

    /** Submits a comment. */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;
        String userName = null;
        String userType = null;

        String text = request.getParameter(PARAM_NAME_COMMENT_TEXT);

        ConfigurationManager config = ConfigurationManager.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userName = (String) session.getAttribute(PARAM_NAME_USER_NAME);
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            if (userType != null) {
                submitComment(text, userName);
                loadComments(request);
                page = config.getProperty(ConfigurationManager.COMMENTS_PAGE);
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
