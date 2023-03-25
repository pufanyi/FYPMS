package main.model.request.supervirsorrequest;

import main.model.project.Project;
import main.model.request.ChangeRequest;

import java.util.Map;

public class ChangeProjectTitle extends SupervisorRequest implements ChangeRequest {
    /**
     * The project to be changed
     */
    private Project project;
    /**
     * The new title of the project
     */
    private String newTitle;

    public ChangeProjectTitle(Project project, String newTitle) {
        super();
        this.project = project;
        this.newTitle = newTitle;
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
     * Change the title of the project
     */
    public void changeTitle() {
        project.setProjectTitle(newTitle);
    }
}
