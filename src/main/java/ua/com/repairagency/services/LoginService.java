/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.com.repairagency.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.connection.Database;

/** Class containing method for user authentication. */
public class LoginService {
    
    /** Checks the validity of username and password. */
    public static boolean authenticateUser(String login, String password) {
        Database db = Database.getInstance();
        //ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = null;
        
        try {
            //conn = pool.getConnection();
            conn = db.getConnection();
            
            PreparedStatement preparedStatement = null;

            try {
                preparedStatement = conn.prepareStatement("SELECT * FROM users WHERE user_login=? AND user_password=?");
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                ResultSet results = null;

                try {
                    results = preparedStatement.executeQuery();   
                    return results.next();
                    // TODO
                    //if (results.next()) {
                    //    int userId = results.getInt("user_id");
                    //    return true;
                    //} else {
                    //    return false;
                    //}
                } finally {
                    if (results != null)
                        results.close();
                }
            } finally {
                if (preparedStatement != null)
                    preparedStatement.close();
            }
        } catch (SQLException ex) {

            // TODO Logger
            return false;
        }
    }
}
