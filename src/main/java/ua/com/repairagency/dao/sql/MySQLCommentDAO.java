package ua.com.repairagency.dao.sql;

import ua.com.repairagency.connection.ConnectionPool;
import ua.com.repairagency.dao.interfaces.ICommentDAO;
import ua.com.repairagency.dao.entities.Comment;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * MySQL-oriented dao class for comments table
 * CRUD - Create, retrieve, update, delete
 */
public class MySQLCommentDAO implements ICommentDAO {

    /**
     * Adds Comment to comments table.
     *
     * @param comment the comment entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public void addComment(Comment comment) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        // insert data into comments table
        String sql = "INSERT INTO comments (comment_text, date_created, date_edited, user_id) values (?,?,?,?)";
        PreparedStatement insertStatement = conn.prepareStatement(sql);

        insertStatement.setString(1, comment.getText());

        // passes the current time
        insertStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );

        // sets date_edited to null
        insertStatement.setNull(3, Types.TIMESTAMP);
        insertStatement.setInt(4, comment.getUserId());

        insertStatement.executeUpdate();
        insertStatement.close();
    }

    /**
     * Retrieves a Comment object with data from comments table.
     *
     * @param id the primary key of the comment
     * @return the comment entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the prepared statement
     */
    @Override
    public Comment getComment(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

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

        return comment;
    }

    /**
     * Retrieves an ArrayList of Comment objects with data from comments table.
     * The list has a limited amount of elements to support pagination.
     *
     * @param start list's first element
     * @param total page's max amount of table rows
     * @return the list of the comment entities
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a statement,
     *                      if could not execute query,
     *                      if could not get a result set,
     *                      if could not close the result set,
     *                      if could not close the statement
     */
    @Override
    public List<Comment> getCommments(int start, int total) throws SQLException {
        List<Comment> comments = new ArrayList<Comment>();

        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

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

        return comments;
    }

    /**
     * Returns the number of records in table.
     *
     * @return the number of trecords in table
     * TODO @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public int getNumberOfRecords() throws SQLException {

        //

        return 0;
    }

    /**
     * Changes text of the comment and records current time
     * as the the time the comment was edited.
     *
     * @param comment the comment entity
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public void updateComment(Comment comment) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "UPDATE comments SET comment_text=?, date_edited=? WHERE comment_id=?";
        PreparedStatement updateStatement = conn.prepareStatement(sql);

        updateStatement.setString(1, comment.getText());

        // passes the current time
        updateStatement.setTimestamp(2, new Timestamp( System.currentTimeMillis() ) );
        updateStatement.setInt(3, comment.getId());

        updateStatement.executeUpdate();
        updateStatement.close();
    }

    /**
     * Deletes a record in comments table.
     *
     * @param id the primary key of the comment
     * @throws SQLException if could not get connection to the db,
     *                      if could not get a prepared statement,
     *                      if could not execute update,
     *                      if could not close the prepared statement
     */
    @Override
    public void deleteComment(int id) throws SQLException {
        ConnectionPool pool = ConnectionPool.getInstance();
        Connection conn = pool.getConnection();

        String sql = "DELETE FROM comments WHERE comment_id=?";
        PreparedStatement deleteStatement = conn.prepareStatement(sql);
        deleteStatement.setInt(1, id);

        deleteStatement.executeUpdate();
        deleteStatement.close();
    }
}
