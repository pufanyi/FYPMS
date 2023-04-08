package main.boundary.mainpage;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.Logout;
import main.boundary.account.ViewUserProfile;
import main.boundary.modelviewer.ProjectViewer;
import main.boundary.modelviewer.RequestViewer;
import main.controller.request.CoordinatorManager;
import main.controller.request.RequestManager;
import main.model.request.Request;
import main.model.request.RequestType;
import main.model.user.Coordinator;
import main.model.user.User;
import main.model.user.UserType;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Objects;
import java.util.Scanner;

import static main.boundary.modelviewer.ProjectViewer.generateProjectDetails;

public class CoordinatorMainPage {
    /**
     * Displays the coordinator main page.
     *
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
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

    /**
     * Displays the coordinator main page.
     *
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
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
        throw new PageBackException();
    }

    /**
     * Displays the coordinator main page.
     *
     * @param user the user whose profile is to be displayed.
     */
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
            System.out.println("\t6. Accept or reject requests");
            System.out.println("\t7. Generate project details");
            System.out.println("\t8. Logout");
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
                    case 6 -> acceptOrRejectRequest();
                    case 7 -> generateProjectDetails();
                    case 8 -> Logout.logout();
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (PageBackException e) {
                CoordinatorMainPage.coordinatorMainPage(coordinator);
            }

        } else {
            throw new IllegalArgumentException("User is not a coordinator.");
        }
    }

    /**
     * Displays the coordinator main page.
     *
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
    private static void acceptOrRejectRequest() throws PageBackException {
        ChangePage.changePage();
        System.out.println(BoundaryStrings.separator);
        System.out.println("Accept or Reject Requests");
        System.out.println();
        System.out.println("Please enter the ID of the request you want to accept or reject.");
        System.out.println("Enter 0 to go back.");
        System.out.println();

        String requestID = new Scanner(System.in).nextLine();
        if (requestID.equals("0")) {
            throw new PageBackException();
        }

        Request request;
        try {
            request = RequestManager.getRequest(requestID);

            if (Objects.isNull(request)) {
                throw new ModelNotFoundException();
            }
        } catch (ModelNotFoundException e) {
            System.out.println("Request not found.");
            System.out.println("Press enter to go back, or enter [0] to try again.");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("0")) {
                acceptOrRejectRequest();
            }
            throw new PageBackException();
        }

        if (request.getRequestType() == RequestType.STUDENT_CHANGE_TITLE) {
            System.out.println("You do not have permission to accept or reject this request.");
            System.out.println("Press enter to go back.");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }

        System.out.println("\t1. Approve");
        System.out.println("\t2. Reject");
        System.out.println("\t3. Go back");
        System.out.println("Please enter your choice: ");
        int choice = new Scanner(System.in).nextInt();
        switch (choice) {
            case 1 -> {
                RequestManager.approveRequest(requestID);
                System.out.println("Request processed successfully.");
            }
            case 2 -> {
                RequestManager.rejectRequest(requestID);
                System.out.println("Request processed successfully.");
            }
            case 3 -> throw new PageBackException();
            default -> {
                System.out.println("Invalid choice. Please try again.");
                System.out.println("Press enter to go back, or enter [0] to try again.");
                String choice2 = new Scanner(System.in).nextLine();
                if (choice2.equals("0")) {
                    acceptOrRejectRequest();
                }
                throw new PageBackException();
            }
        }
        System.out.println("Press enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

}
