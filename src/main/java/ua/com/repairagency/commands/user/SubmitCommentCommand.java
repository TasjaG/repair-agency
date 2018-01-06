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

// TODO
public class SubmitCommentCommand implements ICommand {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException,
            IOException {
        String page = null;

        DAOFactory daoFactory = new DAOFactory();
        ICommentDAO commentDAO = daoFactory.getMySQLCommentDAO();
        IUserDAO userDAO = daoFactory.getMySQLUserDAO();

        try {
            // get userId
            String login = request.getParameter("current_user");
            String text = request.getParameter("comment_text");

            // TODO get user if vie select were login=?, then get id of that user

            //User user = userDAO.getUser(userId);
            int userId = Integer.valueOf(request.getParameter("current_user"));

            Comment comment = new Comment(text, userId);
            commentDAO.addComment(comment);

            // TODO different page
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.MAIN_PAGE);
        } catch (SQLException ex) {

            // TODO Logger
            System.out.println(ex);

            // TODO change to SQL_EXCEPTION_MESSAGE
            request.setAttribute("errorMessage",
                    MessageManagerService.getInstance().getProperty(MessageManagerService.IO_EXCEPTION_MESSAGE));
            page = ConfigurationManagerService.getInstance().getProperty(ConfigurationManagerService.ERROR_PAGE);
        }

        return page;
    }
}
