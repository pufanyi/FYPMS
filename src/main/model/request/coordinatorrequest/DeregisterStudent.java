package main.model.request.coordinatorrequest;

import main.model.project.ProjectStatus;
import main.model.request.ChangeRequest;
import main.model.request.RequestStatus;
import main.model.request.RequestType;
import main.model.user.StudentStatus;

import java.util.Map;

public class DeregisterStudent extends CoordinatorRequest implements ChangeRequest {
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.COORDINATOR_DEREGISTER_STUDENT;
    /**
     * The ID of the student
     */
    private RequestStatus status = RequestStatus.PENDING;
    /**
     * The ID of the student
     */
    private String studentID;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The ID of the supervisor
     */
    private String supervisorID;
    /**
     * The status of the student
     */
    private StudentStatus studentStatus;
    /**
     * The status of the project
     */
    private ProjectStatus projectStatus;

    /**
     * Constructor for the class
     * @param requestID The ID of the request
     * @param studentID The ID of the student
     * @param projectID The ID of the project
     * @param supervisorID The ID of the supervisor
     */
    public DeregisterStudent(String requestID, String studentID, String projectID, String supervisorID) {
        this.requestID = requestID;
        this.studentID = studentID;
        this.projectID = projectID;
        this.supervisorID = supervisorID;
    }

    /**
     * Constructor for the class
     * @param map The map containing the attributes of the class
     */
    public DeregisterStudent(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Get the ID of the student
     * @return The ID of the student
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the ID of the student
     * @param studentID The ID of the student
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the ID of the project
     * @return The ID of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Set the ID of the project
     * @param projectID The ID of the project
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * Get the ID of the supervisor
     * @return The ID of the supervisor
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the ID of the supervisor
     * @param supervisorID The ID of the supervisor
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Get the status of the student
     * @return The status of the student
     */
    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    /**
     * Set the status of the student
     * @param studentStatus The status of the student
     */
    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    /**
     * Get the status of the project
     * @return The status of the project
     */
    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    /**
     * Set the status of the project
     * @param projectStatus The status of the project
     */
    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    /**
     * Get the ID of the request
     * @return The ID of the request
     */
    @Override
    public String getID() {
        return requestID;
    }

    /**
     * Get the type of the request
     * @return The type of the request
     */
    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Get the status of the request
     * @return The status of the request
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
//     * Deregister the student and update the status of the student and the project
//     * @throws IllegalStateException if the student is not registered or the project is not allocated
//     */
//    public void deregisterStudent() throws IllegalStateException {
//        if(studentStatus != StudentStatus.REGISTERED)
//            throw new IllegalStateException("Student is not registered");
//        if(projectStatus != ProjectStatus.ALLOCATED)
//            throw new IllegalStateException("Project is not allocated");
//        student.setStatus(StudentStatus.DEREGISTERED);
//        project.setStatus(ProjectStatus.AVAILABLE);
//        project.setStudentID(null);
//    }
}
