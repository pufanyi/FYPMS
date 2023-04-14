package main.controller.account.password;

import main.model.user.User;
import main.utils.exception.PasswordIncorrectException;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    /**
     * Checks if the password is correct
     *
     * @param user     the user whose password is to be checked
     * @param password the password to be checked
     * @return true if the password is correct, false otherwise
     */
    public static boolean checkPassword(User user, String password) {
//        System.err.println("Checking password...");
//        System.err.println("User password: " + user.getHashedPassword());
//        System.err.println("Input password: " + PasswordHashManager.hashPassword(password));
        return user.getHashedPassword().equals(PasswordHashManager.hashPassword(password));
    }

    /**
     * Changes the password of the user
     *
     * @param user        the user whose password is to be changed
     * @param oldPassword the old password
     * @param newPassword the new password
     * @throws PasswordIncorrectException if the old password is incorrect
     */
    public static void changePassword(User user, String oldPassword, String newPassword) throws PasswordIncorrectException {
        if (checkPassword(user, oldPassword)) {
            user.setHashedPassword(PasswordHashManager.hashPassword(newPassword));
        } else {
            throw new PasswordIncorrectException();
        }
    }
}
