package main.boundary.mainpage;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.Logout;
import main.boundary.account.ViewUserProfile;
import main.boundary.modelviewer.ProjectViewer;
import main.boundary.modelviewer.RequestViewer;
import main.controller.request.CoordinatorManager;
import main.model.user.Coordinator;
import main.model.user.User;
import main.model.user.UserType;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Scanner;

import static main.boundary.modelviewer.ProjectViewer.generateProjectDetails;

public class CoordinatorMainPage {
    public static void viewPendingRequests() throws PageBackException {
        ChangePage.changePage();
        System.out.println(BoundaryStrings.separator);
        System.out.println("View Pending Requests");
        System.out.println();
        System.out.println("Here are the pending requests:");
        System.out.println();
        RequestViewer.viewRequests(CoordinatorManager.getPendingRequests());
        System.out.println();
        System.out.println("Press enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    private static void viewAllRequests() throws PageBackException {
        ChangePage.changePage();
        System.out.println(BoundaryStrings.separator);
        System.out.println("View All Requests");
        System.out.println();
        System.out.println("Here are all the requests:");
        System.out.println();
        RequestViewer.viewRequests(CoordinatorManager.getAllRequests());
        System.out.println();
        System.out.println("Press enter to go back.");
        new Scanner(System.in).nextLine();
    }

    public static void coordinatorMainPage(User user) {
        if (user instanceof Coordinator coordinator) {
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
                    case 1 -> ViewUserProfile.viewUserProfilePage(coordinator);
                    case 2 -> ChangeAccountPassword.changePassword(UserType.COORDINATOR, user.getID());
                    case 3 -> ProjectViewer.viewAllProject();
                    case 4 -> viewPendingRequests();
                    case 5 -> viewAllRequests();
                    case 6 -> generateProjectDetails();
                    case 7 -> Logout.logout();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (PageBackException e) {
                CoordinatorMainPage.coordinatorMainPage(coordinator);
            }

        } else {
            throw new IllegalArgumentException("User is not a coordinator.");
        }
    }

}
