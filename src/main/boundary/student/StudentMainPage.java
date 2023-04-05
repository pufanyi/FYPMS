package main.boundary.student;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.Logout;
import main.boundary.account.ViewUserProfile;
import main.boundary.project.ProjectViewer;
import main.controller.account.AccountManager;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.model.user.UserType;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.parameters.EmptyID;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Scanner;

public class StudentMainPage {
    public static void studentMainPage(User user) {
        if (user instanceof Student student) {
            ChangePage.changePage();
            System.out.println(BoundaryStrings.separator);
            System.out.println("Welcome to Student Main Page");
            System.out.println("Hello, " + student.getUserName() + "!");
            System.out.println();
            System.out.println("\t1. View my profile");
            System.out.println("\t2. Change my password");
            System.out.println("\t3. View project list");
            System.out.println("\t4. View my project");
            System.out.println("\t5. View my supervisor");
            System.out.println("\t6. Register for a project");
            System.out.println("\t7. Deregister for a project");
            System.out.println("\t8. Change title for a project");
            System.out.println("\t9. View history and status of my project");
            System.out.println("\t10. Logout");
            System.out.println(BoundaryStrings.separator);

            System.out.println();
            System.out.print("Please enter your choice: ");

            Scanner scanner = new Scanner(System.in);

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> ViewUserProfile.viewUserProfilePage(student);
                    case 2 -> ChangeAccountPassword.changePassword(UserType.STUDENT, student.getID());
                    case 3 -> ProjectViewer.viewAvailableProjectList();
                    case 4 -> ProjectViewer.viewStudentProject(student);
                    case 5 -> viewMySupervisor(student);
//                case 6 -> RegisterForProject.registerForProject(student);
//                case 7 -> DeregisterForProject.deregisterForProject(student);
//                case 8 -> ChangeTitleForProject.changeTitleForProject(student);
//                case 9 -> ViewHistoryAndStatusOfMyProject.viewHistoryAndStatusOfMyProject(student);
                    case 10 -> Logout.logout();
                    default -> {
                        System.out.println("Invalid choice. Please press enter to try again.");
                        new Scanner(System.in).nextLine();
                        throw new PageBackException();
                    }
                }
            } catch (PageBackException e) {
                StudentMainPage.studentMainPage(student);
            }


        } else {
            throw new IllegalArgumentException("User is not a student.");
        }
    }

    private static void viewMySupervisor(Student student) throws PageBackException {
        ChangePage.changePage();
        if (EmptyID.isEmptyID(student.getSupervisorID())) {
            System.out.println("You do not have a supervisor.");
        } else {
            try {
                Supervisor supervisor = (Supervisor) AccountManager.getByDomainAndID(UserType.FACULTY, student.getSupervisorID());
                ViewUserProfile.viewUserProfile(supervisor);
            } catch (ModelNotFoundException e) {
                System.out.println("Your supervisor is not found.");
            }
        }
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }
}
