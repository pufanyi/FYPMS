package main.model.user;

import main.controller.account.password.PasswordHashManager;

public class UserFactory {
    public static User create(UserType userType, String userID, String password, String name, String email) {
        String hashedPassword = PasswordHashManager.hashPassword(password);
        return switch (userType) {
            case STUDENT -> new Student(userID, name, email, hashedPassword);
            case FACULTY -> new Supervisor(userID, name, email, hashedPassword);
            case COORDINATOR -> new Coordinator(userID, name, email, hashedPassword);
        };
    }
}
