package main.model.request.studentrequest;

import main.model.request.RequestType;

import java.util.HashMap;
import java.util.Map;

public class StudentChangeTitleRequest extends StudentRequest {
    private final RequestType requestType = RequestType.STUDENT_CHANGE_TITLE;
    private String studentID;
    private String supervisorID;
    private String projectID;
    private String newTitle;

    public StudentChangeTitleRequest(String studentID, String supervisorID, String projectID, String newTitle) {
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
        map.put("NewTitle", newTitle);
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
        this.newTitle = map.get("NewTitle");
    }
}
