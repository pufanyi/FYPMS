package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.request.RequestChange;
import main.model.user.Supervisor;

import java.util.Map;

public class ChangeSupervisorRequest extends CoordinatorRequest implements RequestChange {
    /**
     * The project to be changed
     */
    private Project project;
    /**
     * The supervisor to be changed
     */
    private Supervisor supervisor;
    /**
     * The supervisor ID to be changed
     */
    private String supervisorID;

    /**
     * The constructor of the request
     * @param project the project to be changed
     * @param supervisor the supervisor to be changed
     */
    public ChangeSupervisorRequest(Project project, Supervisor supervisor) {
        super();
        this.project = project;
        this.supervisor = supervisor;
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
     * Change the supervisor of the project
     */
    public void changeSupervisor() {
        supervisorID = supervisor.getID();
        project.setSupervisorID(supervisorID);
    }
}
