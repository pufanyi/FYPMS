package main.model.request.studentrequest;

import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.HashMap;
import java.util.Map;

public class StudentDeregistrationRequest implements StudentRequest {
    public String requestID;
    private final RequestType requestType = RequestType.STUDENT_DEREGISTRATION;
    private RequestStatus requestStatus = RequestStatus.PENDING;
    private String studentID;
    private String supervisorID;
    private String projectID;

    public StudentDeregistrationRequest(String requestID, String studentID, String supervisorID, String projectID) {
        this.studentID = studentID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
    }

    public StudentDeregistrationRequest(Map<String, String> map) {
        fromMap(map);
    }

    @Override
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

    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Get the ID of the request.
     */
    @Override
    public String getID() {
        return requestID;
    }

    /**
     * Get the status of the request.
     *
     * @return the status of the request.
     */
    @Override
    public RequestStatus getStatus() {
        return requestStatus;
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

    @Override
    public void display() {
        System.out.println("Request ID: " + requestID);
        System.out.println("Request Type: " + requestType);
        System.out.println("Request Status: " + requestStatus);
    }
}
