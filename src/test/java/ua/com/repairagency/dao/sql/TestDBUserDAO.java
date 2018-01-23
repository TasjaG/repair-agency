package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.connection.Database;
import ua.com.repairagency.dao.entities.User;
import ua.com.repairagency.dao.interfaces.IUserDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A version of User dao that uses a connection to a test db.
 * Intended for testing purposes.
 */
public class TestDBUserDAO implements IUserDAO {

    @Override
    public void addUser(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "INSERT INTO users (user_login, user_password, user_f_name, user_m_name, user_l_name, "
                        + "user_email, user_phone) values (?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        insertStatement.setString(1, user.getLogin());
        insertStatement.setString(2, user.getPassword());
        insertStatement.setString(3, user.getFirstName());
        if (user.getMiddleName().equals("")) {
            insertStatement.setString(4, null);
        } else {
            insertStatement.setString(4, user.getMiddleName());
        }
        insertStatement.setString(5, user.getLastName());
        insertStatement.setString(6, user.getEmail());
        if (user.getPhoneNumber().equals("")) {
            insertStatement.setString(7, null);
        } else {
            insertStatement.setString(7, user.getPhoneNumber());
        }
        insertStatement.executeUpdate();
        int userId = 0;
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        if ( (generatedKeys != null) && generatedKeys.next()) {
            userId = generatedKeys.getInt(1);
            generatedKeys.close();
        }
        insertStatement.close();
        sql = "INSERT INTO users_and_types (utype_id, user_id) values (?,?)";
        insertStatement = conn.prepareStatement(sql);
        insertStatement.setInt(1, user.getUserTypeId());
        insertStatement.setInt(2, userId);
        insertStatement.executeUpdate();
        insertStatement.close();
        conn.close();
    }

    @Override
    public int getIdByLogin(String userName) throws SQLException {
        int userId = 0;
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return 0; // test will fail
        }
        String sql = "SELECT user_id FROM users WHERE user_login=?";
        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, userName);
        ResultSet results = preparedStatement.executeQuery();
        if (results.next()) {
            userId = results.getInt("user_id");
        }
        results.close();
        preparedStatement.close();
        conn.close();
        return userId;
    }

    @Override
    public User getUserByLoginAndPassword(String login, String password) throws SQLException {
        User user = null;
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        String sql = "SELECT * FROM users WHERE user_login=? AND user_password=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setString(1, login);
        selectStatement.setString(2, password);
        ResultSet results = selectStatement.executeQuery();
        if(results.next()) {
            int id = results.getInt("user_id");
            String firstName = results.getString("user_f_name");
            String middleName = results.getString("user_m_name");
            String lastName = results.getString("user_l_name");
            String email = results.getString("user_email");
            String phoneNumber = results.getString("user_phone");
            results.close();
            selectStatement.close();
            sql = "SELECT utype_id FROM users_and_types WHERE user_id=?";
            selectStatement = conn.prepareStatement(sql);
            selectStatement.setInt(1, id);
            results = selectStatement.executeQuery();
            int userTypeId = 0;
            if (results.next()) {
                userTypeId = results.getInt("utype_id");
            }
            user = new User(id, login, password, firstName, middleName, lastName, email, phoneNumber, userTypeId);
        }
        results.close();
        selectStatement.close();
        conn.close();
        return user;
    }

    @Override
    public User getUser(int id) throws SQLException {
        User user = null;
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        String sql = "SELECT * FROM users WHERE user_id=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);
        ResultSet results = selectStatement.executeQuery();
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
            sql = "SELECT utype_id FROM users_and_types WHERE user_id=?";
            selectStatement = conn.prepareStatement(sql);
            selectStatement.setInt(1, id);
            results = selectStatement.executeQuery();
            int userTypeId = 0;
            if (results.next()) {
                userTypeId = results.getInt("utype_id");
            }
            user = new User(id, login, password, firstName, middleName, lastName, email, phoneNumber, userTypeId);
        }
        results.close();
        selectStatement.close();
        conn.close();
        return user;
    }

    @Override
    public List<User> getUsers(int start, int total) throws SQLException {
        User user = null;
        ResultSet tempResults = null;
        List<User> users = new ArrayList<User>();
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        Statement selectEverythingStatement = conn.createStatement();
        start--;
        String sql = "SELECT * FROM users  limit ";
        sql += start;
        sql += ",";
        sql += total;
        ResultSet results = selectEverythingStatement.executeQuery(sql);
        while (results.next()) {
            int id = results.getInt("user_id");
            String login = results.getString("user_login");
            String password = results.getString("user_password");
            String firstName = results.getString("user_f_name");
            String middleName = results.getString("user_m_name");
            String lastName = results.getString("user_l_name");
            String email = results.getString("user_email");
            String phoneNumber = results.getString("user_phone");
            sql = "SELECT utype_id FROM users_and_types WHERE user_id=?";
            PreparedStatement selectStatement = conn.prepareStatement(sql);
            selectStatement.setInt(1, id);
            tempResults = selectStatement.executeQuery();
            int userTypeId = 0;
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
        conn.close();
        return users;
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "UPDATE users SET user_login=?, user_password=?, user_f_name=?, user_m_name=?, user_l_name=?, "
                + "user_email=?, user_phone=? WHERE user_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setString(1, user.getLogin());
        updateStatement.setString(2, user.getPassword());
        updateStatement.setString(3, user.getFirstName());
        if (user.getMiddleName() == null || user.getMiddleName().equals("")) {
            updateStatement.setString(4, null);
        } else {
            updateStatement.setString(4, user.getMiddleName());
        }
        updateStatement.setString(5, user.getLastName());
        updateStatement.setString(6, user.getEmail());
        if (user.getPhoneNumber() == null || user.getPhoneNumber().equals("")) {
            updateStatement.setString(7, null);
        } else {
            updateStatement.setString(7, user.getPhoneNumber());
        }
        updateStatement.setInt(8, user.getId());
        updateStatement.executeUpdate();
        updateStatement.close();
        conn.close();
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "DELETE FROM users_and_types WHERE user_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        sql = "DELETE FROM comments WHERE user_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        sql = "DELETE FROM accepted_applications WHERE user_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        sql = "DELETE FROM applications WHERE user_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        sql = "DELETE FROM users WHERE user_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);
        deleteStatement.executeUpdate();
        deleteStatement.close();
        conn.close();
    }

    @Override
    public int getNumberOfRecords() throws SQLException {
        int numOfRecords = 0;
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return 0; // test will fail
        }
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM users");
        if (results.next()) {
            numOfRecords = results.getInt("count");
        }
        results.close();
        selectStatement.close();
        conn.close();
        return numOfRecords;
    }
}
