package main.model.request;

import java.util.Map;

public class RequestFactory {
    public static Request createRequest(Map<String, String> map) {
        return switch (RequestType.valueOf(map.get("requestType"))) {
            case SUPERVISOR_TRANSFER_STUDENT -> new TransferStudentRequest(map);
            case STUDENT_REGISTRATION -> new StudentRegistrationRequest(map);
            case STUDENT_DEREGISTRATION -> new StudentDeregistrationRequest(map);
            case STUDENT_CHANGE_TITLE -> new StudentChangeTitleRequest(map);
        };
    }
}
