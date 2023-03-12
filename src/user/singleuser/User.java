package user.singleuser;

import org.jetbrains.annotations.NotNull;

/**
 * A class that represents a user
 */
public interface User {
    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    String getUserID();

    /**
     * Gets the username of the user
     *
     * @return the name of the user
     */
    String getUserName();

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    void setPassword(@NotNull String password);

    /**
     * Checks if the password is correct.
     *
     * @param password the password to check
     * @return true if the password is correct, false otherwise
     */
    boolean checkPassword(@NotNull String password);

    /**
     * Gets the email of the user
     *
     * @return the email of the user
     */
    String getEmail();
}
