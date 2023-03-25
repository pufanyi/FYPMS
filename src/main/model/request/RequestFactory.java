package main.model.request;

import main.model.request.coordinatorrequest.AllocateProject;
import main.model.request.studentrequest.ViewProjectListRequest;

import java.util.Map;

public class RequestFactory {
    public static Request createRequest(Map<String, String> map) {
        return switch (map.get("RequestType")) {
            case "VIEW_PROJECT_LIST" -> new ViewProjectListRequest(map);
            case "ALLOCATE_PROJECT" -> new AllocateProject(map);
            default -> null;
        };
    }
}
