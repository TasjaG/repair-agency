package ua.com.repairagency.services;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.ICommentDAO;
import ua.com.repairagency.dao.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SubmitCommentService {

    public static void submitComment(String text, String userName) {
        ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();

        // TODO remove when Comment is changed along with table in db

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;

        int userId = 0;

        // getting id of user from users table
        try {
            conn = pool.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = conn.prepareStatement("SELECT user_id FROM users WHERE user_login=?");
                preparedStatement.setString(1, userName);
                ResultSet results = null;

                try {
                    results = preparedStatement.executeQuery();

                    if (results.next()) {
                        userId = results.getInt("user_id");
                    }
                } finally {
                    if (results != null)
                        results.close();
                }
            } finally {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
        } catch (SQLException e) {

            // TODO logger
        }

        Comment comment = new Comment(text, userId);

        try {
            commentDAO.addComment(comment);
        } catch (SQLException e) {
            // TODO logger
        }
    }
}
