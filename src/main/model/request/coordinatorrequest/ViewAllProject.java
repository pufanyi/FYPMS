package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.repository.project.ProjectRepository;

import java.util.Map;

public class ViewAllProject{
    /**
     * The project to be viewed
     */
    private Project project;
    /**
     * The type of the request
     */
    private static final String requestType = "View all projects";

    /**
     * View all the projects
     */
    public void view() {
        ProjectRepository projectRepository = ProjectRepository.getInstance();
        for(Project project : projectRepository)
            project.displayProject();
    }
}
