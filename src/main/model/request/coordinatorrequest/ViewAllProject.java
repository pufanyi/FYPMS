package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.request.RequestView;
import main.repository.project.ProjectRepository;

import java.util.Map;

public class ViewAllProject extends CoordinatorRequest implements RequestView {
    /**
     * The project to be viewed
     */
    private Project project;

    public ViewAllProject() {
        super();
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
    public void viewAllProject() {
        ProjectRepository projectRepository = ProjectRepository.getInstance();
        for(Project project : projectRepository)
            project.displayProject();
    }
}
