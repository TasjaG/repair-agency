package ua.com.repairagency.services;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.ICommentDAO;
import ua.com.repairagency.dao.interfaces.IUserDAO;

import java.sql.SQLException;

public class SubmitCommentService {

    public static void submitComment(String text, String userName) {
        ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();

        // TODO remove when Comment is changed along with table in db

        int userId = 0;
        try {
            userId = userDAO.getIdByLogin(userName);
        } catch (SQLException e) {
            // TODO Logger
        }

        Comment comment = new Comment(text, userId);

        try {
            commentDAO.addComment(comment);
        } catch (SQLException e) {
            // TODO logger
        }
    }
}
