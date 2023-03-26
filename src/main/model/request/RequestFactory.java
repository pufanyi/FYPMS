package main.model.request;

import main.model.request.studentrequest.StudentChangeTitleRequest;
import main.model.request.studentrequest.StudentDeregistrationRequest;
import main.model.request.studentrequest.StudentRegistrationRequest;

import java.util.Map;

public class RequestFactory {
    public static Request createRequest(Map<String, String> map) {
        return switch (map.get("RequestType")) {
            case "STUDENT_REGISTRATION" -> new StudentRegistrationRequest(map);
            case "STUDENT_DEREGISRATION" -> new StudentDeregistrationRequest(map);
            case "STUDENT_CHANGE_TITLE" -> new StudentChangeTitleRequest(map);
        };
    }
}
