package model.user.password;

import utils.parameters.NotNull;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    /**
     * The password of a user
     */
    private String hashedPassword;

    /**
     * Set the hashed password of a user
     * @param hashedPassword the hashed password of a user
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    /**
     * constructor of a new PasswordManager object with the default password
     */
    public PasswordManager() {
        setPassword("password");
    }

    /**
     * constructor of a new PasswordManager object with the specified password
     *
     * @param password the password of the user
     */
    public PasswordManager(@NotNull String password) {
        setPassword(password);
    }

    /**
     * sets the password for the user
     *
     * @param password the new password for the user.
     */
    public void setPassword(@NotNull String password) {
        this.hashedPassword = PasswordHashManager.hashPassword(password);
    }

    /**
     * checks if the input password is the same as the password of the user
     *
     * @param input the input password
     * @return true if the input password is the same as the password of the user, false otherwise
     */
    public boolean checkPassword(@NotNull String input) {
        return PasswordHashManager.hashPassword(input).equals(hashedPassword);
    }

    /**
     * Gets the hashed password of the user
     *
     * @return the hashed password of the user
     */
    public String getPassword() {
        return hashedPassword;
    }
}
