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
    private Supervisor supervisor;

    /**
     * the student of the project
     */
    private Student student;
    /**
     * the title of the project
     */
    private String projectTitle;

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

    public Integer getProjectID() {
        return projectID;
    }

    public void display() {
        if (status == ProjectStatus.AVAILABLE) {
            System.out.println("Project ID: " + projectID);
            System.out.println("Supervisor Name: " + supervisor.getUserName());
            System.out.println("Supervisor Email Address: " + supervisor.getEmail());
            System.out.println("Project Title: " + projectTitle);
            System.out.println("Project Status: " + status);
        } else {
            System.out.println("Project ID: " + projectID);
            System.out.println("Supervisor Name: " + supervisor.getUserName());
            System.out.println("Supervisor Email Address: " + supervisor.getEmail());
            System.out.println("Student Name: " + student.getUserName());
            System.out.println("Student Email Address: " + student.getEmail());
            System.out.println("Project Title: " + projectTitle);
            System.out.println("Project Status: " + status);
        }
    }
}
