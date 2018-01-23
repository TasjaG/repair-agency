package ua.com.repairagency.connection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.com.repairagency.properties.ConfigurationManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.*;

/**
 *  For testing the connection.
 *  Connects to the actual db (repair_agency).
 */
public class DatabaseTest {
    private String URL = "jdbc:mysql://localhost:3306/repair_agency";
    private String USERNAME = "root";
    private String PASSWORD = "root";
    private String DRIVER = "com.mysql.jdbc.Driver";
    private Connection conn;

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void connectTest() throws SQLException, ClassNotFoundException {
        Class.forName(DRIVER);
        conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        assertNotNull(conn);
    }
}