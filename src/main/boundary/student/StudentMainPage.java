package main.boundary.student;

import main.model.user.Student;
import main.model.user.User;
import main.utils.ui.ChangePage;

public class StudentMainPage {
    public static void studentMainPage(User user) {
        if (user instanceof Student student) {
            ChangePage.changePage();
            System.out.println("Welcome to Student Main Page");
            System.out.println("Hello, " + student.getUserName());
            System.out.println();
            System.out.println("1. View my profile");
            System.out.println("2. View project list");
            System.out.println("3. View my project");
            System.out.println("4. View my supervisor");
            System.out.println("5. Register for a project");
            System.out.println("6. Deregister for a project");
            System.out.println("7. Change title for a project");
            System.out.println("8. View history and status of my project");


        } else {
            throw new IllegalArgumentException("User is not a student.");
        }
    }
}
