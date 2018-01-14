package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.interfaces.IUserTypeDAO;
import ua.com.repairagency.dao.entities.UserType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL-oriented dao class for user_types table
 * CRUD - Create, retrieve, update, delete
 */
public class MySQLUserTypeDAO implements IUserTypeDAO {

    /**
     * Adds userType to user_types table.
     *
     * @param userType the user type entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public void addUserType(UserType userType) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "INSERT INTO user_types (role, description) values (?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);

        insertStatement.setString(1, userType.getRole());
        insertStatement.setString(2, userType.getDescription());

        insertStatement.executeUpdate();
        insertStatement.close();
    }

    /**
     * Retrieves a UserType object with data from user_types table.
     *
     * @param id the primary key of the user type
     * @return the user type entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the prepared statement
     */
    @Override
    public UserType getUserType(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

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

        return userType;
    }

    /**
     * Retrieves an ArrayList of UserType objects with data from user_types table.
     * The list has a limited amount of elements to support pagination.
     *
     * @param start list's first element
     * @param total page's max amount of table rows
     * @return the list of the user type entities
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the statement
     */
    @Override
    public List<UserType> getUserTypes(int start, int total) throws SQLException {
        List<UserType> userTypes = new ArrayList<UserType>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

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

        return userTypes;
    }

    /**
     * Updates record in user_types table.
     *
     * @param userType the user type entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public void updateUserType(UserType userType) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "UPDATE user_types SET role=?, description=? WHERE utype_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);

        updateStatement.setString(1, userType.getRole());
        updateStatement.setString(2, userType.getDescription());
        updateStatement.setInt(3, userType.getId());

        updateStatement.executeUpdate();
        updateStatement.close();
    }

    /**
     * Deletes a record in user_types table and the corresponding record(s) in users_and_types table.
     *
     * @param id the primary key of the user type
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public void deleteUserType(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        // delete record from user_types table
        String sql = "DELETE FROM user_types WHERE utype_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();

        // delete record(s) from users_and_types table
        sql = "DELETE FROM users_and_types WHERE utype_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();
    }

    /** Returns the number of records in table. */
    @Override
    public int numberOfRecords() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM user_types");

        int numOfRecords = 0;

        if (results.next()) {
            numOfRecords = results.getInt("count");
        }
        results.close();
        selectStatement.close();

        return numOfRecords;
    }
}
