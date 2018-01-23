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
        assertNotNull(commentDAO.getComment(15));
        assertNotNull(applicationDAO.getApplication(1));
        assertNotNull(acceptedAppDAO.getAcceptedApplication(1));
    }

    @Test
    public void getIdByLoginTest() throws SQLException {
        assertNotNull(userDAO.getIdByLogin("admin"));
    }

    @Test
    public void getUserByLoginAndPasswordTest() throws SQLException {
        assertNotNull(userDAO.getUserByLoginAndPassword("admin", "admin"));
    }

    @Test
    public void getIdByRole() throws SQLException {
        assertNotNull(userTypeDAO.getIdByRole("admin"));
    }

    @Test
    public void getEntityTest() throws SQLException {
        assertNotNull(userDAO.getUser(4));
        assertNotNull(userTypeDAO.getUserType(4));
        assertNotNull(commentDAO.getComment(14));
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
        User updatedUser = userDAO.getUser(2);
        updatedUser.setFirstName("UpdatedFName!");
        userDAO.updateUser(updatedUser);
        UserType updatedUserType = userTypeDAO.getUserType(2);
        updatedUserType.setDescription("Updated description!");
        userTypeDAO.updateUserType(updatedUserType);
        Comment updatedComment = commentDAO.getComment(2);
        updatedComment.setText("Updated comment text!");
        commentDAO.updateComment(updatedComment);
        Application updatedApplication = applicationDAO.getApplication(3);
        applicationDAO.rejectApplication(updatedApplication);
        updatedApplication = applicationDAO.getApplication(4);
        applicationDAO.acceptApplication(updatedApplication.getId());
        AcceptedApplication updatedAcceptedApplication = acceptedAppDAO.getAcceptedApplication(1);
        acceptedAppDAO.completeAcceptedApplication(updatedAcceptedApplication.getId());
        assertTrue(userDAO.getUser(2).getFirstName().equals("UpdatedFName!"));
        assertTrue(userTypeDAO.getUserType(2).getDescription().equals("Updated description!"));
        assertTrue(commentDAO.getComment(2).getText().equals("Updated comment text!"));
        assertTrue(applicationDAO.getApplication(3).getStatus().equals("rejected"));
        assertTrue(applicationDAO.getApplication(4).getStatus().equals("accepted"));
        assertTrue(acceptedAppDAO.getAcceptedApplication(1).getStatus().equals("completed"));
    }

    /** Rows are deleted in a specific order to accommodate FK constraints. */
    @Test
    public void deleteEntityTest() throws SQLException {
        acceptedAppDAO.deleteAcceptedApplication(2);
        applicationDAO.deleteApplication(5);
        commentDAO.deleteComment(6);
        userDAO.deleteUser(3);
        userTypeDAO.deleteUserType(3);
        assertNull(acceptedAppDAO.getAcceptedApplication(2));
        assertNull(applicationDAO.getApplication(5));
        assertNull(commentDAO.getComment(6));
        assertNull(userTypeDAO.getUserType(3));
        assertNull(userDAO.getUser(3));
    }
}