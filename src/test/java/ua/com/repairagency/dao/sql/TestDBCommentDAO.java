package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.Database;
import ua.com.repairagency.dao.entities.Comment;
import ua.com.repairagency.dao.interfaces.ICommentDAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A version of Comment dao that uses a connection to a test db.
 * Intended for testing purposes.
 */
public class TestDBCommentDAO implements ICommentDAO {

    @Override
    public void addComment(Comment comment) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "INSERT INTO comments (comment_text, date_created, date_edited, user_id) values (?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);
        insertStatement.setString(1, comment.getText());
        insertStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        insertStatement.setNull(3, Types.TIMESTAMP);
        insertStatement.setInt(4, comment.getUserId());
        insertStatement.executeUpdate();
        insertStatement.close();
        conn.close();
    }

    @Override
    public Comment getComment(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        String sql = "SELECT comment_id, comment_text, date_created, date_edited, user_id FROM comments WHERE comment_id=?";
        PreparedStatement selectStatement = conn.prepareStatement(sql);
        selectStatement.setInt(1, id);
        ResultSet results = selectStatement.executeQuery();
        Comment comment = null;
        if (results.next()) {
            String text = results.getString("comment_text");
            Timestamp dateCreated = results.getTimestamp("date_created");
            Timestamp dateEdited = results.getTimestamp("date_edited");
            int userId = results.getInt("user_id");
            comment = new Comment(id, text, dateCreated, dateEdited, userId);
        }
        results.close();
        selectStatement.close();
        conn.close();
        return comment;
    }

    @Override
    public List<Comment> getComments(int start, int total) throws SQLException {
        List<Comment> comments = new ArrayList<Comment>();
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return null; // test will fail
        }
        Statement selectEverythingStatement = conn.createStatement();
        ResultSet results = selectEverythingStatement.executeQuery("SELECT * FROM comments limit "
                                                                        + (start - 1) + "," + total);
        Comment comment = null;
        while (results.next()) {
            int id = results.getInt("comment_id");
            String text = results.getString("comment_text");
            Timestamp dateCreated = results.getTimestamp("date_created");
            Timestamp dateEdited = results.getTimestamp("date_edited");
            int userId = results.getInt("user_id");
            comment = new Comment(id, text, dateCreated, dateEdited, userId);
            comments.add(comment);
        }
        results.close();
        selectEverythingStatement.close();
        conn.close();
        return comments;
    }

    @Override
    public void updateComment(Comment comment) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "UPDATE comments SET comment_text=?, date_edited=? WHERE comment_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);
        updateStatement.setString(1, comment.getText());
        updateStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(3, comment.getId());
        updateStatement.executeUpdate();
        updateStatement.close();
        conn.close();
    }

    @Override
    public void deleteComment(int id) throws SQLException {
        Connection conn = null;
        try {
            conn = Database.getInstance().getConnection();
        } catch(ClassNotFoundException ex) {
            return; // test will fail
        }
        String sql = "DELETE FROM comments WHERE comment_id=?";
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
        ResultSet results = selectStatement.executeQuery("SELECT COUNT(*) AS count FROM comments");
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
