package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.ChangeRequest;
import main.model.request.RequestType;
import main.model.user.Student;
import main.model.user.StudentStatus;

import java.util.Map;

public class AllocateProject extends CoordinatorRequest implements ChangeRequest {
    private String requestID;
    private String studentID;
    private String projectID;
    private String supervisorID;
    private final RequestType requestType = RequestType.COORDINATOR_ALLOCATE_PROJECT;
    private StudentStatus studentStatus;
    private ProjectStatus projectStatus;


    public AllocateProject(String requestID, String studentID, String projectID, String supervisorID, StudentStatus studentStatus) {
        super(requestID);
        this.studentID = studentID;
        this.projectID = projectID;
        this.supervisorID = supervisorID;
    }

    public AllocateProject(Map<String, String> map) {
        fromMap(map);
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    public StudentStatus getStudentStatus() {
        return studentStatus;
    }

    public void setStudentStatus(StudentStatus studentStatus) {
        this.studentStatus = studentStatus;
    }

    public ProjectStatus getProjectStatus() {
        return projectStatus;
    }

    public void setProjectStatus(ProjectStatus projectStatus) {
        this.projectStatus = projectStatus;
    }

    @Override
    public String getID() {
        return requestID;
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
