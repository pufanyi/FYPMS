package main.model.request;

import java.util.Map;

/**

 A factory class that creates a specific type of Request based on the input parameters.
 */
public class RequestFactory {
    /**
     * Creates a Request object based on the input parameters.
     *
     * @param map a Map of key-value pairs representing the parameters of the Request
     * @return a Request object of the specified type
     * @throws IllegalArgumentException if the RequestType is not valid or if the input parameters are not valid for the specified RequestType
     */
    public static Request createRequest(Map<String, String> map) {
        return switch (RequestType.valueOf(map.get("requestType"))) {
            case SUPERVISOR_TRANSFER_STUDENT -> new TransferStudentRequest(map);
            case STUDENT_REGISTRATION -> new StudentRegistrationRequest(map);
            case STUDENT_DEREGISTRATION -> new StudentDeregistrationRequest(map);
            case STUDENT_CHANGE_TITLE -> new StudentChangeTitleRequest(map);
        };
    }
}
