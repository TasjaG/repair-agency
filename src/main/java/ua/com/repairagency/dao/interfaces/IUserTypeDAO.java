package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.UserType;

import java.sql.SQLException;
import java.util.List;

/** Interface for user type entity's dao. */
public interface IUserTypeDAO {

    void addUserType(UserType userType) throws SQLException;

    UserType getUserType(int id) throws SQLException;

    List<UserType> getUserTypes() throws SQLException;

    void updateUserType(UserType userType) throws SQLException;

    void deleteUserType(int id) throws SQLException;
}
