package main.model.request;

import main.model.request.coordinatorrequest.AllocateProject;

import java.util.Map;

public class RequestFactory {
    public static Request createRequest(Map<String, String> map) {
        return switch (map.get("RequestType")) {
            case "ALLOCATE_PROJECT" -> new AllocateProject(map);
            default -> null;
        };
    }
}
