package main.model.request.studentrequest;

import main.model.request.RequestType;

import java.util.HashMap;
import java.util.Map;

public class StudentDeregistrationRequest extends StudentRequest {
    private final RequestType requestType = RequestType.STUDENT_DEREGISTRATION;
    private String studentID;
    private String supervisorID;
    private String projectID;

    public StudentDeregistrationRequest(String studentID, String supervisorID, String projectID) {
        this.studentID = studentID;
        this.supervisorID = supervisorID;
        this.projectID = projectID;
    }

    public StudentDeregistrationRequest(Map<String, String> map) {
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

    public RequestType getRequestType() {
        return requestType;
    }

    /**
     * Converts the object to a map
     *
     * @return the map
     */
    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        map.put("StudentID", studentID);
        map.put("SupervisorID", supervisorID);
        map.put("ProjectID", projectID);
        map.put("RequestType", requestType.toString());
        return map;
    }

    /**
     * Converts the map to an object
     *
     * @param map the map
     */
    @Override
    public void fromMap(Map<String, String> map) {
        this.studentID = map.get("StudentID");
        this.supervisorID = map.get("SupervisorID");
        this.projectID = map.get("ProjectID");
    }
}
