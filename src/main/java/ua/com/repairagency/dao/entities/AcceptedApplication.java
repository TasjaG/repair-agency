package ua.com.repairagency.dao.entities;

import java.sql.Timestamp;

/** Class for the accepted application entity. */
public class AcceptedApplication {
    private int id;
    private String productName;
    private String productComment;
    private double price;

    /**
     * The status of the accepted application.
     * Can be either "waiting" or "completed".
     */
    private String status;
    private Timestamp dateCompleted;

    // FKs
    private int userId;
    private int applicationId;

    /**
     * Constructor for creating an accepted application to insert into table via DAO.
     *
     * @param productName product's name
     * @param productComment product's comment
     * @param price repair job's price
     * @param applicationId the foreign key of the accepted application
     * @param userId the foreign key of the accepted application
     */
    public AcceptedApplication(String productName, String productComment, double price,
                               int applicationId, int userId) {
        this.productName = productName;
        this.productComment = productComment;
        this.price = price;
        this.applicationId = applicationId;
        this.userId = userId;
    }

    /**
     * Constructor for returning an accepted application by id via DAO.
     *
     * @param id the primary key of the accepted application
     * @param productName product's name
     * @param productComment product's comment
     * @param price repair job's price
     * @param status accepted application's status
     * @param dateCompleted accepted application's date of completion
     * @param applicationId the foreign key of the accepted application
     * @param userId the foreign key of the accepted application
     */
    public AcceptedApplication(int id, String productName, String productComment, double price,
                                    String status, Timestamp dateCompleted, int applicationId, int userId) {
        this.id = id;
        this.productName = productName;
        this.productComment = productComment;
        this.price = price;
        this.status = status;
        this.dateCompleted = dateCompleted;
        this.applicationId = applicationId;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(Timestamp dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(int applicationId) {
        this.applicationId = applicationId;
    }
}
