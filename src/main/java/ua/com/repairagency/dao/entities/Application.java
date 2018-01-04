package ua.com.repairagency.dao.entities;

import java.sql.Timestamp;

/** Class for the application entity. */
public class Application {
    private int id;
    private String productName;
    private String productComment;
    private Timestamp dateAdded;

    /**
     * The status of the application.
     * Can be "waiting", "accepted" or "rejected".
     */
    private String status;

    /** The reason an application was rejected. */
    private String comment;
    private Timestamp dateProcessed;

    // FK
    private int userId;

    /**
     * Constructor for creating an application to insert into table via DAO.
     *
     * @param productName product's name
     * @param productComment product's comment
     * @param comment application's comment
     * @param userId the foreign key of the application
     */
    public Application(String productName, String productComment, String comment, int userId) {
        this.productName = productName;
        this.productComment = productComment;
        this.comment = comment;
        this.userId = userId;
    }

    /**
     * Constructor for returning an Application by id via DAO.
     *
     * @param id the primary key of the application
     * @param productName product's name
     * @param productComment product's comment
     * @param dateAdded application's date of addition
     * @param status application's status
     * @param comment application's comment
     * @param dateProcessed application's date of processing
     * @param userId the foreign key of the application
     */
    public Application(int id, String productName, String productComment, Timestamp dateAdded,
                       String status, String comment, Timestamp dateProcessed, int userId) {
        this.id = id;
        this.productName = productName;
        this.productComment = productComment;
        this.dateAdded = dateAdded;
        this.status = status;
        this.comment = comment;
        this.dateProcessed = dateProcessed;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductComment() {
        return productComment;
    }

    public void setProductComment(String productComment) {
        this.productComment = productComment;
    }

    public Timestamp getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Timestamp dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getDateProcessed() {
        return dateProcessed;
    }

    public void setDateProcessed(Timestamp dateProcessed) {
        this.dateProcessed = dateProcessed;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}