package main.boundary.mainpage;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.Logout;
import main.boundary.account.ViewUserProfile;
import main.boundary.modelviewer.ModelViewer;
import main.boundary.modelviewer.ProjectViewer;
import main.controller.request.CoordinatorManager;
import main.controller.request.RequestManager;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.RequestType;
import main.model.user.Coordinator;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.CoordinatorRepository;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.iocontrol.IntGetter;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Objects;
import java.util.Scanner;

import static main.boundary.modelviewer.ProjectViewer.generateProjectDetails;

/**
 * This class provides a user interface for coordinators to view their main page.
 */
public class CoordinatorMainPage {
    /**
     * Displays the pending requests for the coordinator.
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
        ModelViewer.displayListOfDisplayable(CoordinatorManager.getPendingRequests());
        System.out.println();
        System.out.println("Press enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * Displays all requests for the coordinator.
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
        ModelViewer.displayListOfDisplayable(CoordinatorManager.getAllRequests());
        System.out.println();
        System.out.println("Press enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * Displays the coordinator main page for the given user.
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
            if (CoordinatorManager.getPendingRequests().size() > 0) {
                System.out.println("\t6. Accept or reject requests " + BoundaryStrings.NEW);
            } else {
                System.out.println("\t6. Accept or reject requests");
            }
            System.out.println("\t7. Generate project details");
            System.out.println("\t8. Logout");
            System.out.println(BoundaryStrings.separator);

            System.out.println();
            System.out.print("Please enter your choice: ");

            int choice = IntGetter.readInt();

            try {
                coordinator = CoordinatorRepository.getInstance().getByID(coordinator.getID());
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }

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
                    default -> {
                        System.out.println("Invalid choice. Please press <enter> to try again.");
                        new Scanner(System.in).nextLine();
                        throw new PageBackException();
                    }
                }
            } catch (PageBackException e) {
                CoordinatorMainPage.coordinatorMainPage(coordinator);
            }

        } else {
            throw new IllegalArgumentException("User is not a coordinator.");
        }
    }

    /**
     * Allows the coordinator to accept or reject requests.
     *
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
    private static void acceptOrRejectRequest() throws PageBackException {
        ChangePage.changePage();
//        System.out.println(BoundaryStrings.separator);
        System.out.println("Accept or Reject Requests");
        ModelViewer.displayListOfDisplayable(CoordinatorManager.getPendingRequests());
        System.out.println("Please enter the ID of the request you want to accept or reject. (Enter 0 to go back.)");
//        System.out.println(BoundaryStrings.separator);
        System.out.print("Please enter your choice: ");

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

            if (request.getStatus() != RequestStatus.PENDING) {
                System.out.println("Request is not pending.");
                System.out.println("Press enter to go back, or enter [0] to try again.");
                String choice = new Scanner(System.in).nextLine();
                if (choice.equals("0")) {
                    acceptOrRejectRequest();
                }
                throw new PageBackException();
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

        ChangePage.changePage();

        System.out.println("Accept or Reject Requests");

        ModelViewer.displaySingleDisplayable(request);

        System.out.println("\t1. Approve");
        System.out.println("\t2. Reject");
        System.out.println("\t3. Go back");
        System.out.println("Please enter your choice: ");
        int choice = IntGetter.readInt();
        switch (choice) {
            case 1 -> {
                try {
                    RequestManager.approveRequest(requestID);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            case 2 -> {
                RequestManager.rejectRequest(requestID);
            }
            case 3 -> {
                acceptOrRejectRequest();
                throw new PageBackException();
            }
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
        ChangePage.changePage();
        try {
            request = RequestManager.getRequest(requestID);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Here is the updated request:");
        ModelViewer.displaySingleDisplayable(request);
        System.out.println("Press enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

}
