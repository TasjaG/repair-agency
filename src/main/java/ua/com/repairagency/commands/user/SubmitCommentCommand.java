package ua.com.repairagency.commands.user;

import ua.com.repairagency.commands.interfaces.ICommand;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.ICommentDAO;
import ua.com.repairagency.dao.interfaces.IUserDAO;
import ua.com.repairagency.services.ConfigurationManagerService;
import ua.com.repairagency.services.MessageManagerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import static ua.com.repairagency.services.LoadCommentsService.loadComments;
import static ua.com.repairagency.services.SubmitCommentService.submitComment;

// TODO
public class SubmitCommentCommand implements ICommand {

    // TODO change to user
    private static final String PARAM_NAME_USER_NAME = "user";
    private static final String PARAM_NAME_COMMENT_TEXT = "comment_text";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        //try {
            // get userId

        // TODO doesn't get the param here!
            String userName = request.getParameter(PARAM_NAME_USER_NAME);
            String text = request.getParameter(PARAM_NAME_COMMENT_TEXT);

        submitComment(text, userName);
            loadComments(request);

            request.setAttribute("user", userName);

            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.COMMENTS_PAGE);
        //} catch (SQLException ex) {

            // TODO Logger

            // TODO change to SQL_EXCEPTION_MESSAGE
            //request.setAttribute("errorMessage",
           //         MessageManagerService.getInstance().getProperty(MessageManagerService.IO_EXCEPTION_MESSAGE));
          //  page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        //}

        return page;
    }
}
