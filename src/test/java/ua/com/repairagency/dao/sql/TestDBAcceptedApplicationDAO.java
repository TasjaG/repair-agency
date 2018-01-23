package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.Database;
import ua.com.repairagency.dao.entities.AcceptedApplication;
import ua.com.repairagency.dao.interfaces.IAcceptedApplicationDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A version of Comment dao that uses a connection to a test db.
 * Intended for testing purposes.
 */
public class TestDBAcceptedApplicationDAO implements IAcceptedApplicationDAO {

    @Override
    public void addAcceptedApplication(AcceptedApplication acceptedApp) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "INSERT INTO accepted_applications (aa_product_name, aa_product_comment, aa_price, aa_status, "
                        +"date_completed, application_id, user_id) values (?,?,?,?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);
        insertStatement.setString(1, acceptedApp.getProductName());
        if (acceptedApp.getProductComment().equals("")) {
            insertStatement.setString(2, null);
        } else {
            insertStatement.setString(2, acceptedApp.getProductComment());
        }
        insertStatement.setDouble(3, acceptedApp.getPrice());
        insertStatement.setString(4, "waiting");
        insertStatement.setNull(5, Types.TIMESTAMP);
        insertStatement.setInt(6, acceptedApp.getApplicationId());
        insertStatement.setInt(7, acceptedApp.getUserId());
        insertStatement.executeUpdate();
        insertStatement.close();
        conn.close();
    }

    @Override
    public AcceptedApplication getAcceptedApplication(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
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
        conn.close();
        return acceptedApp;
    }

    @Override
    public List<AcceptedApplication> getAcceptedApplications(int start, int total) throws SQLException {
        List<AcceptedApplication> acceptedApps = new ArrayList<AcceptedApplication>();
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        Statement selectEverythingStatement = conn.createStatement();
        start--;
        String sql = "SELECT * FROM accepted_applications limit ";
        sql += start;
        sql += ",";
        sql += total;
        ResultSet results = selectEverythingStatement.executeQuery(sql);
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
        conn.close();
        return acceptedApps;
    }

    @Override
    public void completeAcceptedApplication(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "UPDATE accepted_applications SET aa_status=?, date_completed=? WHERE aa_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setString(1, "completed");
        updateStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(3, id);
        updateStatement.executeUpdate();
        updateStatement.close();
        conn.close();
    }

    @Override
    public void deleteAcceptedApplication(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "DELETE FROM accepted_applications WHERE aa_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
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
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM accepted_applications");
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