package project;

import user.singleuser.Student;
import user.singleuser.Supervisor;

public class Project {
    ProjectStatus status;

    /**
     * The ID of the project
     */
    private final Integer projectID;
    /**
     * the supervisor of the project
     */
    private final Supervisor supervisor;

    /**
     * the student of the project
     */
    private Student student;
    /**
     * the title of the project
     */
    private final String projectTitle;

    /**
     * the constructor of the project
     *
     * @param projectID    the ID of the project
     * @param projectTitle the title of the project
     * @param supervisor   the supervisor of the project
     */
    public Project(int projectID, String projectTitle, Supervisor supervisor) {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.supervisor = supervisor;
        this.student = null;
        this.status = ProjectStatus.AVAILABLE;
    }

    /**
     * Get the ID of the project
     * @return the ID of the project
     */
    public Integer getProjectID() {
        return projectID;
    }

    /**
     * Display the information of the supervisor
     */
    private void displaySupervisorInformation() {
        System.out.println("Supervisor Name: " + supervisor.getUserName());
        System.out.println("Supervisor Email Address: " + supervisor.getEmail());
    }

    /**
     * Display the information of the student
     */
    private void displayStudentInformation() {
        System.out.println("Student Name: " + student.getUserName());
        System.out.println("Student Email Address: " + student.getEmail());
    }

    /**
     * Display the ID of the project
     */
    private void displayProjectID() {
        System.out.println("Project ID: " + projectID);
    }

    /**
     * Display the information of the project
     */
    private void displayProjectInformation() {
        System.out.println("Project Title: " + projectTitle);
        System.out.println("Project Status: " + status);
    }

    /**
     * Display the whole information of the project
     */
    public void display() {
        if (status == ProjectStatus.AVAILABLE) {
            displayProjectID();
            displaySupervisorInformation();
            displayProjectInformation();
        } else if (status == ProjectStatus.ALLOCATED) {
            displayProjectID();
            displaySupervisorInformation();
            displayStudentInformation();
            displayProjectInformation();
        } else {
            throw new IllegalStateException("Project status is not AVAILABLE or ALLOCATED.");
        }
    }
}
