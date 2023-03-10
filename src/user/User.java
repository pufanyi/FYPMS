package user;

/**
 * This class represents a user, which is the base class for other types of users.
 * It includes a user ID and a password field.
 */
public interface User {
    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    String getUserID();

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    void setPassword(String password);

    /**
     * Checks if the password is correct.
     *
     * @param password the password to check
     * @return true if the password is correct, false otherwise
     */
    boolean checkPassword(String password);
}
