package ua.com.repairagency.services;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IUserDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO
public class SubmitRegistrationService {

    public static void registerUser(String login, String password, String firstName, String middleName,
                                    String lastName, String email, String phoneNumber) {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        IUserDAO userDAO = DAOFactory.getMySQLUserDAO();
        int userTypeId = 0;

        // getting id of user UserType from user_types table
        try {
            conn = pool.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = conn.prepareStatement("SELECT utype_id FROM user_types WHERE role=?");
                preparedStatement.setString(1, "user");
                ResultSet results = null;

                try {
                    results = preparedStatement.executeQuery();

                    if (results.next()) {
                        userTypeId = results.getInt("utype_id");
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

        User user = new User(login, password, firstName, middleName, lastName, email, phoneNumber, userTypeId);

        // User insertion
        try {
            userDAO.addUser(user);
        } catch (SQLException e) {
            // TODO logger
        }
    }
}
