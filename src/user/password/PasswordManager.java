package user.password;

import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    /**
     * The password of a user
     */
    private byte[] hashedPassword;

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
        return Arrays.equals(PasswordHashManager.hashPassword(input), hashedPassword);
    }
}
