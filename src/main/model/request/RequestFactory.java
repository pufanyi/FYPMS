package main.model.request;

import main.model.request.studentrequest.StudentChangeTitleRequest;
import main.model.request.studentrequest.StudentDeregistrationRequest;
import main.model.request.studentrequest.StudentRegistrationRequest;

public class RequestFactory {
    public static Request createRequest(RequestType requestType) {
        return switch (requestType) {
            case STUDENT_REGISTRATION -> new StudentRegistrationRequest(map);
            case STUDENT_DEREGISTRATION -> new StudentDeregistrationRequest(map);
            case STUDENT_CHANGE_TITLE -> new StudentChangeTitleRequest(map);
        };
    }
}
