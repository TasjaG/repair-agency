package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.AcceptedApplication;

import java.sql.SQLException;
import java.util.List;

/** Interface for accepted application entity's dao. */
public interface IAcceptedApplicationDAO extends IEntity {

    void addAcceptedApplication(AcceptedApplication acceptedApp) throws SQLException;

    AcceptedApplication getAcceptedApplication(int id) throws SQLException;

    List<AcceptedApplication> getAcceptedApplications(int start, int total) throws SQLException;

    void completeAcceptedApplication(int id) throws SQLException;

    void deleteAcceptedApplication(int id) throws SQLException;
}
