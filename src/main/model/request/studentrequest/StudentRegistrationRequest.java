package main.model.request.studentrequest;

import main.model.request.Request;
import main.model.request.RequestType;

import java.util.Map;

public class StudentRegistrationRequest {
    private final RequestType requestType = RequestType.STUDENT_REGISTRATION;
    private String studentID;
    private String supervisorID;
    private String projectID;

    public StudentRegistrationRequest(String studentID, String supervisorID, String projectID) {
        this.studentID = studentID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
    }

    public StudentRegistrationRequest(Map<String, String> map) {
        this.studentID = map.get("StudentID");
        this.supervisorID = map.get("SupervisorID");
        this.projectID = map.get("ProjectID");
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

    public RequestType getRequestType() {
        return requestType;
    }
}
