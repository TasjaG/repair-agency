package ua.com.repairagency.services;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.dao.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.SQLException;

public class SubmitApplicationService {

    public static void submitApplication(String productName, String productComment, String userName) {
        DAOFactory daoFactory = new DAOFactory();
        IApplicationDAO applicationDAO = daoFactory.getMySQLApplicationDAO();
        IUserDAO userDAO = daoFactory.getMySQLUserDAO();
        int userId = 0;

        // getting id of user from users table
        try {
            userId = userDAO.getIdByLogin(userName);
        } catch (SQLException e) {

            // TODO logger
        }

        Application application = new Application(productName, productComment, userId);

        try {
            applicationDAO.addApplication(application);
        } catch (SQLException ex) {

            // TODO Logger
        }
    }
}
