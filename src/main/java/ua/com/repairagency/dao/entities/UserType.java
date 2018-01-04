package ua.com.repairagency.dao.entities;

/** Class for the user type entity. */
public class UserType {
    private int id;

    /** The base roles are "admin", "manager", "repairman" and "user". */
    private String role;
    private String description;

    /**
     * Constructor for creating a user type to insert into table via DAO.
     *
     * @param role user type's role
     * @param description user type's description
     */
    public UserType(String role, String description) {
        this.role = role;
        this.description = description;
    }

    /**
     * Constructor for returning a UserType by id via DAO.
     *
     * @param id the primary key of the user type
     * @param role user type's role
     * @param description user type's description
     */
    public UserType(int id, String role, String description) {
        this.id = id;
        this.role = role;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
