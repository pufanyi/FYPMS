package main.model.request.supervirsorrequest;

import main.model.project.Project;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;

import java.util.List;
import java.util.Map;

public class ViewHisProject extends SupervisorRequest implements ViewRequest {
    /*
     * The project to be viewed
     */
    private Project project;
    /*
     * The supervisor of the project
     */
    private Supervisor supervisor;
    /**
     * The ID of the supervisor
     */
    private String supervisorID;

    public ViewHisProject(String requestID) {
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
     * View this projects
     */
    @Override
    public void view() {
        supervisorID = supervisor.getID();
        List<Project> projectList = ProjectRepository.getInstance().findByRules(project1 -> project1.getSupervisorID().equals(supervisorID));
        for(Project project : projectList) {
            project.displayProject();
        }
    }
}
