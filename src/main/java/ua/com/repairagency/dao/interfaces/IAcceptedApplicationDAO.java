package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.AcceptedApplication;

import java.sql.SQLException;
import java.util.List;

/** Interface for accepted application entity's dao. */
public interface IAcceptedApplicationDAO {

    void addAcceptedApplication(AcceptedApplication acceptedApp) throws SQLException;

    AcceptedApplication getAcceptedApplication(int id) throws SQLException;

    List<AcceptedApplication> getAcceptedApplication() throws SQLException;

    void completeAcceptedApplication(int id) throws SQLException;

    void deleteAcceptedApplication(int id) throws SQLException;
}
