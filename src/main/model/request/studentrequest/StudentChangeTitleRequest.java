package main.model.request.studentrequest;

import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.Map;

public class StudentChangeTitleRequest implements StudentRequest {
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.STUDENT_CHANGE_TITLE;
    /**
     * The status of the request
     */
    private RequestStatus requestStatus = RequestStatus.PENDING;
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
     * The new title of the project
     */
    private String newTitle;
    /**
     * The ID of the coordinator who deals with the request
     */
    private String coordinatorID = null;

    /**
     * Constructor
     * @param requestID The ID of the request
     * @param studentID The ID of the student
     * @param supervisorID The ID of the supervisor
     * @param projectID The ID of the project
     * @param newTitle The new title of the project
     */
    public StudentChangeTitleRequest(String requestID, String studentID, String supervisorID, String projectID, String newTitle) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
        this.newTitle = newTitle;
    }

    /**
     * Constructor
     * @param map The map of the request
     */
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

    public String getCoordinatorID() {
        return coordinatorID;
    }

    /**
     * Set the ID of the coordinator who deals with the request
     * @param coordinatorID The ID of the coordinator
     */
    public void setCoordinatorID(String coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    /**
     * Get the type of the request.
     * @return the type of the request.
     */
    @Override
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
     * display the information of the request.
     */
    @Override
    public void display(){
        System.out.println("Request ID: " + requestID);
        System.out.println("Request Type: " + requestType);
        System.out.println("Request Status: " + requestStatus);
    }
}
