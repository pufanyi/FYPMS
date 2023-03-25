package main.model.request.coordinatorrequest;

import main.model.request.RequestChange;

import java.util.Map;

public class ChangeSupervisorRequest extends CoordinatorRequest implements RequestChange {
    @Override
    public Map<String, String> toMap() {
        return null;
    }

    @Override
    public void fromMap(Map<String, String> map) {

    }
}
