package main.boundary.modelviewer;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
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
     * Display the information of the supervisor.
     *
     * @param supervisorID the supervisor ID.
     */
    private static void displayProjectSupervisorInformation(String supervisorID) {
        try {
            Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
            System.out.printf("| Supervisor Name             | %-30s |\n", supervisor.getUserName());
            System.out.printf("| Supervisor Email Address    | %-30s |\n", supervisor.getEmail());
        } catch (ModelNotFoundException e) {
            System.out.println("No Supervisor Yet");
        }
    }

    /**
     * Display the information of the student.
     *
     * @param studentID the student ID.
     */
    private static void displayProjectStudentInformation(String studentID) {
        try {
            Student student = StudentRepository.getInstance().getByID(studentID);
            System.out.printf("| Student Name               | %-30s |\n", student.getUserName());
            System.out.printf("| Student Email Address      | %-30s |\n", student.getEmail());
        } catch (ModelNotFoundException e) {
            System.out.println("No Student Yet");
        }
    }

    /**
     * Display the details of a project with the given project ID. It retrieves the project from the ProjectRepository and calls the {@link displaySingleProject(Project project)} method to display the project details.
     *
     * @param projectID the project ID.
     */
    public static void viewProject(String projectID) {
        try {
            Project project = ProjectRepository.getInstance().getByID(projectID);
            displaySingleProject(project);
        } catch (ModelNotFoundException e) {
            System.out.println("Project not found.");
        }
    }

    /**
     * Display the information of the project.
     *
     * @param project the project object.
     */
    private static void displayProjectInformation(Project project) {
        System.out.printf("| Project Status              | %-30s |\n", project.getStatus());
    }

    /**
     * Display the complete information of the project.
     *
     * @param project the project object.
     */
    public static void displaySingleProject(Project project) {
        String projectTitle = project.getProjectTitle();
        int maxTitleLength = 60;
        String titleLine1;
        String titleLine2;

        if (projectTitle.length() <= maxTitleLength) {
            int leftPadding = (maxTitleLength - projectTitle.length()) / 2;
            int rightPadding = maxTitleLength - projectTitle.length() - leftPadding;
            titleLine1 = String.format("| %-" + leftPadding + "s%-"+ projectTitle.length() + "s%-" + rightPadding + "s |\n", "", projectTitle, "");
            titleLine2 = "";
        } else {
            String[] words = projectTitle.split("\\s+");
            String firstLine = "";
            String secondLine = "";
            int remainingLength = maxTitleLength;
            int i = 0;
            while (i < words.length) {
                if (firstLine.length() + words[i].length() + 1 <= maxTitleLength) {
                    firstLine += words[i] + " ";
                    remainingLength = maxTitleLength - firstLine.length();
                    i++;
                } else {
                    break;
                }
            }
            for (; i < words.length; i++) {
                if (secondLine.length() + words[i].length() + 1 <= maxTitleLength) {
                    secondLine += words[i] + " ";
                } else {
                    break;
                }
            }
            int leftPadding1 = (maxTitleLength - firstLine.length()) / 2;
            int leftPadding2 = (maxTitleLength - secondLine.length()) / 2;
            int rightPadding1 = maxTitleLength - firstLine.length() - leftPadding1;
            int rightPadding2 = maxTitleLength - secondLine.length() - leftPadding2;
            titleLine1 = String.format("| %-" + leftPadding1 + "s%-"+ firstLine.length() + "s%-" + rightPadding1 + "s |\n", "", firstLine.trim(), "");
            titleLine2 = String.format("| %-" + leftPadding2 + "s%-"+ secondLine.length() + "s%-" + rightPadding2 + "s |\n", "", secondLine.trim(), "");
        }

        System.out.print(titleLine1);
        System.out.print(titleLine2);
        System.out.printf("| Project ID:                 | %-30s |\n", project.getID());
        displayProjectSupervisorInformation(project.getSupervisorID());
        if (project.getStatus() == ProjectStatus.ALLOCATED) {
            displayProjectStudentInformation(project.getStudentID());
        }
        displayProjectInformation(project);
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
        System.out.println("================================================================");
        for (Project p : projectList) {
            displaySingleProject(p);
            System.out.println("================================================================");
        }
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
    public static void generateDetailsByProjectID() throws PageBackException {
        System.out.println("Please Enter the ProjectID to search: ");
        String s1 = new Scanner(System.in).nextLine();
        try {
            Project p1 = ProjectRepository.getInstance().getByID(s1);
            p1.displayProject();
        } catch (ModelNotFoundException e) {
            System.out.println("Cannot find the project matching this ID");
            System.out.println("Press enter to retry, or enter [b] to go back");
            String input = new Scanner(System.in).nextLine().trim();
            if (input.equals("b")) {
                throw new PageBackException();
            } else {
                generateDetailsByProjectID();
            }
        }
        System.out.println("Enter <Enter> to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
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
        List<Project> projectList = ProjectRepository.getInstance().findByRules(p -> p.getSupervisorID().equalsIgnoreCase(s1));
        displayProjectDetails(projectList);
        System.out.println("Enter <Enter> to continue");
        new Scanner(System.in).nextLine();
        throw new PageBackException();
    }


    /**
     * Prompts the user to enter a student ID and displays the details of projects that require the student with the matching ID. It uses the ProjectRepository to find projects that match the student ID and calls the displayProject() method on each project to display their details. It also handles cases where no projects are found and prompts the user to go back using a PageBackException.
     */
    public static void generateDetailsByStudentID() throws PageBackException {
        System.out.println("Enter the StudentID to search");
        String s1 = new Scanner(System.in).next();
        displayProjectDetails(ProjectRepository.getInstance().findByRules(p -> Objects.equals(p.getStudentID(), s1)));
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
        displayProjectDetails(ProjectRepository.getInstance().findByRules(p -> Objects.equals(p.getStatus(), status)));
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
