package main.boundary.mainpage;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.Logout;
import main.boundary.account.ViewUserProfile;
import main.boundary.modelviewer.ModelViewer;
import main.controller.project.ProjectManager;
import main.controller.request.RequestManager;
import main.controller.request.SupervisorManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.RequestType;
import main.model.user.Supervisor;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.FacultyRepository;
import main.utils.exception.ModelAlreadyExistsException;
import main.utils.exception.ModelNotFoundException;
import main.utils.exception.PageBackException;
import main.utils.exception.SupervisorStudentsLimitExceedException;
import main.utils.iocontrol.IntGetter;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * The {@code SupervisorMainPage} class is a boundary class that represents the main page for a supervisor in a system. It provides methods to display the main page options and handle user input for different actions, such as viewing the supervisor's profile, changing the supervisor's password, creating a project, modifying project titles, viewing pending student requests, approving or rejecting student requests, submitting requests for transferring, viewing all incoming/outgoing request history and status, and logging out.
 */
public class SupervisorMainPage {
    /**
     * This method displays the main page options to the supervisor and handles user input to perform corresponding actions based on the selected option.
     *
     * @param user the supervisor.
     */
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
            System.out.println("\t4. View all my projects");
            System.out.println("\t5. Modify title of projects");
            System.out.println("\t6. View all pending student requests");
            if (SupervisorManager.getPendingRequestsBySupervisor(supervisor.getID()).size() > 0) {
                System.out.println("\t7. Approve/Reject student requests " + BoundaryStrings.NEW);
            } else {
                System.out.println("\t7. Approve/Reject student requests");
            }
            System.out.println("\t8. Submit request for transferring");
            System.out.println("\t9. View all incoming/outgoing requests' history and status");
            System.out.println("\t10. Logout");
            System.out.println(BoundaryStrings.separator);

            System.out.println();
            System.out.print("Please enter your choice: ");

            int choice = IntGetter.readInt();

            try {
                supervisor = FacultyRepository.getInstance().getByID(supervisor.getID());
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }

            try {
                switch (choice) {
                    case 1 -> ViewUserProfile.viewUserProfilePage(supervisor);
                    case 2 -> ChangeAccountPassword.changePassword(UserType.FACULTY, supervisor.getID());
                    case 3 -> supervisorCreateProject(supervisor);
                    case 4 -> supervisorViewAllProjects(supervisor);
                    case 5 -> supervisorChangeProjectTitle(supervisor);
                    case 6 -> supervisorViewAllPendingRequest(supervisor);
                    case 7 -> supervisorApproveOrRejectRequest(supervisor);
                    case 8 -> supervisorRequestForTransfer(supervisor);
                    case 9 -> supervisorViewAllRequestHistory(supervisor);
                    case 10 -> Logout.logout();

                    default -> {
                        System.out.println("Invalid choice. Please press <enter> to try again.");
                        new Scanner(System.in).nextLine();
                        throw new PageBackException();
                    }
                }
            } catch (PageBackException e) {
                supervisorMainPage(supervisor);
            } catch (ModelAlreadyExistsException | ModelNotFoundException e) {
                throw new RuntimeException(e);
            }

        } else {
            throw new IllegalArgumentException("User is not a supervisor.");
        }
    }

    private static void supervisorViewAllProjects(Supervisor supervisor) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Viewing all projects....");
        List<Project> projects = ProjectManager.getAllProjectsBySupervisor(supervisor.getID());
        ModelViewer.displayListOfDisplayable(projects);
        System.out.println("Enter enter to go back");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This method displays all request history for the supervisor.
     *
     * @param supervisor the supervisor.
     */
    private static void supervisorViewAllRequestHistory(Supervisor supervisor) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Viewing all request history....");
        List<Request> requests = SupervisorManager.getAllRequestHistory(supervisor);
        ModelViewer.displayListOfDisplayable(requests);
        System.out.println("Enter enter to go back");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This method allows the supervisor to approve or reject a request by entering the request ID and the desired status.
     *
     * @param supervisor the supervisor.
     * @throws PageBackException if the user wants to go back to the previous page.
     */
    private static void supervisorApproveOrRejectRequest(Supervisor supervisor) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Approving or rejecting a request....");
        System.out.println("Here are all pending requests:");
        List<Request> requests = SupervisorManager.getPendingRequestsBySupervisor(supervisor.getID());
        ModelViewer.displayListOfDisplayable(requests);
        System.out.print("Enter the request ID to approve or reject (or [0] to go back): ");
        String requestID = new Scanner(System.in).nextLine();
        if (requestID.equals("0")) {
            throw new PageBackException();
        }
        Request request;
        try {
            request = RequestRepository.getInstance().getByID(requestID);
        } catch (ModelNotFoundException e) {
            System.out.println("Request not found!");
            System.out.println("Enter enter to go back, or enter 0 to retry");
            String input = new Scanner(System.in).nextLine();
            if (input.equals("0")) {
                supervisorApproveOrRejectRequest(supervisor);
            }
            throw new PageBackException();
        }
//        System.err.println(request.getStatus());
        if (!Objects.equals(request.getSupervisorID(), supervisor.getID())
                || !Objects.equals(request.getRequestType(), RequestType.STUDENT_CHANGE_TITLE)) {
            System.out.println("Request is not assigned to you!");
            System.out.println("Enter enter to go back, or enter 0 to retry");
            String input = new Scanner(System.in).nextLine();
            if (input.equals("0")) {
                supervisorApproveOrRejectRequest(supervisor);
            }
            throw new PageBackException();
        }
        if (request.getStatus() != RequestStatus.PENDING) {
            System.out.println("Request is not pending!");
            System.out.println("Enter enter to continue");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }
        ChangePage.changePage();
        System.out.println("Here is the request:");
        ModelViewer.displaySingleDisplayable(request);
        System.out.println("Enter the status to change to APPROVED (A) / REJECTED (R)");
        String status = new Scanner(System.in).nextLine();
        if (status.equalsIgnoreCase("A") ||
                status.equalsIgnoreCase("APPROVED")) {
            try {
                RequestManager.approveRequest(requestID);
            } catch (SupervisorStudentsLimitExceedException e) {
                throw new RuntimeException(e);
            }
        } else if (status.equalsIgnoreCase("REJECTED") ||
                status.equalsIgnoreCase("R")) {
            RequestManager.rejectRequest(requestID);
        } else {
            System.out.println("Invalid status!");
            System.out.println("Enter enter to go back, or enter 0 to retry");
            String input = new Scanner(System.in).nextLine();
            if (input.equals("0")) {
                supervisorApproveOrRejectRequest(supervisor);
            }
        }
        try {
            request = RequestRepository.getInstance().getByID(requestID);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
        ChangePage.changePage();
        System.out.println("Here is the updated request:");
        ModelViewer.displaySingleDisplayable(request);
        System.out.println("Enter enter to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This method allows the supervisor to create a project by entering the required details such as project title, project description, and project capacity.
     *
     * @param supervisor the supervisor.
     * @throws PageBackException if the user wants to go back to the previous page.
     */
    private static void supervisorCreateProject(Supervisor supervisor) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Creating a project....");
        System.out.println("Please enter the Project Title:");
        String projectTitle = new Scanner(System.in).nextLine();
        Project p;
        try {
            p = ProjectManager.createProject(projectTitle, supervisor.getID());
        } catch (ModelAlreadyExistsException e) {
            throw new RuntimeException(e);
        }
        System.out.println("The project details are as follows:");
        ModelViewer.displaySingleDisplayable(p);
        System.out.println("Are you sure you want to create this project? (Y/N)");
        String input = new Scanner(System.in).nextLine();
        if (!input.equalsIgnoreCase("Y")) {
            System.out.println("Project creation cancelled!");
            try {
                ProjectRepository.getInstance().remove(p.getID());
                ProjectManager.updateProjectsStatus();
            } catch (ModelNotFoundException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Enter enter to continue");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }
        System.out.println("Project created successfully!");
        System.out.println("Enter enter to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This method allows the supervisor to modify the title of an existing project by entering the project ID and the new title.
     *
     * @param supervisor the supervisor.
     * @throws ModelAlreadyExistsException if the project already exists.
     * @throws ModelNotFoundException      if the project is not found.
     * @throws PageBackException           if the user wants to go back to the previous page.
     */
    private static void supervisorChangeProjectTitle(Supervisor supervisor) throws ModelAlreadyExistsException, ModelNotFoundException, PageBackException {
        ChangePage.changePage();
        System.out.println("Changing the title of project....");
        List<Project> projects = ProjectRepository.getInstance().findByRules(
                project -> Objects.equals(project.getSupervisorID(), supervisor.getID())
        );
        System.out.println("Here are all your projects:");
        ModelViewer.displayListOfDisplayable(projects);
        System.out.print("Enter the project ID to change: ");
        String projectID = new Scanner(System.in).nextLine();
        Project p = ProjectRepository.getInstance().getByID(projectID);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            if (ProjectManager.notContainsProjectByID(projectID)) {
                System.out.println("Project Not Found! Enter again or Enter b to exit");
            } else if (!Objects.equals(p.getSupervisorID(), supervisor.getID())) {
                System.out.println("Project created by other supervisor! No access! Enter again or Enter b to exit");
            } else break;
            System.out.print("Enter the project ID to change: ");
            projectID = scanner.nextLine();
            if (projectID.equals("b")) {
                throw new PageBackException();
            }
        }
        ChangePage.changePage();
        System.out.println("Here is the project:");
        ModelViewer.displaySingleDisplayable(p);
        System.out.println("Enter the new title:");
        String newTitle = new Scanner(System.in).nextLine();
        p.setProjectTitle(newTitle);
        ChangePage.changePage();
        System.out.println("Here is the new project after changing the title:");
        ModelViewer.displaySingleDisplayable(p);
        System.out.println("Are you sure you want to change the title? (Y/[N])");
        String input = new Scanner(System.in).nextLine();
        if (!input.equalsIgnoreCase("Y")) {
            System.out.println("Project title change cancelled!");
            System.out.println("Enter enter to continue");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }
        ProjectManager.changeProjectTitle(projectID, newTitle);
        System.out.println("Project title changed successfully!");
        System.out.println("Enter enter to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();

    }

    /**
     * This method allows the supervisor to submit a request for transferring a project by entering the project ID and the student ID.
     *
     * @param supervisor the supervisor.
     * @throws PageBackException if the user wants to go back to the previous page.
     */
    private static void supervisorRequestForTransfer(Supervisor supervisor) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Processing to transfer....");
        System.out.println("Below are all your projects ready for transfer:");
        List<Project> projects = ProjectRepository.getInstance().findByRules(
                project -> Objects.equals(project.getSupervisorID(), supervisor.getID()),
                project -> project.getStatus() == ProjectStatus.ALLOCATED
        );
        if (projects.isEmpty()) {
            System.out.println("No project available for transfer!");
            System.out.println("Enter enter to continue");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }
        ModelViewer.displayListOfDisplayable(projects);
        System.out.println("Enter the project ID to transfer: ");
        Scanner scanner = new Scanner(System.in);
        String projectID = scanner.nextLine();
        while (ProjectManager.notContainsProjectByID(projectID)) {
            System.out.println("Project Not Found! Enter again or Enter b to exit");
            projectID = scanner.nextLine();
            if (projectID.equals("b")) {
                throw new PageBackException();
            }
        }
        Project project;
        try {
            project = ProjectRepository.getInstance().getByID(projectID);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
        if (!Objects.equals(project.getSupervisorID(), supervisor.getID())) {
            System.out.println("Project created by other supervisor! No access!");
            System.out.println("Enter enter to continue, or enter b to exit");
            String input = scanner.nextLine();
            if (input.equals("b")) {
                throw new PageBackException();
            }
            supervisorRequestForTransfer(supervisor);
            throw new PageBackException();
        }
        if (project.getStatus() != ProjectStatus.ALLOCATED) {
            System.out.println("Project not allocated!");
            System.out.println("Enter enter to continue, or enter b to exit");
            String input = scanner.nextLine();
            if (input.equals("b")) {
                throw new PageBackException();
            }
            supervisorRequestForTransfer(supervisor);
            throw new PageBackException();
        }
        ChangePage.changePage();
        System.out.println("Here is the project:");
        ModelViewer.displaySingleDisplayable(project);
        System.out.println("Enter the new supervisor ID transfer to: ");
        String newSupervisor = scanner.nextLine();
        while (!FacultyRepository.getInstance().contains(newSupervisor)) {
            System.out.println("User Not Found! Enter again or Enter b to exit");
            newSupervisor = scanner.nextLine();
            if (newSupervisor.equals("b")) {
                throw new PageBackException();
            }
        }
        String oldStudentID = project.getStudentID();
        String oldSupervisorID = project.getSupervisorID();

        ChangePage.changePage();

        project.setSupervisorID(newSupervisor);
        System.out.println("Here is the new project after changing the supervisor:");
        ModelViewer.displaySingleDisplayable(project);
        System.out.println("Are you sure you want to change the supervisor? (Y/[N])");
        String input = new Scanner(System.in).nextLine();
        if (!input.equalsIgnoreCase("Y")) {
            System.out.println("Project supervisor change cancelled!");
            System.out.println("Enter enter to continue");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }

        SupervisorManager.transferToNewSupervisor(projectID, oldSupervisorID, newSupervisor, oldStudentID);
        System.out.println("Successfully sent request for transfer!");
        System.out.println("Enter enter to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This method allows the supervisor to view all the requests that are pending for approval.
     *
     * @param requests the list of requests.
     * @return the request ID.
     * @throws PageBackException if the user wants to go back to the previous page.
     */
    private static String getRequestToProcess(List<Request> requests) throws PageBackException {
        System.out.println("==================================");
        System.out.println("Enter the request ID to process");
        Scanner scanner = new Scanner(System.in);
        String requestID = scanner.nextLine();
        boolean found = false;
        for (Request r : requests) {
            if (r.getID().equals(requestID)) {
                found = true;
                return requestID;
            }
        }
        System.out.println("Request Not Found! Enter again or Enter b to exit");
        requestID = scanner.nextLine();
        if (requestID.equals("b")) {
            throw new PageBackException();
        } else {
            return getRequestToProcess(requests);
        }
    }

    /**
     * This method displays all pending student requests for the supervisor.
     *
     * @param supervisor the supervisor.
     * @throws PageBackException if the user wants to go back to the previous page.
     */
    private static void supervisorViewAllPendingRequest(Supervisor supervisor) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Displaying all pending requests by students...");
        List<Request> requests = SupervisorManager.getPendingRequestsBySupervisor(supervisor.getID());
        ModelViewer.displayListOfDisplayable(requests);
//        if (requests.isEmpty()) {
//            System.out.println("Enter any key to continue");
//            new Scanner(System.in).nextLine();
//            throw new PageBackException();
//        }
//        System.out.println("Enter Y to process the requests OR Enter any other key to exit");
//        String c = new Scanner(System.in).nextLine();
//        if (!c.equalsIgnoreCase("Y")) {
//            throw new PageBackException();
//        }
//        String requestID = getRequestToProcess(requests);
//        Request r1;
//        try {
//            r1 = RequestRepository.getInstance().getByID(requestID);
//        } catch (ModelNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Press Y to confirm to process the following request");
//        r1.display();
//        String choice = new Scanner(System.in).nextLine();
//        if (!choice.equalsIgnoreCase("Y")) {
//            System.out.println("Ending request processing, press any key to continue");
//            new Scanner(System.in).nextLine();
//            throw new PageBackException();
//        }
//        System.out.println("Enter A to approve / R to reject");
//        char process = new Scanner(System.in).nextLine().charAt(0);
//
//        if (r1 instanceof StudentChangeTitleRequest req) {
//            if (req.getSupervisorID().equals(supervisor.getID()) && req.getStatus() == RequestStatus.PENDING) {
//                if (process == 'A' || process == 'a') {
//                    try {
//                        ProjectManager.changeProjectTitle(r1.getProjectID(), req.getNewTitle());
//                        RequestManager.approveRequestForStatus(requestID);
//                    } catch (ModelNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("Request approved.");
//                } else if (process == 'R' || process == 'r') {
//                    try {
//                        RequestManager.rejectRequestForStatus(requestID);
//                    } catch (ModelNotFoundException e) {
//                        throw new RuntimeException(e);
//                    }
//                    System.out.println("Request rejected.");
//                }
//            } else
//                System.out.println("No access to this request or request is not pending. Process unsuccessful.");
//        } else {
//            System.out.println("Invalid requestID. Process unsuccessful. ");
//        }
        System.out.println("Enter any enter to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }
}
