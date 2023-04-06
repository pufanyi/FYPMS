package main.controller.account.password;

import main.model.user.User;
import main.utils.exception.model.PasswordIncorrectException;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    public static boolean checkPassword(User user, String password) {
//        System.err.println("Checking password...");
//        System.err.println("User password: " + user.getHashedPassword());
//        System.err.println("Input password: " + PasswordHashManager.hashPassword(password));
        return user.getHashedPassword().equals(PasswordHashManager.hashPassword(password));
    }

    public static void changePassword(User user, String oldPassword, String newPassword) throws PasswordIncorrectException {
        if (checkPassword(user, oldPassword)) {
            user.setHashedPassword(PasswordHashManager.hashPassword(newPassword));
        } else {
            throw new PasswordIncorrectException();
        }
    }
}
