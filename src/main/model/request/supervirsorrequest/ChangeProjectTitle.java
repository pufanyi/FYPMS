package main.model.request.supervirsorrequest;

import main.model.project.Project;
import main.model.request.ChangeRequest;
import main.repository.project.ProjectRepository;

import java.util.HashMap;
import java.util.Map;

public class ChangeProjectTitle extends SupervisorRequest implements ChangeRequest {
    /**
     * The project to be changed
     */
    private Project project;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The new title of the project
     */
    private String newTitle;
    /**
     * The type of the request
     */
    private static final String requestType = "Change project title";

    public ChangeProjectTitle(String requestID, String projectID, String newTitle) {
        super(requestID);
        this.projectID = projectID;
        this.newTitle = newTitle;
    }

    /**
     * Convert the request to a map
     * @return the map
     */
    @Override
    public Map<String, String> toMap() {
        Map<String, String> ans = new HashMap<>();
        ans.put("requestType", requestType);
        ans.put("projectID", projectID);
        ans.put("newTitle", newTitle);
        return ans;
    }

    /**
     *
     * @param map the map
     */
    @Override
    public void fromMap(Map<String, String> map) {
        this.projectID = map.get("projectID");
        this.newTitle = map.get("newTitle");
    }

    /**
     * Change the title of the project
     */
    public void changeTitle() {
        project = ProjectRepository.getInstance().getByID(projectID);
        project.setProjectTitle(newTitle);
    }
}
