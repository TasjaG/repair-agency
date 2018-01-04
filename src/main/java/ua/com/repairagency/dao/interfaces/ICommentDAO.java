package ua.com.repairagency.dao.interfaces;

import ua.com.repairagency.dao.entities.Comment;

import java.sql.SQLException;
import java.util.List;

/** Interface for comment entity's dao. */
public interface ICommentDAO {

    void addComment(Comment comment) throws SQLException;

    Comment getComment(int id) throws SQLException;

    List<Comment> getCommment() throws SQLException;

    void updateComment(Comment comment) throws SQLException;

    void deleteComment(int id) throws SQLException;
}
