package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.Application;

import java.sql.SQLException;
import java.util.List;

/** Interface for application entity's dao. */
public interface IApplicationDAO {

    void addApplication(Application application) throws SQLException;

    Application getApplication(int id) throws SQLException;

    List<Application> getApplication() throws SQLException;

    void acceptApplication(int id) throws SQLException;

    void rejectApplication(Application application) throws SQLException;

    void deleteApplication(int id) throws SQLException;
}
