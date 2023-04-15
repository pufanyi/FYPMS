package main.model.request;

/**
 * This enum represents the type of the request
 */
public enum RequestType {
    /**
     * The request is a supervisor tranfer student request
     */
    SUPERVISOR_TRANSFER_STUDENT,
    /**
     * The request is a student registration request
     */
    STUDENT_REGISTRATION,
    /**
     * The request is a student deregistration request
     */
    STUDENT_DEREGISTRATION,
    /**
     * The request is a student change title request
     */
    STUDENT_CHANGE_TITLE,
}
