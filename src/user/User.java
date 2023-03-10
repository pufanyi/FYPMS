package user;

/**
 * This class represents a user, which is the base class for other types of users.
 * It includes a user ID and a password field.
 */
public class User {

    /**
     * The ID of the user.
     */
    private String userID;

    /**
     * The password for the user.
     */
    private String password;

    /**
     * Constructs a new User object with the specified user ID.
     *
     * @param userID the ID of the user.
     */
    public User(String userID) {
        this.userID = userID;
        password = "password";
    }

    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
