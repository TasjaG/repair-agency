package ua.com.repairagency.dao.factory;

import ua.com.repairagency.dao.sql.*;

/** Class containing methods for returning dao objects for different entities. */
public class DAOFactory {

    /** @return the MySQL-oriented implementation of the IUserDAO interface. */
    public MySQLUserDAO getMySQLUserDAO() {
        return new MySQLUserDAO();
    }

    /** @return the MySQL-oriented implementation of the IUserTypeDAO interface. */
    public MySQLUserTypeDAO getMySQLUserTypeDAO() {
        return new MySQLUserTypeDAO();
    }

    /** @return the MySQL-oriented implementation of the ICommentDAO interface. */
    public MySQLCommentDAO getMySQLCommentDAO() {
        return new MySQLCommentDAO();
    }

    /** @return the MySQL-oriented implementation of the IApplicationDAO interface. */
    public MySQLApplicationDAO getMySQLApplicationDAO() {
        return new MySQLApplicationDAO();
    }

    /** @return the MySQL-oriented implementation of the IAcceptedApplicationDAO interface. */
    public MySQLAcceptedApplicationDAO getMySQLAcceptedApplicationDAO() {
        return new MySQLAcceptedApplicationDAO();
    }
}