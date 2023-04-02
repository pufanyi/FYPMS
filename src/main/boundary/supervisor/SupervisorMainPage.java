package main.boundary.supervisor;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.ViewUserProfile;
import main.boundary.coordinator.CoordinatorMainPage;
import main.controller.project.ProjectManager;
import main.controller.request.StudentRequestManager;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.RequestType;
import main.model.request.studentrequest.StudentChangeTitleRequest;
import main.model.request.studentrequest.StudentDeregistrationRequest;
import main.model.request.studentrequest.StudentRegistrationRequest;
import main.model.user.Supervisor;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.request.RequestRepository;
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
                    case 1 -> ViewUserProfile.viewUserProfile(supervisor);
                    case 2 -> ChangeAccountPassword.changePassword(UserType.FACULTY,supervisor.getID());
                    case 3 -> supervisorCreateProject(supervisor);
                    case 4 -> supervisorChangeProjectTitle(supervisor);
                    case 5 -> supervisorViewAllPendingRequest();
                    case 6 -> supervisorRequestForTransfer(supervisor);
                    //case 7 ->
                    //case 8 -> Logout.logout();

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
            System.out.println("Enter the project ID to change");
            String projectID = new Scanner(System.in).next();
            System.out.println("Enter the new title");
            String newTitle = new Scanner(System.in).next();
            ProjectManager.changeProjectTitle(projectID,newTitle);

    }

    private static void supervisorRequestForTransfer(Supervisor supervisor) throws ModelNotFoundException {
        System.out.println("Processing to transfer....");
        System.out.println("Enter the project ID to transfer");
        String projectID = new Scanner(System.in).next();
        System.out.println("Enter the new supervisor transfer to");
        String newSupervisor = new Scanner(System.in).next();
        ProjectManager.transferToNewSupervisor(projectID, newSupervisor);
    }

    private static void supervisorViewAllPendingRequest(Supervisor supervisor) throws ModelNotFoundException, ModelAlreadyExistsException {
        System.out.println("Displaying all pending requests by students...");
        for (Request r : RequestRepository.getInstance().findByRules(r -> r.getStatus().equals(RequestStatus.PENDING))) {
            if (r instanceof StudentChangeTitleRequest req)
                if (req.getSupervisorID().equals(supervisor.getID()))
                    req.display();
        }
        System.out.println("Enter Y to process the requests OR Enter any other key to exit");
        char c = new Scanner(System.in).next().charAt(0);
        if (c == 'Y' || c == 'y') {
            System.out.println("Enter the request ID to process");
            String requestID = new Scanner(System.in).next();
            Request r1 = RequestRepository.getInstance().getByID(requestID);
            System.out.println("Press Y to confirm to process the following request");
            r1.display();
            char choice = new Scanner(System.in).next().charAt(0);
            if (choice == 'y' || choice == 'Y') {
                System.out.println("Enter A to approve / R too reject");
                char process = new Scanner(System.in).next().charAt(0);

                if (r1 instanceof StudentChangeTitleRequest req){
                    if (req.getSupervisorID().equals(supervisor.getID())){
                        if (process=='A' || process=='a'){
                            ProjectManager.changeProjectTitle(r1.getProjectID(),req.getNewTitle());
                            StudentRequestManager.approveRequest(requestID);
                            System.out.println("Request approved.");
                        }
                        else if (process=='R' || process=='r'){
                            StudentRequestManager.rejectStudentRequest(requestID);
                            System.out.println("Request rejected.");
                        }
                    }
                    else System.out.println("No access to this request. Process unsuccessful.");
                }
                else {
                    System.out.println("Invalid requestID.Process unsuccessful. ");
                }
            }
            System.out.println("Ending request processing");
        }
    }



}
