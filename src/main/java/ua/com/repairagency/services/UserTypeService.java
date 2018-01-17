package ua.com.repairagency.services;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.entities.UserType;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IUserDAO;
import ua.com.repairagency.dao.interfaces.IUserTypeDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO works under the assumption that the user has only one role - change?
public class UserTypeService {

    /** Gets the user's role. */
    public static String getUserTypeByUserName(String userName) {
        String userTypeString = null;

        UserType userTypeObj  = null;
        User user = null;
        int userId = 0;
        int userTypeId = 0;

        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();
        IUserTypeDAO userTypeDAO = DAOFactory.getMySQLUserTypeDAO();

        try {
            userId = userDAO.getIdByLogin(userName);
            user = userDAO.getUser(userId);
            userTypeId = user.getUserTypeId();
            userTypeObj = userTypeDAO.getUserType(userTypeId);
            userTypeString = userTypeObj.getRole();
        } catch (SQLException e) {
            // TODO
        } catch (NullPointerException e) {
            // TODO
        }

        return userTypeString;
    }
}
