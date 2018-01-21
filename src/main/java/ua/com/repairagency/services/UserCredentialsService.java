package ua.com.repairagency.services;

import java.sql.SQLException;

import org.apache.log4j.Logger;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IUserDAO;

/** Class containing boolean methods for checking user credentials. */
public class UserCredentialsService {

    private static final Logger log = Logger.getLogger(UserCredentialsService.class);

    /** Checks the validity of username and password. */
    public static boolean userExists(String login, String password) {
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();
        User user = null;

        try {

            // retrieves user with specified credentials from db if such a user exists
            user = userDAO.getUserByLoginAndPassword(login, password);
        } catch (SQLException ex) {
            log.error("Problem checking user's credentials:", ex);
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
            log.error("Problem checking if username already exists:", ex);
        }

        if(id > 0) {
            return false;
        }
        return true;
    }
}
