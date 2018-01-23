package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;
import ua.com.repairagency.dao.entities.Application;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL-oriented dao class for applications table
 * CRUD - Create, retrieve, update, delete
 */
public class MySQLApplicationDAO implements IApplicationDAO {

    /**
     * Adds Application to applications table.
     *
     * @param application the application entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement,
     *                      if could not close connection
     */
    @Override
    public void addApplication(Application application) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "INSERT INTO applications (product_name, product_comment, date_added, application_status, "
                        + "application_comment, date_processed, user_id) values (?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);

        insertStatement.setString(1, application.getProductName());

        if (application.getProductComment().equals("")) {
            insertStatement.setString(2, null);
        } else {
            insertStatement.setString(2, application.getProductComment());
        }

        // passes the current time
        insertStatement.setTimestamp(3, new Timestamp( System.currentTimeMillis() ) );

        // when the application is created, it's default status is set to "waiting"
        insertStatement.setString(4, "waiting");

        // a comment is only added when application is rejected
        insertStatement.setString(5, null);

        // sets date_processed to null
        insertStatement.setNull(6, Types.TIMESTAMP);
        insertStatement.setInt(7, application.getUserId());

        insertStatement.executeUpdate();
        insertStatement.close();
        pool.closeConnection(conn);
    }

    /**
     * Retrieves an Application object with data from applications table.
     *
     * @param id the primary key of the application
     * @return the application entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the prepared statement,
     *                      if could not close connection
     */
    @Override
    public Application getApplication(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "SELECT product_name, product_comment, date_added, application_status, application_comment, "
                            +"date_processed, user_id FROM applications WHERE application_id=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);

        ResultSet results = selectStatement.executeQuery();

        Application application = null;

        if (results.next()) {
            String productName = results.getString("product_name");
            String productComment = results.getString("product_comment");
            Timestamp dateAdded = results.getTimestamp("date_added");
            String status = results.getString("application_status");
            String comment = results.getString("application_comment");
            Timestamp dateProcessed = results.getTimestamp("date_processed");
            int userId = results.getInt("user_id");

            application = new Application(id, productName, productComment, dateAdded, status, comment,
                                                dateProcessed, userId);
        }
        results.close();
        selectStatement.close();
        pool.closeConnection(conn);

        return application;
    }

    /**
     * Retrieves a list of Application objects with data from applications table.
     * The list has a limited amount of elements to support pagination.
     *
     * @param start list's first element
     * @param total page's max amount of table rows
     * @return the list of the application entities
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the statement,
     *                      if could not close connection
     */
    @Override
    public List<Application> getApplications(int start, int total) throws SQLException {
        List<Application> applications = new ArrayList<Application>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        Statement selectEverythingStatement = conn.createStatement();
        start--;
        String sql = "SELECT * FROM applications limit ";
        sql += start;
        sql += ",";
        sql += total;
        ResultSet results = selectEverythingStatement.executeQuery(sql);

        Application application = null;

        while (results.next()) {
            int id = results.getInt("application_id");
            String productName = results.getString("product_name");
            String productComment = results.getString("product_comment");
            Timestamp dateAdded = results.getTimestamp("date_added");
            String status = results.getString("application_status");
            String comment = results.getString("application_comment");
            Timestamp dateProcessed = results.getTimestamp("date_processed");
            int userId = results.getInt("user_id");

            application = new Application(id, productName, productComment, dateAdded, status, comment,
                                                dateProcessed, userId);
            applications.add(application);
        }
        results.close();
        selectEverythingStatement.close();
        pool.closeConnection(conn);

        return applications;
    }

    /**
     * Sets application_status to "accepted"
     * and sets the current time as date_processed.
     *
     * @param id the primary key of the application
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement,
     *                      if could not close connection
     */
    @Override
    public void acceptApplication(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "UPDATE applications SET application_status=?, date_processed=? WHERE application_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);

        updateStatement.setString(1, "accepted");

        // passes the current time
        updateStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(3, id);

        updateStatement.executeUpdate();
        updateStatement.close();
        pool.closeConnection(conn);
    }

    /**
     * Sets application_status to "rejected" and records an application comment,
     * sets the current time as date_processed.
     *
     * @param application the application entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement,
     *                      if could not close connection
     */
    @Override
    public void rejectApplication(Application application) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "UPDATE applications SET application_status=?, application_comment=?, date_processed=? "
                        + "WHERE application_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);

        updateStatement.setString(1, "rejected");
        updateStatement.setString(2, application.getComment());

        // passes the current time
        updateStatement.setTimestamp(3, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(4, application.getId());

        updateStatement.executeUpdate();
        updateStatement.close();
        pool.closeConnection(conn);
    }

    /**
     * Deletes a record in applications table.
     *
     * @param id the primary key of the application
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement,
     *                      if could not close connection
     */
    @Override
    public void deleteApplication(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        // first deletes record in accepted_applications table due to FK constraints
        String sql = "DELETE FROM accepted_applications WHERE application_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();

        // deletes record from applications table
        sql = "DELETE FROM applications WHERE application_id=?";
        deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();
        pool.closeConnection(conn);
    }

    /**
     * Returns the number of records in table.
     *
     * @return the number of records in applications table
     * @throws if could not get connection to the db,
     *                      if could not get a statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the statement,
     *                      if could not close connection
     */
    @Override
    public int getNumberOfRecords() throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM applications");

        int numOfRecords = 0;

        if (results.next()) {
            numOfRecords = results.getInt("count");
        }
        results.close();
        selectStatement.close();
        pool.closeConnection(conn);

        return numOfRecords;
    }
}
