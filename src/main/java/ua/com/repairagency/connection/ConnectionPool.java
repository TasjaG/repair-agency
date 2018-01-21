package ua.com.repairagency.connection;

import org.apache.log4j.Logger;
import ua.com.repairagency.services.ConfigurationManagerService;

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
        ConfigurationManagerService config = ConfigurationManagerService.getInstance();

        DATASOURCE = config.getProperty(ConfigurationManagerService.DATASOURCE);
        TOMCAT_JNDI_NAME = config.getProperty(ConfigurationManagerService.TOMCAT_JNDI_NAME);

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
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    public synchronized Connection getConnection() throws SQLException {
            return pool.getConnection();
    }

    public void closeConnection(Connection connection) throws SQLException {
        if(connection != null){
            connection.close();
        }
    }
}
