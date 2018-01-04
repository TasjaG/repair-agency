package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.interfaces.IUserDAO;
import ua.com.repairagency.dao.entities.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL-oriented dao class for users table
 * CRUD - Create, retrieve, update, delete
 */
public class MySQLUserDAO implements IUserDAO {

    /**
     * Adds user to users table and stores user's id and user type in users_and_types table.
     *
     * @param user the user entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the prepared statement
     */
    public void addUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        // insert data into users table
        String sql = "INSERT INTO users (user_login, user_password, user_f_name, user_m_name, user_l_name, "
                        + "user_email, user_phone) values (?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);

        insertStatement.setString(1, user.getLogin());
        insertStatement.setString(2, user.getPassword());
        insertStatement.setString(3, user.getFirstName());
        insertStatement.setString(4, user.getMiddleName());
        insertStatement.setString(5, user.getLastName());
        insertStatement.setString(6, user.getEmail());
        insertStatement.setString(7, user.getPhoneNumber());

        insertStatement.executeUpdate();

        // get user_id for this user to insert into users_and_types table, -1 if nothing found
        int userId = -1;

        // getGeneratedKeys() returns result set of keys that were auto generated (the user_id column)
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();

        // if result has data, get primary key of value of last inserted record
        if (null != generatedKeys && generatedKeys.next()) {
            userId = generatedKeys.getInt(1);

            // only close if not null
            generatedKeys.close();
        }
        insertStatement.close();

        // insert data into users_and_types table
        sql = "INSERT INTO users_and_types (utype_id, user_id) values (?,?)";
        insertStatement = conn.prepareStatement(sql);

        insertStatement.setInt(1, user.getUserTypeId());
        insertStatement.setInt(2, userId);

        insertStatement.executeUpdate();
        insertStatement.close();
    }

    /**
     * Retrieves a User object with data from users table and users_and_types table.
     *
     * @param id the primary key of the user
     * @return the user entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the prepared statement
     */
    public User getUser(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "SELECT user_id, user_login, user_password, user_f_name, user_m_name, user_l_name, user_email, "
                        + "user_phone FROM users WHERE user_id=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);

        ResultSet results = selectStatement.executeQuery();

        User user = null;

        if (results.next()) {
            String login = results.getString("user_login");
            String password = results.getString("user_password");
            String firstName = results.getString("user_f_name");
            String middleName = results.getString("user_m_name");
            String lastName = results.getString("user_l_name");
            String email = results.getString("user_email");
            String phoneNumber = results.getString("user_phone");

            results.close();
            selectStatement.close();

            // get user utype id from users_and_types table
            sql = "SELECT utype_id FROM users_and_types WHERE user_id=?";
            selectStatement = conn.prepareStatement(sql);
            selectStatement.setInt(1, id);

            results = selectStatement.executeQuery();

            // -1 if nothing found
            int userTypeId = -1;

            if (results.next()) {
                userTypeId = results.getInt("utype_id");
            }
            user = new User(id, login, password, firstName, middleName, lastName, email, phoneNumber, userTypeId);
        }
        results.close();
        selectStatement.close();

        return user;
    }

    /**
     * Retrieves an ArrayList of User objects with data from users table and users_and_types table.
     *
     * @return the list of the user entities
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a statement,
     *                      if could not get a prepared statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the statement,
     *                      if could not close the prepared statement
     */
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<User>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        Statement selectEverythingStatement = conn.createStatement();
        ResultSet results = selectEverythingStatement.executeQuery("SELECT * FROM users");

        User user = null;
        ResultSet tempResults = null;

        while (results.next()) {
            int id = results.getInt("user_id");
            String login = results.getString("user_login");
            String password = results.getString("user_password");
            String firstName = results.getString("user_f_name");
            String middleName = results.getString("user_m_name");
            String lastName = results.getString("user_l_name");
            String email = results.getString("user_email");
            String phoneNumber = results.getString("user_phone");

            // get user utype id from users_and_types table
            String sql = "SELECT utype_id FROM users_and_types WHERE user_id=?";
            PreparedStatement selectStatement = conn.prepareStatement(sql);
            selectStatement.setInt(1, id);

            tempResults = selectStatement.executeQuery();

            // -1 if nothing found
            int userTypeId = -1;

            if (tempResults.next()) {
                userTypeId = tempResults.getInt("utype_id");
                tempResults.close();
                selectStatement.close();
            }
            user = new User(id, login, password, firstName, middleName, lastName, email, phoneNumber, userTypeId);
            users.add(user);
        }
        results.close();
        selectEverythingStatement.close();

        return users;
    }

    /**
     * Updates record in users table.
     *
     * @param user the user entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    public void updateUser(User user) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "UPDATE users SET user_login=?, user_password=?, user_f_name=?, user_m_name=?, user_l_name=?, "
                + "user_email=?, user_phone=? WHERE user_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);

        updateStatement.setString(1, user.getLogin());
        updateStatement.setString(2, user.getPassword());
        updateStatement.setString(3, user.getFirstName());
        updateStatement.setString(4, user.getMiddleName());
        updateStatement.setString(5, user.getLastName());
        updateStatement.setString(6, user.getEmail());
        updateStatement.setString(7, user.getPhoneNumber());
        updateStatement.setInt(8, user.getId());

        updateStatement.executeUpdate();
        updateStatement.close();
    }

    /**
     * Deletes a record in users table and the corresponding record(s) in users_and_types table.
     *
     * @param id the primary key of the user
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    public void deleteUser(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        // delete record from users table
        String sql = "DELETE FROM users WHERE user_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();

        // delete record(s) from users_and_types table
        sql = "DELETE FROM users_and_types WHERE user_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();
    }
}
