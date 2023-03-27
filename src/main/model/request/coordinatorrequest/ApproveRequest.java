package main.model.request.coordinatorrequest;

import main.model.request.ChangeRequest;
import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.Map;

public class ApproveRequest extends CoordinatorRequest implements ChangeRequest {
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.COORDINATOR_APPROVE_REQUEST;
    /**
     * The status of the request
     */
    private RequestStatus status = RequestStatus.PENDING;
    /**
     * The ID of the student
     */
    private String studentID;
    /**
     * The ID of the supervisor
     */
    private String supervisorID;
    /**
     * The ID of the project
     */
    private String projectID;

    /**
     * Constructs a new ApproveRequest object with the specified request.
     * @param requestID the requestID to be approved
     * @param studentID the studentID to be approved
     * @param supervisorID the supervisorID to be approved
     * @param projectID the projectID to be approved
     */
    public ApproveRequest(String requestID, String studentID, String supervisorID, String projectID) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
    }

    /**
     * Constructs a new ApproveRequest object with the specified map.
     * @param map the map to be converted to an ApproveRequest object
     */
    public ApproveRequest(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Get the studentID
     * @return the studentID
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the studentID
     * @param studentID the studentID to be set
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the supervisorID
     * @return the supervisorID
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the supervisorID
     * @param supervisorID the supervisorID to be set
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Get the projectID
     * @return the projectID
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Set the projectID
     * @param projectID the projectID to be set
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * Get the requestID
     * @return the requestID
     */
    @Override
    public String getID() {
        return requestID;
    }

    /**
     * Get the request type
     * @return the request type
     */
    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Get the status of the request
     * @return the status of the request
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

    @Override
    public void view() {
        return;
    }

//    /**
//     * Approve the request
//     * @throws IllegalStateException if the request is not pending
//     */
//    public void approve() throws IllegalStateException{
//        if(request.getStatus() != RequestStatus.PENDING)
//            throw new IllegalStateException("Request is not pending");
//        else if(request.getStatus() == RequestStatus.APPROVED)
//            throw new IllegalStateException("Request is already approved");
//        request.setStatus(RequestStatus.APPROVED);
//    }
}
