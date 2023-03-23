package main.model.user;

import main.model.Model;
import main.model.user.password.PasswordManager;
import main.utils.parameters.NotNull;

/**
 * A class that represents a user
 */
public abstract class User extends Model {
    private final PasswordManager passwordManager;

    User() {
        this.passwordManager = new PasswordManager();
    }

    User(@NotNull String password) {
        this.passwordManager = new PasswordManager(password);
    }


    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    public abstract String getID();

    /**
     * Gets the username of the user
     *
     * @return the name of the user
     */
    public abstract String getUserName();

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    public final void setPassword(@NotNull String password) {
        this.passwordManager.setPassword(password);
    }

    /**
     * Checks if the password is correct.
     *
     * @param password the password to check
     * @return true if the password is correct, false otherwise
     */
    public final boolean checkPassword(@NotNull String password) {
        return this.passwordManager.checkPassword(password);
    }

    public final String getPassword() {
        return this.passwordManager.getPassword();
    }

    /**
     * Gets the email of the user
     *
     * @return the email of the user
     */
    public abstract String getEmail();
}
