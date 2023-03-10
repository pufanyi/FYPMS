package project;

import user.Supervisor;

public class Project {
    public enum Status {
        AVAILABLE, RESERVED, UNAVAILABLE, ALLOCATED
    }

    Status status;

    /**
     * The ID of the project
     */
    private int projectID;
    /**
     * the supervisor of the project
     */
    private Supervisor supervisor;
    /**
     * the title of the project
     */
    private String projectTitle;

    /**
     * the constructor of the project
     *
     * @param projectID the ID of the project
     */
    public Project(int projectID, String projectTitle, Supervisor supervisor) {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.supervisor = supervisor;
        status = Status.AVAILABLE;
    }
    
}
