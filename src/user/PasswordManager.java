package user;

import java.util.Objects;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    /**
     * The password of a user
     */
    private String password;

    /**
     * constructor of a new PasswordManager object with the default password
     */
    public PasswordManager() {
        password = "password";
    }

    /**
     * constructor of a new PasswordManager object with the specified password
     *
     * @param password the password of the user
     */
    public PasswordManager(String password) {
        this.password = password;
    }

    /**
     * sets the password for the user
     *
     * @param password the new password for the user.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * checks if the input password is the same as the password of the user
     *
     * @param input the input password
     * @return true if the input password is the same as the password of the user, false otherwise
     */
    public boolean checkPassword(String input) {
        return Objects.equals(input, password);
    }
}
