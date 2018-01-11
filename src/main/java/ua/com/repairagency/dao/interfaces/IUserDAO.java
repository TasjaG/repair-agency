package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.User;

import java.sql.SQLException;
import java.util.List;

/** Interface for user entity's dao. */
public interface IUserDAO {

    void addUser(User user) throws SQLException;

    User getUser(int id) throws SQLException;

    List<User> getUsers(int start, int total) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;
}
