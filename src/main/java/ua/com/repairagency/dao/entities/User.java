package ua.com.repairagency.dao.entities;

/** Class for the user entity. */
public class User {
    private int id;
    private String login;
    private String password;
    private String firstName;
    private String middleName;
    private String lastName;
    private String email;
    private String phoneNumber;

    // FK
    private int userTypeId;

    /**
     * Constructor for creating a user to insert into table via DAO.
     *
     * @param login user's login
     * @param password user's password
     * @param firstName user's first name
     * @param middleName user's middle name
     * @param lastName user's last name
     * @param email user's e-mail address
     * @param phoneNumber user's contact number
     * @param userTypeId the foreign key of the user
     */
    public User(String login, String password, String firstName, String middleName,
                    String lastName, String email, String phoneNumber, int userTypeId) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userTypeId = userTypeId;
    }

    /**
     * Constructor for returning a User by id via DAO.
     *
     * @param id the primary key of the user
     * @param login user's login
     * @param password user's password
     * @param firstName user's first name
     * @param middleName user's middle name
     * @param lastName user's last name
     * @param email user's e-mail address
     * @param phoneNumber user's contact number
     * @param userTypeId the foreign key of the user
     */
    public User(int id, String login, String password, String firstName, String middleName,
                String lastName, String email, String phoneNumber, int userTypeId) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.userTypeId = userTypeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }
}
