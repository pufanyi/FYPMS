package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.request.ChangeRequest;
import main.model.request.RequestType;
import main.model.user.Supervisor;

import java.util.Map;

public class ChangeSupervisorRequest extends CoordinatorRequest implements ChangeRequest {
    private String requestID;
    private final RequestType requestType = RequestType.COORDINATOR_CHANGE_SUPERVISOR;
    private String supervisorID;
    private String projectID;
    private String studentID;
    

//    public void changeSupervisor() {
//        supervisorID = supervisor.getID();
//        project.setSupervisorID(supervisorID);
//    }
}
