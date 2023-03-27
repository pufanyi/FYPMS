package main.controller.account.password;

import main.model.user.User;

/**
 * A class manages the password of a user
 */
public class PasswordManager {
    public static boolean checkPassword(User user, String password) {
        return user.getHashedPassword().equals(PasswordHashManager.hashPassword(password));
    }

    public static void changePassword(User userID, String oldPassword, String newPassword) throws PasswordIncorrectException {
    }
}
