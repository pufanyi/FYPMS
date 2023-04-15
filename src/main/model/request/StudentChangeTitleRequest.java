package main.model.request;

import java.util.Map;

/**
 * This class represents a student request to change the title of a project
 */
public class StudentChangeTitleRequest implements Request {
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.STUDENT_CHANGE_TITLE;
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The status of the request
     */
    private RequestStatus requestStatus = RequestStatus.PENDING;
    /**
     * The ID of the student
     */
    private String studentID;
    /**
     * The ID of the supervisor who deals with the request
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
     * Constructor
     *
     * @param requestID    The ID of the request
     * @param studentID    The ID of the student
     * @param supervisorID The ID of the supervisor
     * @param projectID    The ID of the project
     * @param newTitle     The new title of the project
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
     *
     * @param map The map of the request
     */
    public StudentChangeTitleRequest(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Get the id of the student
     * @return the id of the student
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the id of the student
     * @param studentID the id of the student
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the id of the supervisor
     * @return the id of the supervisor
     */
    @Override
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the id of the supervisor
     * @param supervisorID the id of the supervisor
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Get the id of the project
     * @return the id of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Set the id of the project
     * @param projectID the id of the project
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * Get the new title of the project
     * @return the new title of the project
     */
    public String getNewTitle() {
        return newTitle;
    }

    /**
     * Set the new title of the project
     * @param newTitle the new title of the project
     */
    public void setNewTitle(String newTitle) {
        this.newTitle = newTitle;
    }

    /**
     * Get the type of the request.
     *
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
     Returns a string representation of the object suitable for display purposes.
     The string includes the request ID, request type, request status (with colorful formatting),
     project ID, supervisor ID, student ID, and new title.
     @return a formatted string representing the object for display purposes.
     */
    @Override
    public String getDisplayableString() {
        return String.format("| %-18s | %-27s |\n", "Request ID", requestID) +
                String.format("| %-18s | %-27s |\n", "Request Type", requestType) +
                String.format("| %-18s | %-36s |\n", "Request Status", requestStatus.showColorfulStatus()) +
                String.format("| %-18s | %-27s |\n", "Project ID", projectID) +
                String.format("| %-18s | %-27s |\n", "Supervisor ID", supervisorID) +
                String.format("| %-18s | %-27s |\n", "Student ID", studentID) +
                String.format("| %-18s | %-27s |\n", "New Title", newTitle);
    }
}
