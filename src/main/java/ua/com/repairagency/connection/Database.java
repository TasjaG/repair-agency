package ua.com.repairagency.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ua.com.repairagency.services.ConfigurationManagerService;

// redundant
/**
 *  A Singleton class for connecting to the db.
 */
public class Database {

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;
    
    private static Database instance;
    private Connection conn;

    private Database() {
        ConfigurationManagerService config = ConfigurationManagerService.getInstance();

        URL = config.getProperty(ConfigurationManagerService.URL);
        USERNAME = config.getProperty(ConfigurationManagerService.USERNAME);
        PASSWORD = config.getProperty(ConfigurationManagerService.PASSWORD);
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public Connection getConnection() throws SQLException{
        if (conn == null)
            connect();
        return conn;
    }

    private void connect() throws SQLException{
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //conn = DriverManager.getConnection("jdbc:mysql://localhost/repair_agency?user=root&password=root");
    }
}
