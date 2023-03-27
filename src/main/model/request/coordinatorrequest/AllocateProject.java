package main.model.request.coordinatorrequest;

import main.model.request.ChangeRequest;
import main.model.request.RequestStatus;
import main.model.request.RequestType;

import java.util.Map;

public class AllocateProject extends CoordinatorRequest implements ChangeRequest {
    /**
     * The ID of the request
     */
    private String requestID;
    /**
     * The ID of the student to be allocated
     */
    private String studentID;
    /**
     * The ID of the project to be allocated
     */
    private String projectID;
    /**
     * The ID of the supervisor of the project
     */
    private String supervisorID;
    /**
     * The type of the request
     */
    private final RequestType requestType = RequestType.COORDINATOR_ALLOCATE_PROJECT;
    /**
     * The status of the request
     */
    private RequestStatus status = RequestStatus.PENDING;

    /**
     * Constructs a new AllocateProject object with the specified request.
     * @param requestID the ID of the request
     * @param studentID the ID of the student to be allocated
     * @param projectID the ID of the project to be allocated
     * @param supervisorID the ID of the supervisor of the project
     */
    public AllocateProject(String requestID, String studentID, String projectID, String supervisorID) {
        super(requestID);
        this.requestID = requestID;
        this.studentID = studentID;
        this.projectID = projectID;
        this.supervisorID = supervisorID;
    }

    /**
     * Constructs a new AllocateProject object with the specified request.
     * @param map the map of the request
     */
    public AllocateProject(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Get the ID of the student to be allocated
     * @return the ID of the student to be allocated
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the ID of the student to be allocated
     * @param studentID the ID of the student to be allocated
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the ID of the project to be allocated
     * @return the ID of the project to be allocated
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Set the ID of the project to be allocated
     * @param projectID the ID of the project to be allocated
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * Get the ID of the supervisor of the project
     * @return the ID of the supervisor of the project
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the ID of the supervisor of the project
     * @param supervisorID the ID of the supervisor of the project
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
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
     * @param status the status of the request.
     */
    @Override
    public void setStatus(RequestStatus status) {
        this.status = status;
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
     * Get the type of the request
     * @return the type of the request
     */
    @Override
    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public void view(){
        return;
    }

//    /**
//     *  Allocate the project to the student
//     * @throws IllegalStateException if the student is already registered or the project is already allocated
//     */
//    public void allocateProject() throws IllegalStateException {
//        if(student.getStatus() == StudentStatus.REGISTERED)
//            throw new IllegalStateException("Student is already registered");
//        if(project.getStatus() == ProjectStatus.ALLOCATED)
//            throw new IllegalStateException("Project is already allocated");
//        student.setStatus(StudentStatus.REGISTERED);
//        project.setStatus(ProjectStatus.ALLOCATED);
//        project.setStudentID(student.getID());
//    }
}
