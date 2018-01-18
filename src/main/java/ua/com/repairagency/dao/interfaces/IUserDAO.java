package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.User;

import java.sql.SQLException;
import java.util.List;

/** Interface for user entity's dao. */
public interface IUserDAO extends IEntity {

    void addUser(User user) throws SQLException;

    int getIdByLogin(String userName) throws SQLException;

    User getUserByLoginAndPassword(String login, String password) throws SQLException;

    User getUser(int id) throws SQLException;

    List<User> getUsers(int start, int total) throws SQLException;

    void updateUser(User user) throws SQLException;

    void deleteUser(int id) throws SQLException;
}
