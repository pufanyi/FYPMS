package main.utils.exception;

/**
 Thrown when a supervisor has reached the maximum number of students they can supervise.

 */
public class SupervisorStudentsLimitExceedException extends Exception {
    /*
    Constructs a new SupervisorStudentsLimitExceedException with a default message.
    */
    public SupervisorStudentsLimitExceedException() {
        super("Supervisor has reached the maximum number of students");
    }
}
