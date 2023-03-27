package main.model.request.coordinatorrequest;

import main.model.request.ChangeRequest;
import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.Map;

public class RejectRequest extends CoordinatorRequest implements ChangeRequest {
    /**
     * The ID of the request
     */
    private String requestID;
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
     * The status of the request
     */
    private RequestStatus status = RequestStatus.PENDING;
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.COORDINATOR_REJECT_REQUEST;

    /**
     * Constructor for RejectRequest
     * @param requestID the ID of the request
     * @param supervisorID the ID of the supervisor
     * @param projectID the ID of the project
     * @param studentID the ID of the student
     */
    public RejectRequest(String requestID, String supervisorID, String projectID, String studentID) {
        this.requestID = requestID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
        this.studentID = studentID;
    }

    /**
     * Constructor for RejectRequest
     * @param map the map of the request
     */
    public RejectRequest(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Get the ID of the supervisor
     * @return the ID of the supervisor
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the ID of the supervisor
     * @param supervisorID the ID of the supervisor
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Get the ID of the project
     * @return the ID of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Set the ID of the project
     * @param projectID the ID of the project
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * Get the ID of the student
     * @return the ID of the student
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the ID of the student
     * @param studentID the ID of the student
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the ID of the request
     * @return the ID of the request
     */
    @Override
    public String getID() {
        return requestID;
    }

    /**
     * Set the ID of the request
     * @return the ID of the request
     */
    @Override
    public RequestStatus getStatus() {
        return status;
    }

    /**
     * Set the status of the request
     * @param status the status of the request.
     */
    @Override
    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    /**
     * Get the type of the request
     * @return the type of the request
     */
    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public void view() {
        return;
    }

//    public void reject() throws IllegalStateException{
//        if(request.getStatus() != RequestStatus.PENDING)
//            throw new IllegalStateException("Request is not pending");
//        else if(request.getStatus() == RequestStatus.APPROVED) {
//            throw new IllegalStateException("Request is already approved");
//        }
//        request.setStatus(RequestStatus.DENIED);
//    }
}
