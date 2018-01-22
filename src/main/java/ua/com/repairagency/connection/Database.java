package ua.com.repairagency.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ua.com.repairagency.properties.ConfigurationManager;

// TODO move to tests folder
/**
 *  A Singleton class for connecting to the db.
 */
public class Database {

    private final String URL;
    private final String USERNAME;
    private final String PASSWORD;
    private final String DRIVER;

    private static Database instance;
    private Connection conn;

    private Database() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        URL = config.getProperty(ConfigurationManager.URL);
        USERNAME = config.getProperty(ConfigurationManager.USERNAME);
        PASSWORD = config.getProperty(ConfigurationManager.PASSWORD);
        DRIVER = config.getProperty(ConfigurationManager.DRIVER);
    }

    public static Database getInstance() {
        if (instance == null)
            instance = new Database();
        return instance;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        if (conn == null)
            connect();
        return conn;
    }

    private void connect() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        //conn = DriverManager.getConnection("jdbc:mysql://localhost/repair_agency?user=root&password=root");
    }
}
