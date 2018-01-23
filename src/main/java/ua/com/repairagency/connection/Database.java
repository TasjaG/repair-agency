package ua.com.repairagency.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import ua.com.repairagency.properties.ConfigurationManager;

/**
 *  A Singleton class for connecting to the db.
 *  Used for testing, connects to the test db (repair_agency_test).
 */
public class Database {

    private String URL = "jdbc:mysql://localhost:3306/repair_agency_test";
    private String USERNAME = "root";
    private String PASSWORD = "root";
    private String DRIVER = "com.mysql.jdbc.Driver";

    private static Database instance;
    private Connection conn;

    private Database() {

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

    private void connect() throws SQLException {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException ex) {
            // ex.printStackTrace();
        }
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}
