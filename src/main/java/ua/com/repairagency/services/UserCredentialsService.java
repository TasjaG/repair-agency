package ua.com.repairagency.services;

import java.sql.SQLException;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IUserDAO;

/** Class containing boolean methods for checking user credentials. */
public class UserCredentialsService {
    
    /** Checks the validity of username and password. */
    public static boolean userExists(String login, String password) {
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();
        User user = null;

        try {

            // retrieves user with specified credentials from db if such a user exists
            user = userDAO.getUserByLoginAndPassword(login, password);
        } catch (SQLException ex) {
            // TODO Logger
        }

        if(user != null) {
            return true;
        }
        return false;
    }

    /** Checks whether the given username in available. */
    public static boolean isUsernameAvailable(String userName) {
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();
        int id = 0;

        try {

            // retrieves id of user with specified credentials from db if such a user exists
            id = userDAO.getIdByLogin(userName);
        } catch (SQLException ex) {
            // TODO Logger
        }

        if(id > 0) {
            return false;
        }
        return true;
    }
}
