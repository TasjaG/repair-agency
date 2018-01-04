package ua.com.repairagency.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// redundant
/**
 *  A Singleton class for connecting to the db.
 */
public class Database {

    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String CONN_STRING = "jdbc:mysql://localhost:3306/repair_agency?useSSL=false";

    private static Database instance;
    private Connection conn;

    private Database() {

    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
            instance.connect();
        }
        return instance;
    }

    public Connection getConnection() {
        return conn;
    }

    public void connect() {
        if (conn != null)
            return;

        try {
            conn = DriverManager.getConnection(CONN_STRING, USERNAME, PASSWORD);

            System.out.println("Connected!");
        } catch (SQLException e) {
            //( new Logger.getLogger(new ConnectionTest) ).logException()
            //e.printStackTrace();
            System.err.println(e);
        }
    }
}
