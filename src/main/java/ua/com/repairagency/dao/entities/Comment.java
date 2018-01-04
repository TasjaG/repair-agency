package ua.com.repairagency.dao.entities;

import java.sql.Timestamp;

/** Class for the comment entity. */
public class Comment {
    private int id;
    private String text;
    private Timestamp dateCreated;
    private Timestamp dateEdited;

    // FK
    private int userId;

    /**
     * Constructor for creating a comment to insert into table via DAO.
     *
     * @param text the comment's text
     * @param userId the foreign key of the comment
     */
    public Comment(String text, int userId) {
        this.text = text;
        this.userId = userId;
    }

    /**
     * Constructor for returning a Comment by id via DAO.
     *
     * @param id the primary key of the comment
     * @param text the comment's text
     * @param dateCreated comment's date of posting
     * @param dateEdited comment's date of last editing
     * @param userId the foreign key of the comment
     */
    public Comment(int id, String text, Timestamp dateCreated, Timestamp dateEdited, int userId) {
        this.id = id;
        this.text = text;
        this.dateCreated = dateCreated;
        this.dateEdited = dateEdited;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Timestamp getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Timestamp dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Timestamp getDateEdited() {
        return dateEdited;
    }

    public void setDateEdited(Timestamp dateEdited) {
        this.dateEdited = dateEdited;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
