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
        super(map);
        this.supervisorID = (String) map.get("supervisorID");
    }

    public String getSupervisorID() {
        return supervisorID;
    }

    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    @Override
    public Map<String, Object> toMap() {
        Map<String, Object> map = super.toMap();
        map.put("supervisorID", supervisorID);
        return map;
    }
}
