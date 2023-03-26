package main.model.request.supervirsorrequest;

import main.model.project.Project;
import main.model.request.CreateRequest;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;

import java.util.Map;

public class CreateProject extends SupervisorRequest implements CreateRequest{
    /**
     * The project to be created
     */
    private Project project;
    /**
     * The supervisor of the project
     */
    private Supervisor supervisor;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The title of the project
     */
    private String projectTitle;

    /**
     * Constructs a new CreateProject object with the specified project.
     */
    public CreateProject(String requestID, Supervisor supervisor, String projectTitle) {
        super(requestID);
        this.supervisor = supervisor;
        this.projectTitle = projectTitle;
    }

    @Override
    public Map<String, String> toMap() {
        //TODO: fill in the map
        return null;
    }

    @Override
    public void fromMap(Map<String, String> map) {
        //TODO: fill in the map
    }

    /**
     * Generate a new project ID
     * @return the new project ID
     */
    public String generateProjectID() {
        return ProjectRepository.getInstance().size() + "";
    }

    /**
     * Create a new project
     */
    public void createProject() {
        projectID = generateProjectID();
        project = new Project(projectID, projectTitle, supervisor.getID());
    }

}
