package main.utils.exception;

public class SupervisorStudentsLimitExceedException extends Exception {
    public SupervisorStudentsLimitExceedException() {
        super("Supervisor has reached the maximum number of students");
    }
}
