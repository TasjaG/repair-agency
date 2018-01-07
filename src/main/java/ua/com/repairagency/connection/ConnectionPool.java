package ua.com.repairagency.connection;

import ua.com.repairagency.services.ConfigurationManagerService;

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
    
    private ConnectionPool() {
        ConfigurationManagerService config = ConfigurationManagerService.getInstance();

        DATASOURCE = config.getProperty(ConfigurationManagerService.DATASOURCE);
        TOMCAT_JNDI_NAME = config.getProperty(ConfigurationManagerService.TOMCAT_JNDI_NAME);
        
        try{
            Context initContext = new InitialContext();
            Context envContext = (Context) initContext.lookup(TOMCAT_JNDI_NAME);
            pool = (DataSource) envContext.lookup(DATASOURCE);
        } catch(NamingException ex){

            // TODO Logger
            //ex.printStackTrace();
        }
    }

    /** Thread-safe. */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null) {
            instance = new ConnectionPool();
        }
        return instance;
    }

    // TODO this throws an exception
    //public synchronized Connection getConnection() throws SQLException {
    public synchronized Connection getConnection() {
        
        try {
            Connection conn = pool.getConnection();
            return pool.getConnection();
        } catch (SQLException ex) {
            return null;
        }
    }

    // TODO explicitly close the connection when finished working with db
    public void closeConnection(Connection connection){
        try{
            if(connection != null){
                connection.close();
            }
        }catch(SQLException e){

            // TODO Logger
            //e.printStackTrace();
        }
    }
}
