package ua.com.repairagency.dao.sql;

import org.junit.*;
import ua.com.repairagency.dao.entities.*;
import ua.com.repairagency.dao.factory.DAOFactory;
import ua.com.repairagency.dao.interfaces.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.*;

/** Test for various dao methods using test db repair_agency_test. */
public class MySQLDAOTest {
    private static IUserDAO userDAO;
    private static IUserTypeDAO userTypeDAO;
    private static ICommentDAO commentDAO;
    private static IApplicationDAO applicationDAO;
    private static IAcceptedApplicationDAO acceptedAppDAO;
    private static User user;
    private static UserType userType;
    private static Comment comment;
    private static Application application1;
    private static Application application2;
    private static AcceptedApplication acceptedApp;

    @BeforeClass
    public static void setUpClassTest() {
        userDAO = new TestDBUserDAO();
        userTypeDAO = new TestDBUserTypeDAO();
        commentDAO = new TestDBCommentDAO();
        applicationDAO = new TestDBApplicationDAO();
        acceptedAppDAO = new TestDBAcceptedApplicationDAO();
        user = new User("TestUser","TestPassword","TestFName","TestMName",
                "TestLName","TestEmail","TestPhoneNum",4);
        userType = new UserType("TestUserType","A test.");
        comment = new Comment("Comment by TestUser",5);
        application1 = new Application("TestProduct","A test.",5);
        application2 = new Application("TestProduct","A test.",5);
        acceptedApp = new AcceptedApplication("TestProduct","A test",100d,
                1,5);
    }

    @Test
    public void addEntityTest() throws SQLException {
        userDAO.addUser(user);
        userTypeDAO.addUserType(userType);
        commentDAO.addComment(comment);
        applicationDAO.addApplication(application1);
        acceptedAppDAO.addAcceptedApplication(acceptedApp);
        assertNotNull(userDAO.getUser(5));
        assertNotNull(userTypeDAO.getUserType(5));
        assertNotNull(commentDAO.getComment(16));
        assertNotNull(applicationDAO.getApplication(1));
        assertNotNull(acceptedAppDAO.getAcceptedApplication(1));
    }

    @Test
    public void getIdByLoginTest() throws SQLException {
        assertNotNull(userDAO.getIdByLogin("TestUser"));
    }

    @Test
    public void getUserByLoginAndPasswordTest() throws SQLException {
        assertNotNull(userDAO.getUserByLoginAndPassword("UserType", "TestPassword"));
    }

    @Test
    public void getIdByRole() throws SQLException {
        assertNotNull(userTypeDAO.getIdByRole("TestUserType"));
    }

    @Test
    public void getEntityTest() throws SQLException {
        assertNotNull(userDAO.getUser(5));
        assertNotNull(userTypeDAO.getUserType(5));
        assertNotNull(commentDAO.getComment(16));
        assertNotNull(applicationDAO.getApplication(1));
        assertNotNull(acceptedAppDAO.getAcceptedApplication(1));
    }

    @Test
    public void getEntitiesTest() throws SQLException {
        assertNotNull(userDAO.getUsers(1, userDAO.getNumberOfRecords()));
        assertNotNull(userTypeDAO.getUserTypes(1, userDAO.getNumberOfRecords()));
        assertNotNull(commentDAO.getComments(1, userDAO.getNumberOfRecords()));
        assertNotNull(applicationDAO.getApplications(1, userDAO.getNumberOfRecords()));
        assertNotNull(acceptedAppDAO.getAcceptedApplications(1, userDAO.getNumberOfRecords()));
    }

    @Test
    public void updateEntityTest() throws SQLException {
        user.setFirstName("UpdatedFName!");
        userDAO.updateUser(user);
        userType.setDescription("Updated description!");
        userTypeDAO.updateUserType(userType);
        comment.setText("Updated comment text!");
        commentDAO.updateComment(comment);
        applicationDAO.rejectApplication(application1);
        applicationDAO.acceptApplication(application2.getId());
        acceptedAppDAO.completeAcceptedApplication(acceptedApp.getId());
        assertTrue(userDAO.getUser(5).getFirstName().equals("UpdatedFName!"));
        assertTrue(userTypeDAO.getUserType(5).getDescription().equals("Updated description!"));
        assertTrue(commentDAO.getComment(16).getText().equals("Updated comment text!"));
        assertTrue(applicationDAO.getApplication(1).getStatus().equals("rejected"));
        assertTrue(applicationDAO.getApplication(2).getStatus().equals("accepted"));
        assertTrue(acceptedAppDAO.getAcceptedApplication(1).getStatus().equals("completed"));
    }

    @Test
    public void getNumberOfRecordsTest() throws SQLException {
        assertEquals(5, userDAO.getNumberOfRecords());
        assertEquals(5, userTypeDAO.getNumberOfRecords());
        assertEquals(16, commentDAO.getNumberOfRecords());
        assertEquals(2, applicationDAO.getNumberOfRecords());
        assertEquals(1, acceptedAppDAO.getNumberOfRecords());
    }

    /** Rows are deleted in a specific order to accommodate FK constraints. */
    @Test
    public void deleteEntityTest() throws SQLException {
        acceptedAppDAO.deleteAcceptedApplication(1);
        applicationDAO.deleteApplication(1);
        applicationDAO.deleteApplication(2);
        commentDAO.deleteComment(16);
        userTypeDAO.deleteUserType(5);
        userDAO.deleteUser(5);
        assertNull(acceptedAppDAO.getAcceptedApplication(1));
        assertNull(applicationDAO.getApplication(1));
        assertNull(commentDAO.getComment(16));
        assertNull(userTypeDAO.getUserType(5));
        assertNull(userDAO.getUser(5));
    }
}