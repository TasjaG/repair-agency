package ua.com.repairagency.services;

import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.dao.interfaces.ICommentDAO;
import ua.com.repairagency.dao.interfaces.IUserDAO;
import ua.com.repairagency.dao.interfaces.IUserTypeDAO;

import java.sql.SQLException;

/** Service class for submitting various forms. */
public class SubmitFormService {

    /** Submits a comment. */
    public static void submitComment(String text, String userName) {
        ICommentDAO commentDAO = DAOFactory.getMySQLCommentDAO();
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();

        // TODO remove when Comment is changed along with table in db

        try {
            int userId = userDAO.getIdByLogin(userName);
            Comment comment = new Comment(text, userId);
            commentDAO.addComment(comment);
        } catch (SQLException e) {
            // TODO logger
        }
    }

    /** Submits an application. */
    public static void submitApplication(String productName, String productComment, String userName) {
        DAOFactory daoFactory = new DAOFactory();
        IApplicationDAO applicationDAO = daoFactory.getMySQLApplicationDAO();
        IUserDAO userDAO = daoFactory.getMySQLUserDAO();

        // getting id of user from users table
        try {
            int userId = userDAO.getIdByLogin(userName);
            Application application = new Application(productName, productComment, userId);
            applicationDAO.addApplication(application);
        } catch (SQLException ex) {
            // TODO Logger
        }
    }

    /** Submits a new user. */
    public static void registerUser(String login, String password, String firstName, String middleName,
                                    String lastName, String email, String phoneNumber) {
        IUserTypeDAO userTypeDAO = DAOFactory.getMySQLUserTypeDAO();
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();
        int userTypeId = 0;

        try {

            // getting id of "user" user type from user_types table
            userTypeId = userTypeDAO.getIdByRole("user");

            User user = new User(login, password, firstName, middleName, lastName, email, phoneNumber, userTypeId);
            userDAO.addUser(user);
        } catch (SQLException e) {
            // TODO logger
        }
    }
}
