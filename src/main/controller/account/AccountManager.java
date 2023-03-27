package main.controller.account;

import main.controller.account.password.PasswordManager;
import main.controller.account.user.UserAdder;
import main.controller.account.user.UserFinder;
import main.controller.account.user.UserUpdater;
import main.model.user.User;
import main.model.user.UserFactory;
import main.model.user.UserType;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

public class AccountManager {
    public static User login(UserType userType, String userID, String password)
            throws PasswordIncorrectException, ModelNotFoundException, ModelAlreadyExistsException {
        User user = UserFinder.findUser(userID, userType);
        if (PasswordManager.checkPassword(user, password)) {
            return user;
        } else {
            throw new PasswordIncorrectException();
        }
    }

    public static void changePassword(UserType userType, String userID, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelAlreadyExistsException, ModelNotFoundException {
        User user = UserFinder.findUser(userID, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserUpdater.updateUser(user);
    }

    public static void register(UserType userType, String userID, String password, String name, String email)
            throws ModelAlreadyExistsException {
//        System.err.println("Registering:");
//        System.err.println("\t" + "UserType = " + userType);
//        System.err.println("\t" + "UserID = " + userID);
//        System.err.println("\t" + "Name = " + name);
//        System.err.println("\t" + "Email = " + email);
        User user = UserFactory.create(userType, userID, password, name, email);
        UserAdder.addUser(user);
    }

    public static User register(UserType userType, String userID, String name, String email)
            throws ModelAlreadyExistsException {
        User user = UserFactory.create(userType, userID, "password", name, email);
        UserAdder.addUser(user);
        return user;
    }
}
