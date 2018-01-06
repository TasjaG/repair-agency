package ua.com.repairagency.connection;

//import com.sun.istack.internal.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// redundant
public class ConnectionTest {

    public static final String USERNAME = "root";
    public static final String PASSWORD = "root";
    public static final String CONN_STRING = "jdbc:mysql://localhost:3306/repair_agency?useSSL=false";
/*
    public static void main(String[] args) {
        Connection conn = null;

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);
            System.out.println("Connected!");
        } catch (SQLException e) {
            //( new Logger.getLogger(new ConnectionTest) ).logException()
            //e.printStackTrace();
            System.err.println(e);
        }
    
    String driver = ConfigurationManagerService.getInstance()
                    .getProperty(ConfigurationManagerService.DRIVER);
            Class.forName(driver);
            Connection conn = null;

            try {
                String url = ConfigurationManagerService.getInstance()
                        .getProperty(ConfigurationManagerService.URL);
                conn = DriverManager.getConnection(url);
    }
*/
    /*
    public static void main(String[] args) {
        MySQLUserDAO userDAO = new MySQLUserDAO();

        try {
            // usertype 2 should mean user is user_types table
            userDAO.addUser(new User("Tom", "somePassword", "Thomas",
                        null, "Raith",
                                "raith@gmail.com", "0693879890", 4));
        } catch (SQLException e) {
            // TODO log exception
            //e.printStackTrace();
        }
    }
*/
}
