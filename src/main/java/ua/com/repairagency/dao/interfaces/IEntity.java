package ua.com.repairagency.dao.interfaces;

import java.sql.SQLException;

/** Base interface for all entity interfaces. */
public interface IEntity {

    int getNumberOfRecords() throws SQLException;
}
