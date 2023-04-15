package main.model.user;

/**
 * This enum represents the status of a student
 */
public enum StudentStatus {
    /**
     * the student has submitted a  request that is pending to be processed
     */
    PENDING,
    /**
     * the student has deregistered a project
     */
    DEREGISTERED,
    /**
     * the student has registered a project
     */
    REGISTERED,
    /**
     * the student never register for any project
     */
    UNREGISTERED
}
