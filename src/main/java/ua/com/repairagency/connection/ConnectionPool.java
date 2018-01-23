package ua.com.repairagency.connection;

import org.apache.log4j.Logger;
import ua.com.repairagency.properties.ConfigurationManager;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/** Singleton class for connection pooling. */
public class ConnectionPool {

    private static final Logger log = Logger.getLogger(ConnectionPool.class);
    private static ConnectionPool instance = null;

    /** The DataSource object used for connection pooling. */
    private DataSource pool;

    private final String DATASOURCE;
    private final String TOMCAT_JNDI_NAME;

    /** The constructor. */
    private ConnectionPool() {
        ConfigurationManager config = ConfigurationManager.getInstance();

        DATASOURCE = config.getProperty(ConfigurationManager.DATASOURCE);
        TOMCAT_JNDI_NAME = config.getProperty(ConfigurationManager.TOMCAT_JNDI_NAME);

        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(TOMCAT_JNDI_NAME);
            pool = (DataSource) envContext.lookup(DATASOURCE);
        } catch(NamingException ex){
            log.error("Problem getting DataSource object:", ex);
        }
    }

    /** Thread-safe. */
    public static synchronized ConnectionPool getInstance() {
        log.info("Trying to get instance of ConnectionPool.");
        if (instance == null) {
            log.info("Trying to create ConnectionPool instance.");
            instance = new ConnectionPool();
        }
        if (instance == null) {
            log.warn("ConnectionPool instance is still null!");
        }
        log.info("The getInstance method finished successfully.");
        return instance;
    }

    /** Gets a connection. */
    public synchronized Connection getConnection() throws SQLException {
        log.info("Trying to get a connection from pool.");
        return pool.getConnection();
    }

    /** Closes a connection.
     *
     * @param connection the connection to be closed
     */
    public void closeConnection(Connection connection) throws SQLException {
        log.info("Trying to close connection.");
        if(connection != null){
            connection.close();
        } else {
            log.warn("Connection is null!");
        }
        log.info("The closeConnection method finished successfully.");
    }
}
