package main.boundary.modelviewer;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.user.Student;
import main.repository.project.ProjectRepository;
import main.repository.user.FacultyRepository;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.iocontrol.IntGetter;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.ChangePage;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Displays the project details.
 */
public class ProjectViewer {
    /**
     * Displays the details of a project with the given project ID. It retrieves the project from the ProjectRepository and calls the {@link displaySingleProject(Project p)} method to display the project details.
     *
     * @param projectID the project ID.
     */
    public static void viewProject(String projectID) {
        try {
            Project p = ProjectRepository.getInstance().getByID(projectID);
            displaySingleProject(p);
        } catch (ModelNotFoundException e) {
            System.out.println("Project not found.");
        }
    }

    /**
     * Displays the details of a single project. It takes a Project object as input and calls its {@code displayProject()} method to display the project details.
     *
     * @param p the project.
     */
    public static void displaySingleProject(Project p) {
        System.out.println("==================================");
        p.displayProject();
        System.out.println("==================================");
    }

    /**
     * Displays the details of a list of projects. It takes a list of Project objects as input and iterates through the list to call the displayProject() method on each project, displaying their details.
     *
     * @param projectList the list of projects.
     */
    public static void displayProjectDetails(List<Project> projectList) {
        if (projectList.isEmpty()) {
            System.out.println("No project found.");
            return;
        }
        for (Project p : projectList) {
            System.out.println("==================================");
            p.displayProject();
        }
        System.out.println("==================================");
    }


    /**
     * Displays a menu to the user to select a project status and returns the selected ProjectStatus enum value. If an invalid option is selected, it prompts the user to retry or go back, and throws a PageBackException if the user chooses to go back.
     *
     * @return the selected ProjectStatus enum value.
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
    public static ProjectStatus getProjectStatus() throws PageBackException {
        System.out.println("\t1. Available");
        System.out.println("\t2. Unavailable");
        System.out.println("\t3. Reserved");
        System.out.println("\t4. Allocated");
        int option = IntGetter.readInt();
        return switch (option) {
            case 1 -> ProjectStatus.AVAILABLE;
            case 2 -> ProjectStatus.UNAVAILABLE;
            case 3 -> ProjectStatus.RESERVED;
            case 4 -> ProjectStatus.ALLOCATED;
            default -> {
                System.out.println("Please enter a number between 1-4.");
                System.out.println("Press Enter to retry or enter 0 and press Enter to exit.");
                String input = new Scanner(System.in).nextLine().trim();
                if (input.equals("0")) {
                    throw new PageBackException();
                } else {
                    yield getProjectStatus();
                }
            }
        };
    }

    /**
     * Prompts the user to enter a project ID and displays the details of the project with the matching ID using the ProjectRepository and Project object's displayProject() method.
     */
    public static void generateDetailsByProjectID() {
        System.out.println("Please Enter the ProjectID to search: ");
        String s1 = new Scanner(System.in).next();
        try {
            Project p1 = ProjectRepository.getInstance().getByID(s1);
            p1.displayProject();
        } catch (ModelNotFoundException e) {
            System.out.println("Cannot find the project matching this ID");
        }
    }

    /**
     * Prompts the user to enter a supervisor ID and displays the details of projects supervised by the supervisor with the matching ID. It checks if the supervisor ID is valid using the FacultyRepository and handles cases where the supervisor is not found or the user wants to go back by throwing a PageBackException.
     */
    public static void generateDetailsBySupervisorID() throws PageBackException {
        System.out.println("Please enter the SupervisorID to search: ");
        String s1 = new Scanner(System.in).next();
        if (!FacultyRepository.getInstance().contains(s1)) {
            System.out.println("Supervisor Not Found.");
            System.out.println("Press enter to retry, or enter [b] to go back");
            String input = new Scanner(System.in).nextLine().trim();
            if (input.equals("0")) {
                throw new PageBackException();
            } else {
                generateDetailsBySupervisorID();
                return;
            }
        }
        for (Project p : ProjectRepository.getInstance().findByRules(p -> p.getSupervisorID() == s1))
            p.displayProject();
    }


    /**
     * Prompts the user to enter a student ID and displays the details of projects that require the student with the matching ID. It uses the ProjectRepository to find projects that match the student ID and calls the displayProject() method on each project to display their details. It also handles cases where no projects are found and prompts the user to go back using a PageBackException.
     */
    public static void generateDetailsByStudentID() throws PageBackException {
        System.out.println("Enter the StudentID to search");
        String s1 = new Scanner(System.in).next();
        int ctrl = 0;
        for (Project p : ProjectRepository.getInstance().findByRules(p -> Objects.equals(p.getStudentID(), s1))) {
            p.displayProject();
            ctrl = 1;
        }
        if (ctrl == 0) System.out.println("No projects required is found");
        System.out.println("Enter <Enter> to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * Prompts the user to select a project status and displays the details of projects with the matching status. It calls the getProjectStatus() method to get the selected status, and uses the ProjectRepository to find projects that match the status. It handles cases where no projects are found and prompts the user to go back using a PageBackException.
     *
     * @throws PageBackException if the user wants to go back
     */
    public static void generateDetailsByStatus() throws PageBackException {
        ProjectStatus status = getProjectStatus();
        int ctrl = 0;
        for (Project p1 : ProjectRepository.getInstance().findByRules(p -> Objects.equals(p.getStatus(), status))) {
            p1.displayProject();
            ctrl = 1;
        }
        if (ctrl == 0) System.out.println("No projects required is found");
        System.out.println("Enter <Enter> to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();

    }

    /**
     * Provides a menu to the user to select the way to search for project details and calls the corresponding method based on the user's selection. It also handles cases where the user wants to go back by calling the ChangePage.changePage() method and throwing a PageBackException.
     *
     * @throws PageBackException if the user wants to go back
     */
    public static void generateProjectDetails() throws PageBackException {
        ChangePage.changePage();
        System.out.println(BoundaryStrings.separator);
        System.out.println("Please select the way to search:");
        System.out.println("\t 1. By ProjectID");
        System.out.println("\t 2. By SupervisorID");
        System.out.println("\t 3. By Student");
        System.out.println("\t 4. By Status");
        System.out.println("\t 0. Go Back");
        System.out.println(BoundaryStrings.separator);
        System.out.print("Please enter your choice: ");
        int c = IntGetter.readInt();
        if (c == 0) {
            throw new PageBackException();
        }
        try {
            switch (c) {
                case 1 -> generateDetailsByProjectID();
                case 2 -> generateDetailsBySupervisorID();
                case 3 -> generateDetailsByStudentID();
                case 4 -> generateDetailsByStatus();
                default -> {
                    System.out.println("Invalid choice. Please enter again. ");
                    new Scanner(System.in).nextLine();
                    throw new PageBackException();
                }
            }
        } catch (PageBackException e) {
            generateProjectDetails();
        }
    }

    /**
     * Displays the project details.
     *
     * @throws PageBackException if the user wants to go back
     */
    public static void viewAvailableProjectList() throws PageBackException {
        ChangePage.changePage();
        System.out.println("View Available Project List");
        displayProjectDetails(ProjectManager.viewAvailableProjects());
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * Displays the project details.
     *
     * @throws PageBackException if the user wants to go back
     */
    public static void viewAllProject() throws PageBackException {
        ChangePage.changePage();
        System.out.println("View All Project List");
        displayProjectDetails(ProjectManager.viewAllProject());
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }

    /**
     * Displays the project details.
     *
     * @param student the student
     * @throws PageBackException if the user wants to go back
     */
    public static void viewStudentProject(Student student) throws PageBackException {
        ChangePage.changePage();
        System.out.println("View Student Project");
        Project p = ProjectManager.getStudentProject(student);
        if (p == null) {
            System.out.println("Student has no project yet.");
        } else {
            displaySingleProject(p);
        }
        System.out.println("Press Enter to go back.");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }
}
