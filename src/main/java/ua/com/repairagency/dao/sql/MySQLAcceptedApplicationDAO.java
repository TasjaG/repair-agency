package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;
import ua.com.repairagency.dao.entities.AcceptedApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL-oriented dao class for accepted_applications table
 * CRUD - Create, retrieve, update, delete
 */
public class MySQLAcceptedApplicationDAO implements IAcceptedApplicationDAO {

    /**
     * Adds AcceptedApplication to accepted_applications table.
     *
     * @param acceptedApp the accepted application entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    public void addAcceptedApplication(AcceptedApplication acceptedApp) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "INSERT INTO accepted_applications (aa_product_name, aa_product_comment, aa_price, aa_status, date_completed, "
                        + "application_id, user_id) values (?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);

        insertStatement.setString(1, acceptedApp.getProductName());
        insertStatement.setString(2, acceptedApp.getProductComment());
        insertStatement.setDouble(3, acceptedApp.getPrice());

        // when the accepted application is created, it's default status is set to "waiting"
        insertStatement.setString(4, "waiting");

        // sets date_completed to null
        insertStatement.setNull(5, Types.TIMESTAMP);

        insertStatement.setInt(6, acceptedApp.getApplicationId());
        insertStatement.setInt(7, acceptedApp.getUserId());

        insertStatement.executeUpdate();
        insertStatement.close();
    }

    /**
     * Retrieves an AcceptedApplication object with data from accepted_applications table.
     *
     * @param id the primary key of the accepted application
     * @return the accepted application entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the prepared statement
     */
    public AcceptedApplication getAcceptedApplication(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "SELECT aa_product_name, aa_product_comment, aa_price, aa_status, date_completed, "
                + "application_id, user_id FROM accepted_applications WHERE aa_id=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);

        ResultSet results = selectStatement.executeQuery();

        AcceptedApplication acceptedApp = null;

        if (results.next()) {
            String productName = results.getString("aa_product_name");
            String productComment = results.getString("aa_product_comment");
            double price = results.getDouble("aa_price");
            String status = results.getString("aa_status");
            Timestamp dateCompleted = results.getTimestamp("date_completed");
            int applicationId = results.getInt("application_id");
            int userId = results.getInt("user_id");

            acceptedApp = new AcceptedApplication(id, productName, productComment, price, status, dateCompleted,
                    applicationId, userId);
        }
        results.close();
        selectStatement.close();

        return acceptedApp;
    }

    /**
     * Retrieves an ArrayList of AcceptedApplication objects with data from accepted_applications table.
     *
     * @return the list of the accepted application entities
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the statement
     */
    public List<AcceptedApplication> getAcceptedApplication() throws SQLException {
        List<AcceptedApplication> acceptedApps = new ArrayList<AcceptedApplication>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        Statement selectEverythingStatement = conn.createStatement();
        ResultSet results = selectEverythingStatement.executeQuery("SELECT * FROM accepted_applications");

        AcceptedApplication acceptedApp = null;

        while (results.next()) {
            int id = results.getInt("aa_id");
            String productName = results.getString("aa_product_name");
            String productComment = results.getString("aa_product_comment");
            double price = results.getDouble("aa_price");
            String status = results.getString("aa_status");
            Timestamp dateCompleted = results.getTimestamp("date_completed");
            int applicationId = results.getInt("application_id");
            int userId = results.getInt("user_id");

            acceptedApp = new AcceptedApplication(id, productName, productComment, price, status, dateCompleted, applicationId, userId);
            acceptedApps.add(acceptedApp);
        }
        results.close();
        selectEverythingStatement.close();

        return acceptedApps;
    }

    /**
     * Sets application_status to "completed"
     * and sets current time as date_completed.
     *
     * @param id the primary key of the accepted application
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    public void completeAcceptedApplication(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "UPDATE applications SET application_status=?, date_processed=? WHERE aa_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);

        updateStatement.setString(1, "completed");

        // passes the current time
        updateStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(3, id);

        updateStatement.executeUpdate();
        updateStatement.close();
    }

    /**
     * Deletes a record in accepted_applications table.
     *
     * @param id the primary key of the accepted application
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    public void deleteAcceptedApplication(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "DELETE FROM accepted_applications WHERE aa_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeQuery();
        deleteStatement.close();
    }
}
