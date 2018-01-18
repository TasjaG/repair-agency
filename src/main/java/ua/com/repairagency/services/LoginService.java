/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.repairagency.services;

import java.sql.SQLException;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.IUserDAO;

/** Class containing method for user authentication. */
public class LoginService {
    
    /** Checks the validity of username and password. */
    public static boolean authenticateUser(String login, String password) {
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
}
