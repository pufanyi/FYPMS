package main.model.request;

import main.utils.parameters.EmptyID;

import java.util.Map;

public class TransferStudentRequest implements Request {
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.SUPERVISOR_TRANSFER_STUDENT;
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The status of the request
     */
    private RequestStatus requestStatus = RequestStatus.PENDING;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The ID of the supervisor
     */
    private String supervisorID;
    /**
     * The ID of the new supervisor
     */
    private String newSupervisorID;
    /**
     * The ID of the student
     */
    private String studentID;
    /**
     * The ID of the coordinator who deals with the request
     */
    private String coordinatorID = null;

    /**
     * Constructor
     *
     * @param requestID       The ID of the request
     * @param projectID       The ID of the project
     * @param supervisorID    The ID of the supervisor
     * @param newSupervisorID The ID of the new supervisor
     * @param studentID       The ID of the student
     */
    public TransferStudentRequest(String requestID, String projectID, String supervisorID, String newSupervisorID, String studentID) {
        this.requestID = requestID;
        this.projectID = projectID;
        this.supervisorID = supervisorID;
        this.newSupervisorID = newSupervisorID;
        this.studentID = studentID;
    }

    /**
     * Constructor
     *
     * @param map The map of the request
     */
    public TransferStudentRequest(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Get the ID of the project
     *
     * @return The ID of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Get the ID of the supervisor
     *
     * @return The ID of the supervisor
     */
    @Override
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the ID of the supervisor
     *
     * @param supervisorID The ID of the supervisor to be set
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Get the ID of the new supervisor
     *
     * @return The ID of the new supervisor
     */
    public String getNewSupervisorID() {
        return newSupervisorID;
    }

    /**
     * Get the ID of the student
     *
     * @return The ID of the student
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Get the ID of the coordinator
     *
     * @return The ID of the coordinator
     */
    public String getCoordinatorID() {
        return coordinatorID;
    }

    /**
     * Set the ID of the coordinator
     *
     * @param coordinatorID The ID of the coordinator
     */
    public void setCoordinatorID(String coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    /**
     * Get the ID of the request
     *
     * @return The ID of the request
     */
    @Override
    public String getID() {
        return requestID;
    }

    /**
     * Get the status of the request
     *
     * @return The status of the request
     */
    @Override
    public RequestStatus getStatus() {
        return requestStatus;
    }

    /**
     * Set the status of the request
     *
     * @param status the status of the request.
     */
    @Override
    public void setStatus(RequestStatus status) {
        this.requestStatus = status;
    }

    /**
     * Get the type of the request
     *
     * @return The type of the request
     */
    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public String getDisplayableString() {
        return String.format("| %-18s | %-27s |\n", "Request ID", requestID) +
                String.format("| %-18s | %-27s |\n", "Request Type", requestType) +
                String.format("| %-18s | %-27s |\n", "Request Status", requestStatus) +
                String.format("| %-18s | %-27s |\n", "Project ID", projectID) +
                String.format("| %-18s | %-27s |\n", "Supervisor ID", supervisorID) +
                String.format("| %-18s | %-27s |\n", "New Supervisor ID", newSupervisorID) +
                String.format("| %-18s | %-27s |\n", "Student ID", studentID);
//                String.format("| %-18s | %-27s |\n", "Coordinator ID", EmptyID.isEmptyID(coordinatorID) ? "N/A" : coordinatorID);
    }
}
