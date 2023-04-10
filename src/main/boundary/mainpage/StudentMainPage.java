package main.boundary.mainpage;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.Logout;
import main.boundary.account.ViewUserProfile;
import main.boundary.modelviewer.ProjectViewer;
import main.boundary.modelviewer.RequestViewer;
import main.controller.account.AccountManager;
import main.controller.project.ProjectManager;
import main.controller.request.StudentManager;
import main.model.project.Project;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.iocontrol.IntGetter;
import main.utils.parameters.EmptyID;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.Objects;
import java.util.Scanner;

/**
 * This is a Java class that represents the main page of a student in a system or application. It contains several methods for displaying different functionalities of the student main page, such as viewing the user profile, changing the password, viewing project lists, registering/deregistering for projects, changing project title, viewing project history and status, and logging out.
 */
public class StudentMainPage {
    /**
     * This method displays the main page of a student. It takes a User object as a parameter and displays a menu of options for the student to choose from. The user's choice is then processed using a switch statement, which calls different methods based on the choice.
     *
     * @param user The user object of the student.
     */
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

            int choice = IntGetter.readInt();

            try {
                switch (choice) {
                    case 1 -> ViewUserProfile.viewUserProfilePage(student);
                    case 2 -> ChangeAccountPassword.changePassword(UserType.STUDENT, student.getID());
                    case 3 -> ProjectViewer.viewAvailableProjectList();
                    case 4 -> ProjectViewer.viewStudentProject(student);
                    case 5 -> viewMySupervisor(student);
                    case 6 -> registerProject(student);
                    case 7 -> deregisterForProject(student);
                    case 8 -> changeTitleForProject(student);
                    case 9 -> viewHistoryAndStatusOfMyProject(student);
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

    /**
     * This private method is called when the student wants to change the title of their project. It displays the history and status of the student's project, prompts the student to enter a new title, and sends a request to change the title to the system. If an error occurs, the student is given the option to go back or retry.
     *
     * @param student the student.
     * @throws PageBackException if the user wants to go back.
     */
    private static void changeTitleForProject(Student student) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Here is the history and status of your project: ");
        RequestViewer.viewRequests(StudentManager.getStudentRequestHistory(student.getID()));
        String projectID = getProjectID();
        Project project;
        try {
            project = ProjectRepository.getInstance().getByID(projectID);
        } catch (ModelNotFoundException e) {
            throw new IllegalArgumentException("Project not found.");
        }
        if (!Objects.equals(student.getProjectID(), projectID)) {
            System.out.println("You are not registered for this project.");
            System.out.println("Press Enter to go back, or enter [r] to retry.");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("r")) {
                changeTitleForProject(student);
            }
            throw new PageBackException();
        }
        System.out.println("Please enter the new title: ");
        String newTitle = new Scanner(System.in).nextLine();
        try {
            StudentManager.changeProjectTitle(projectID, newTitle, student.getID());
        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
            System.out.println("Enter [b] to go back, or press enter to retry.");
            String choice = new Scanner(System.in).nextLine();
            if (!choice.equals("b")) {
                changeTitleForProject(student);
            }
            throw new PageBackException();
        }
        System.out.println("Successfully sent a request to change title");
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This private method is called when the student wants to deregister from a project. It prompts the student to enter the project ID, sends a request to deregister from the project, and displays a success message. If an error occurs, the student is given the option to go back or retry.
     *
     * @param student the student.
     * @throws PageBackException if the user wants to go back.
     */
    private static void deregisterForProject(Student student) throws PageBackException {
        ChangePage.changePage();
        String projectID = getProjectID();
        try {
            StudentManager.deregisterStudent(projectID, student.getID());
        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
            System.out.println("Enter [b] to go back, or press enter to retry.");
            String choice = new Scanner(System.in).nextLine();
            if (!choice.equals("b")) {
                deregisterForProject(student);
            }
            throw new PageBackException();
        }
        System.out.println("Successfully sent a request to deregister");
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This private method is called to get the project ID from the user. It prompts the user to enter the project ID and returns it as a string. If the user wants to go back, a PageBackException is thrown.
     *
     * @return the project ID.
     * @throws PageBackException if the user wants to go back.
     */
    private static String getProjectID() throws PageBackException {
        System.out.println("Please enter the project ID: ");
        String projectID = new Scanner(System.in).nextLine();
        if (ProjectRepository.getInstance().contains(projectID)) {
            System.out.println("Project found.");
            System.out.println("Here is the project information: ");
            ProjectViewer.viewProject(projectID);
        } else {
            System.out.println("Project not found.");
            System.out.println("Press Enter to go back.");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }
        return projectID;
    }

    /**
     * This private method is called to view the history and status of the student's project. It displays the project's information.
     *
     * @param student the student.
     * @throws PageBackException if the user wants to go back.
     */
    private static void viewHistoryAndStatusOfMyProject(Student student) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Here is the history and status of your project: ");
        RequestViewer.viewRequests(StudentManager.getStudentRequestHistory(student.getID()));
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This private method is called when the student wants to register for a project. It prompts the student to enter the project ID, sends a request to register for the project, and displays a success message. If an error occurs, the student is given the option to go back or retry.
     *
     * @param student the student.
     * @throws PageBackException if the user wants to go back.
     */
    private static void registerProject(Student student) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Here is the list of available projects: ");
        ProjectViewer.displayProjectDetails(ProjectManager.getAllAvailableProject());
        String projectID = new Scanner(System.in).nextLine();
        if (ProjectManager.notContainsProjectByID(projectID)) {
            System.out.println("Project not found.");
            System.out.println("Press Enter to go back, or enter [r] to retry.");
            String choice = new Scanner(System.in).nextLine();
            if (choice.equals("r")) {
                registerProject(student);
            }
            throw new PageBackException();
        }
        Project project;
        try {
            project = ProjectManager.getProjectByID(projectID);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
        ChangePage.changePage();
        System.out.println("Here is the project information: ");
        ProjectViewer.viewProject(projectID);
        System.out.print("Are you sure you want to register for this project? (y/[n]): ");
        String choice = new Scanner(System.in).nextLine();
        if (choice.equalsIgnoreCase("y")) {
            System.out.println("Request submitted!");
        } else {
            System.out.println("Request cancelled.");
            System.out.println("Press <Enter> to go back.");
            new Scanner(System.in).nextLine();
            throw new PageBackException();
        }
        try {
            StudentManager.registerStudent(projectID, student.getID());
            System.out.println("Request submitted!");
        } catch (Exception e) {
//            System.out.println("Error: " + e.getMessage());
            System.out.println("Enter [b] to go back, or press enter to retry.");
            String yNChoice = new Scanner(System.in).nextLine();
            if (yNChoice.equals("b")) {
                throw new PageBackException();
            } else {
                registerProject(student);
            }
        }
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * This private method is called to view the supervisor of the student's project. It displays the supervisor's information.
     *
     * @param student the student.
     * @throws PageBackException if the user wants to go back.
     */
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
