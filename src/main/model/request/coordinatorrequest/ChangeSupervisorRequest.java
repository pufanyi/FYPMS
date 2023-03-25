package main.model.request.coordinatorrequest;

import main.model.request.RequestChange;

import java.util.Map;

public class ChangeSupervisorRequest extends CoordinatorRequest implements RequestChange {
    private String supervisorID;

    public ChangeSupervisorRequest(String supervisorID) {
        super();
        this.supervisorID = supervisorID;
    }

    public ChangeSupervisorRequest(Map<String, Object> map) {
        this.supervisorID = (String) map.get("supervisorID");
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> map = super.toMap();
        map.put("supervisorID", supervisorID);
        return map;
    }

    @Override
    public void fromMap(Map<String, String> map) {
        this.supervisorID = map.get("supervisorID");
    }
}
