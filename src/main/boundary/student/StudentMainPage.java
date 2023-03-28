package main.boundary.student;

import main.boundary.student.details.ChangeStudentPassword;
import main.boundary.student.details.ViewStudentProfile;
import main.model.user.Student;
import main.model.user.User;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.util.Scanner;

public class StudentMainPage {
    public static void studentMainPage(User user) {
        if (user instanceof Student student) {
            ChangePage.changePage();
            System.out.println("Welcome to Student Main Page");
            System.out.println("Hello, " + student.getUserName());
            System.out.println();
            System.out.println("1. View my profile");
            System.out.println("2. Change my password");
            System.out.println("3. View project list");
            System.out.println("3. View my project");
            System.out.println("4. View my supervisor");
            System.out.println("5. Register for a project");
            System.out.println("6. Deregister for a project");
            System.out.println("7. Change title for a project");
            System.out.println("8. View history and status of my project");
            System.out.println("9. Logout");

            Scanner scanner = new Scanner(System.in);

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> ViewStudentProfile.viewStudentProfile(student);
                    case 2 -> ChangeStudentPassword.changeStudentPassword(student.getID());
//                  case 3 -> ViewProjectList.viewProjectList();
//                case 4 -> ViewMyProject.viewMyProject(student);
//                case 5 -> ViewMySupervisor.viewMySupervisor(student);
//                case 6 -> RegisterForProject.registerForProject(student);
//                case 7 -> DeregisterForProject.deregisterForProject(student);
//                case 8 -> ChangeTitleForProject.changeTitleForProject(student);
//                case 9 -> ViewHistoryAndStatusOfMyProject.viewHistoryAndStatusOfMyProject(student);
//                case 10 -> Logout.logout();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (PageBackException e) {
                StudentMainPage.studentMainPage(student);
            }


        } else {
            throw new IllegalArgumentException("User is not a student.");
        }
    }
}
