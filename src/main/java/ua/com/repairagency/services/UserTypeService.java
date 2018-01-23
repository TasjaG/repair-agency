package ua.com.repairagency.services;

import org.apache.log4j.Logger;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.entities.UserType;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IUserDAO;
import ua.com.repairagency.dao.interfaces.IUserTypeDAO;

import java.sql.SQLException;

/** Service class for retrieving the user's role. */
public class UserTypeService {

    private static final Logger log = Logger.getLogger(UserTypeService.class);

    /** Gets the user's role. */
    public static String getUserTypeByUserName(String userName) {
        log.info("Trying to get user type by the specified username.");

        // works under the assumption that the user has only one role

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
        } catch (SQLException ex) {
            log.error("Problem retrieving user's role:", ex);
        } catch (NullPointerException ex) {
            log.error("Problem retrieving user's role:", ex);
        }
        log.info("The getUserTypeByUserName method finished successfully.");
        return userTypeString;
    }
}
