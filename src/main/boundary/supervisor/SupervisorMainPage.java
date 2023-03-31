package main.boundary.supervisor;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.coordinator.CoordinatorMainPage;
import main.boundary.coordinator.details.ProjectDetailsGenerator;
import main.boundary.supervisor.details.ViewSupervisorProfile;
import main.controller.project.ProjectManager;
import main.controller.request.CoordinatorRequestManager;
import main.model.user.Coordinator;
import main.model.user.Supervisor;
import main.model.user.User;
import main.model.user.UserType;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Scanner;

public class SupervisorMainPage {
    public static void supervisorMainPage(User user) {
        if (user instanceof Supervisor supervisor) {
            ChangePage.changePage();
            System.out.println(BoundaryStrings.separator);
            System.out.println("Welcome to Supervisor Main Page");
            System.out.println("Hello, " + user.getUserName() + "!");
            System.out.println();
            System.out.println("\t1. View my profile");
            System.out.println("\t2. Change my password");
            System.out.println("\t3. Create a project ");
            System.out.println("\t4. Modify title of projects");
            System.out.println("\t5. View all pending student requests");
            System.out.println("\t6. Submit request for transferring");
            System.out.println("\t7. View all incoming/outgoing requests' history and status");
            System.out.println("\t8. Logout");
            System.out.println(BoundaryStrings.separator);

            System.out.println();
            System.out.print("Please enter your choice: ");

            Scanner scanner = new Scanner(System.in);

            int choice = scanner.nextInt();

            try {
                switch (choice) {
                    case 1 -> ViewSupervisorProfile.viewSupervisorProfile(supervisor);
                    case 2 -> ChangeAccountPassword.changePassword(UserType.FACULTY,supervisor.getID());
                    case 3 -> supervisorCreateProject(supervisor);
                    case 4 -> supervisorChangeProjectTitle(supervisor);
                    //case 5 -> CoordinatorRequestManager.viewAllRequest();
                    case 6 -> supervisorRequestForTransfer(supervisor);
                    //case 10 -> Logout.logout();

                    default -> System.out.println("Invalid choice. Please try again.");
                }
            } catch (PageBackException e) {
                CoordinatorMainPage.coordinatorMainPage(user);
            } catch (ModelAlreadyExistsException | ModelNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new IllegalArgumentException("User is not a supervisor.");
        }
    }

    private static void supervisorCreateProject(Supervisor supervisor) throws ModelAlreadyExistsException {
            System.out.println("Creating a project....");
            System.out.println("Please enter the Project Title");
            String projectTitle = new Scanner(System.in).next();
            ProjectManager.createProject(projectTitle, supervisor.getID());

    }

    private static void supervisorChangeProjectTitle(Supervisor supervisor) throws ModelAlreadyExistsException, ModelNotFoundException {
            System.out.println("Changing the title of project....");
            System.out.println("Enter the ID of project to change");
            String projectID = new Scanner(System.in).next();
            System.out.println("Enter the new title");
            String newTitle = new Scanner(System.in).next();
            ProjectManager.changeProjectTitle(projectID,newTitle);

    }

    private static void supervisorRequestForTransfer(Supervisor supervisor) throws ModelNotFoundException {
        System.out.println("Processing to transfer....");
        System.out.println("Enter the ID of project to transfer");
        String projectID = new Scanner(System.in).next();
        ProjectManager.transferToNewSupervisor(projectID, supervisor.getID());
    }
}
