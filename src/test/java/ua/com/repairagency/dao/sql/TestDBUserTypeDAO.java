package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.Database;
import ua.com.repairagency.dao.entities.UserType;
import ua.com.repairagency.dao.interfaces.IUserTypeDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A version of UserType dao that uses a connection to a test db.
 * Intended for testing purposes.
 */
public class TestDBUserTypeDAO implements IUserTypeDAO {

    @Override
    public void addUserType(UserType userType) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "INSERT INTO user_types (role, description) values (?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);
        insertStatement.setString(1, userType.getRole());
        insertStatement.setString(2, userType.getDescription());
        insertStatement.executeUpdate();
        insertStatement.close();
        conn.close();
    }

    @Override
    public int getIdByRole(String role) throws SQLException {
        int userTypeId = 0;
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return 0; // test will fail
        }
        String sql = "SELECT utype_id FROM user_types WHERE role=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, role);
        ResultSet results = preparedStatement.executeQuery();
        if (results.next()) {
            userTypeId = results.getInt("utype_id");
        }
        results.close();
        preparedStatement.close();
        conn.close();
        return userTypeId;
    }

    @Override
    public UserType getUserType(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        String sql = "SELECT utype_id, role, description FROM user_types WHERE utype_id=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);
        ResultSet results = selectStatement.executeQuery();
        UserType userType = null;
        if (results.next()) {
            String role = results.getString("role");
            String description = results.getString("description");

            userType = new UserType(id, role, description);
        }
        results.close();
        selectStatement.close();
        conn.close();
        return userType;
    }

    @Override
    public List<UserType> getUserTypes(int start, int total) throws SQLException {
        List<UserType> userTypes = new ArrayList<UserType>();
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        Statement selectEverythingStatement = conn.createStatement();
        ResultSet results = selectEverythingStatement.executeQuery("SELECT * FROM user_types "
                                                                        + (start - 1) + "," + total);
        UserType userType = null;
        while (results.next()) {
            int id = results.getInt("user_id");
            String role = results.getString("role");
            String description = results.getString("description");

            userType = new UserType(id, role, description);
            userTypes.add(userType);
        }
        results.close();
        selectEverythingStatement.close();
        conn.close();
        return userTypes;
    }

    @Override
    public void updateUserType(UserType userType) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "UPDATE user_types SET role=?, description=? WHERE utype_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setString(1, userType.getRole());
        updateStatement.setString(2, userType.getDescription());
        updateStatement.setInt(3, userType.getId());
        updateStatement.executeUpdate();
        updateStatement.close();
        conn.close();
    }

    @Override
    public void deleteUserType(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "DELETE FROM user_types WHERE utype_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        sql = "DELETE FROM users_and_types WHERE utype_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        conn.close();
    }

    @Override
    public int getNumberOfRecords() throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return 0; // test will fail
        }
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM user_types");
        int numOfRecords = 0;
        if (results.next()) {
            numOfRecords = results.getInt("count");
        }
        results.close();
        selectStatement.close();
        conn.close();
        return numOfRecords;
    }
}