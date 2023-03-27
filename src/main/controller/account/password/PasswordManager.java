package main.controller.account.password;

import main.model.user.User;
import main.utils.exception.user.PasswordIncorrectException;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    public static boolean checkPassword(User user, String password) {
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
