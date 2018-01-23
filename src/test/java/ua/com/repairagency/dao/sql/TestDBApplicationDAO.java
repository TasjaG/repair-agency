package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.Database;
import ua.com.repairagency.dao.entities.Application;
import ua.com.repairagency.dao.interfaces.IApplicationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A version of Application dao that uses a connection to a test db.
 * Intended for testing purposes.
 */
public class TestDBApplicationDAO implements IApplicationDAO {

    @Override
    public void addApplication(Application application) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "INSERT INTO applications (product_name, product_comment, date_added, application_status, "
                        + "application_comment, date_processed, user_id) values (?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);
        insertStatement.setString(1, application.getProductName());
        if (application.getProductComment().equals("")) {
            insertStatement.setString(2, null);
        } else {
            insertStatement.setString(2, application.getProductComment());
        }
        insertStatement.setTimestamp(3, new Timestamp( System.currentTimeMillis() ) );
        insertStatement.setString(4, "waiting");
        insertStatement.setString(5, null);
        insertStatement.setNull(6, Types.TIMESTAMP);
        insertStatement.setInt(7, application.getUserId());
        insertStatement.executeUpdate();
        insertStatement.close();
        conn.close();
    }

    @Override
    public Application getApplication(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
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
        conn.close();
        return application;
    }

    @Override
    public List<Application> getApplications(int start, int total) throws SQLException {
        List<Application> applications = new ArrayList<Application>();
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
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
        conn.close();
        return applications;
    }

    @Override
    public void acceptApplication(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "UPDATE applications SET application_status=?, date_processed=? WHERE application_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setString(1, "accepted");
        updateStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(3, id);
        updateStatement.executeUpdate();
        updateStatement.close();
        conn.close();
    }

    @Override
    public void rejectApplication(Application application) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "UPDATE applications SET application_status=?, application_comment=?, date_processed=? "
                        + "WHERE application_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setString(1, "rejected");
        updateStatement.setString(2, application.getComment());
        updateStatement.setTimestamp(3, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(4, application.getId());
        updateStatement.executeUpdate();
        updateStatement.close();
        conn.close();
    }

    @Override
    public void deleteApplication(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
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
        conn.close();
    }

    @Override
    public int getNumberOfRecords() throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return 0; // test will fail
        }
        Statement selectStatement = conn.createStatement();
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM applications");
        int numOfRecords = 0;
        if (results.next()) {
            numOfRecords = results.getInt("count");
        }
        results.close();
        selectStatement.close();
        conn.close();
        return numOfRecords;
    }
}
