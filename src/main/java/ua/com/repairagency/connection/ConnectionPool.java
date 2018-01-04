package ua.com.repairagency.connection;

import ua.com.repairagency.properties.ConfigurationManager;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/** Singleton class for connection pooling. */
public class ConnectionPool {

    private static ConnectionPool instance = null;

    /** The DataSource object used for connection pooling. */
    private DataSource pool;

    private final String DATASOURCE;
    private final String TOMCAT_JNDI_NAME;
    private final String USERNAME;
    private final String PASSWORD;

    private ConnectionPool() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        DATASOURCE = config.getProperty(ConfigurationManager.DATASOURCE);
        TOMCAT_JNDI_NAME = config.getProperty(ConfigurationManager.TOMCAT_JNDI_NAME);
        USERNAME = config.getProperty(ConfigurationManager.USERNAME);
        PASSWORD = config.getProperty(ConfigurationManager.PASSWORD);

        try{
            Context context = new InitialContext();
            Context envContext = (Context) context.lookup(TOMCAT_JNDI_NAME);
            pool = (DataSource) envContext.lookup(DATASOURCE);
        }catch(NamingException e){

            // TODO Logger
            e.printStackTrace();
        }
    }

    /** Thread-safe. */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
        return pool.getConnection(USERNAME, PASSWORD);
    }

    // TODO explicitly close the connection when finished working with db
    public void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){

            // TODO Logger
            e.printStackTrace();
        }
    }
}
