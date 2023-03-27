package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.repository.project.ProjectRepository;

import java.util.Map;

public class ViewAllProject extends CoordinatorRequest implements ViewRequest {
    /**
     * The project to be viewed
     */
    private Project project;
    /**
     * The type of the request
     */
    private static final String requestType = "View all projects";

    public ViewAllProject(String requestID) {
        super(requestID);
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
     * View all the projects
     */
    @Override
    public void view() {
        ProjectRepository projectRepository = ProjectRepository.getInstance();
        for(Project project : projectRepository)
            project.displayProject();
    }
}
