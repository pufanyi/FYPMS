package main.boundary.modelviewer;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.user.Student;
import main.repository.project.ProjectRepository;
import main.repository.user.FacultyRepository;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Displays the project details.
 */
public class ProjectViewer {
    /**
     * Displays the project details.
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
     * Displays the project details.
     *
     * @param p the project.
     */
    public static void displaySingleProject(Project p) {
        System.out.println("==================================");
        p.displayProject();
        System.out.println("==================================");
    }

    /**
     * Displays the project details.
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
     * Displays the project details.
     */
    public static ProjectStatus getProjectStatus() throws PageBackException {
        System.out.println("\t1. Available");
        System.out.println("\t2. Unavailable");
        System.out.println("\t3. Reserved");
        System.out.println("\t4. Allocated");
        int option = new Scanner(System.in).nextInt();
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
     * generate details of project by ProjectID
     */
    public static void generateDetailsByProjectID() {
        System.out.println("Enter the ProjectID to search");
        String s1 = new Scanner(System.in).next();
        try {
            Project p1 = ProjectRepository.getInstance().getByID(s1);
            p1.displayProject();
        } catch (ModelNotFoundException e) {
            System.out.println("Cannot find the project matching this ID");
        }
    }

    /**
     * generate details of project by SupervisorID
     */
    public static void generateDetailsBySupervisorID() throws PageBackException {
        System.out.println("Enter the SupervisorID to search");
        String s1 = new Scanner(System.in).next();
        if (FacultyRepository.getInstance().contains(s1)) {
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
     * generate details of project by StudentID
     */
    public static void generateDetailsByStudentID() {
        System.out.println("Enter the StudentID to search");
        String s1 = new Scanner(System.in).next();
        for (Project p : ProjectRepository.getInstance().findByRules(p -> Objects.equals(p.getStudentID(), s1)))
            p.displayProject();
    }

    /**
     * generate details of project by Status
     *
     * @throws PageBackException if the user wants to go back
     */
    public static void generateDetailsByStatus() throws PageBackException {
        ProjectStatus status = getProjectStatus();
        for (Project p1 : ProjectRepository.getInstance().findByRules(p -> Objects.equals(p.getStatus(), status)))
            p1.displayProject();
    }

    /**
     * generate details of project
     *
     * @throws PageBackException if the user wants to go back
     */
    public static void generateProjectDetails() throws PageBackException {
        System.out.println("1. By ProjectID");
        System.out.println("2. By SupervisorID");
        System.out.println("3. By Student");
        System.out.println("4. By Status");
        System.out.println("0. Go Back");
        Scanner sc = new Scanner(System.in);
        int c = sc.nextInt();
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
                    System.out.println("Invalid choice. Enter again");
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
        System.out.println("Press Enter to go back");
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
        System.out.println("Press Enter to go back");
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
        System.out.println("Press Enter to go back");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }
}
