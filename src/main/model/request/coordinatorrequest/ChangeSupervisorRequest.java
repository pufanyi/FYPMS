package main.model.request.coordinatorrequest;

import main.model.request.ChangeRequest;
import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.Map;

public class ChangeSupervisorRequest extends CoordinatorRequest implements ChangeRequest {
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.COORDINATOR_CHANGE_SUPERVISOR;
    /**
     * The ID of the supervisor
     */
    private String supervisorID;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The ID of the student
     */
    private String studentID;
    /**
     * The ID of the new supervisor
     */
    private String newSupervisorID;
    /**
     * The status of the request
     */
    private RequestStatus status = RequestStatus.PENDING;

    public ChangeSupervisorRequest(String requestID, String supervisorID, String projectID, String studentID, String newSupervisorID) {
        this.requestID = requestID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
        this.studentID = studentID;
        this.newSupervisorID = newSupervisorID;
    }

    public ChangeSupervisorRequest(Map<String, String> map) {
        fromMap(map);
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getNewSupervisorID() {
        return newSupervisorID;
    }

    public void setNewSupervisorID(String newSupervisorID) {
        this.newSupervisorID = newSupervisorID;
    }

    @Override
    public String getID() {
        return requestID;
    }

    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public RequestStatus getStatus() {
        return status;
    }

    @Override
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    @Override
    public void view() {
        return;
    }

//    public void changeSupervisor() {
//        supervisorID = supervisor.getID();
//        project.setSupervisorID(supervisorID);
//    }
}
