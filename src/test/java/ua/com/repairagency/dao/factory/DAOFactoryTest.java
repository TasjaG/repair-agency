package ua.com.repairagency.dao.factory;

import org.junit.Test;
import ua.com.repairagency.dao.interfaces.*;
import ua.com.repairagency.dao.sql.*;

import static org.junit.Assert.*;

/** Test to make sure the DAO Factory always returns a DAO object. */
public class DAOFactoryTest {

    @Test
    public void getMySQLUserDAOTest() {
        IUserDAO userDAO = new TestDBUserDAO();
        assertNotNull(userDAO);
    }

    @Test
    public void getMySQLUserTypeDAOTest() {
        IUserTypeDAO userTypeDAO = new TestDBUserTypeDAO();
        assertNotNull(userTypeDAO);
    }

    @Test
    public void getMySQLCommentDAOTest() {
        ICommentDAO commentDAO = new TestDBCommentDAO();
        assertNotNull(commentDAO);
    }

    @Test
    public void getMySQLApplicationDAOTest() {
        IApplicationDAO applicationDAO = new TestDBApplicationDAO();
        assertNotNull(applicationDAO);
    }

    @Test
    public void getMySQLAcceptedApplicationDAOTest() {
        IAcceptedApplicationDAO acceptedAppDAO = new TestDBAcceptedApplicationDAO();
        assertNotNull(acceptedAppDAO);
    }
}