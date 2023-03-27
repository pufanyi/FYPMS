package main.model.user;

public class UserFactory {
    public static User create(UserType userType, String userID, String password, String name, String email) {
        return switch (userType) {
            case STUDENT -> new Student(userID, password, name, email);
            case FACULTY -> new Supervisor(userID, password, name, email);
            case COORDINATOR -> new Coordinator(userID, password, name, email);
        };
    }
}
