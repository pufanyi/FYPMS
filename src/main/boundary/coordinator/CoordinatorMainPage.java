package main.boundary.coordinator;

import main.boundary.coordinator.details.ProjectDetailsGenerator;
import main.controller.project.ProjectManager;
import main.controller.request.CoordinatorRequestManager;
import main.model.user.Coordinator;
import main.model.user.User;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Scanner;

public class CoordinatorMainPage {
    public static void coordinatorMainPage(User user) {
        if (user instanceof Coordinator) {
            ChangePage.changePage();
            System.out.println(BoundaryStrings.separator);
            System.out.println("Welcome to Coordinator Main Page");
            System.out.println("Hello, " + user.getUserName() + "!");
            System.out.println();
            System.out.println("\t1. View my profile");
            System.out.println("\t2. Change my password");
            System.out.println("\t3. View all projects");
            System.out.println("\t4. View pending requests");
            System.out.println("\t5. View all requests' history and status");
            System.out.println("\t6. Generate project details");
            System.out.println("\t7. Logout");
            System.out.println(BoundaryStrings.separator);

            System.out.println();
            System.out.print("Please enter your choice: ");

            Scanner scanner = new Scanner(System.in);

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    //case 1 -> ViewStudentProfile.viewStudentProfile(student);
                    //case 2 -> ChangeStudentPassword.changeStudentPassword(student.getID());
//                    case 3 -> ProjectManager.viewAllProject();
                    case 4 -> CoordinatorRequestManager.viewPendingRequest();
                    case 5 -> CoordinatorRequestManager.viewAllRequest();
                    case 6 -> ProjectDetailsGenerator.generateProjectDetails();
                    //case 10 -> Logout.logout();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (PageBackException e) {
                CoordinatorMainPage.coordinatorMainPage(user);
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new IllegalArgumentException("User is not a student.");
        }
    }
}
