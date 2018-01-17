package ua.com.repairagency.commands.user;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static ua.com.repairagency.services.SubmitCommentService.submitComment;

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

        ConfigurationManagerService config = ConfigurationManagerService.getInstance();
        MessageManagerService messages = MessageManagerService.getInstance();
        HttpSession session = request.getSession(false);

        // if no session exists, user is redirected to login page
        if (session != null) {
            userName = (String) session.getAttribute(PARAM_NAME_USER_NAME);
            userType = (String) session.getAttribute(PARAM_NAME_USER_ROLE);

            if (userType != null) {
                submitComment(text, userName);

                // TODO loadComments(request);
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
