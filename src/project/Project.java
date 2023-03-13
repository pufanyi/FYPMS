package project;

import user.singleuser.Supervisor;

public class Project {
    ProjectStatus status;

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
     * @param projectID    the ID of the project
     * @param projectTitle the title of the project
     * @param supervisor   the supervisor of the project
     */
    public Project(int projectID, String projectTitle, Supervisor supervisor) {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.supervisor = supervisor;
        this.status = ProjectStatus.AVAILABLE;
    }
}
