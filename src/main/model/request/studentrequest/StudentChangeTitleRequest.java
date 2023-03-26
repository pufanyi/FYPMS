package main.model.request.studentrequest;

import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.HashMap;
import java.util.Map;

public class StudentChangeTitleRequest implements StudentRequest {
    private String requestID;
    private final RequestType requestType = RequestType.STUDENT_CHANGE_TITLE;
    private RequestStatus requestStatus = RequestStatus.PENDING;
    private String studentID;
    private String supervisorID;
    private String projectID;
    private String newTitle;

    public StudentChangeTitleRequest(String requestID, String studentID, String supervisorID, String projectID, String newTitle) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
        this.newTitle = newTitle;
    }

    public StudentChangeTitleRequest(Map<String, String> map) {
        fromMap(map);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
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

    public String getNewTitle() {
        return newTitle;
    }

    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Get the ID of the request.
     */
    @Override
    public String getID() {
        return this.requestID;
    }

    /**
     * Get the status of the request.
     *
     * @return the status of the request.
     */
    @Override
    public RequestStatus getStatus() {
        return this.requestStatus;
    }

    /**
     * Set the status of the request.
     *
     * @param status the status of the request.
     */
    @Override
    public void setStatus(RequestStatus status) {
        this.requestStatus = status;
    }

    /**
     * Get the type of the request.
     *
     * @return the type of the request.
     */
    @Override
    public RequestType getType() {
        return null;
    }
}
