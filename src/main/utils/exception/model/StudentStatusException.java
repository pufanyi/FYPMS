package main.utils.exception.model;

import main.model.user.StudentStatus;

/**
 * The {@link StudentStatusException} class is a custom exception that is thrown when a student's status prevents them from
 * performing an action related to selecting a project for their FYP.
 * It extends the {@link IllegalStateException} class.
 */
public class StudentStatusException extends IllegalStateException {

    /**
     * The {@link status} instance variable holds the {@link StudentStatus} of the student that caused the exception.
     */
    StudentStatus status;

    /**
     * Creates a new instance of the {@link StudentStatusException} class with a custom error message based on the student's status.
     *
     * @param status The {@link StudentStatus} of the student that caused the exception.
     */
    public StudentStatusException(StudentStatus status) {
        this.status = status;
        if (status == StudentStatus.REGISTERED) {
            System.out.println("You are currently allocated to a FYP and do not have access to available projects");
        } else if (status == StudentStatus.DEREGISTERED) {
            System.out.println("You are not allowed to make selection again as you deregistered from your FYP");
        } else if (status == StudentStatus.UNREGISTERED) {
            System.out.println("You have not registered a project");
        }
    }
}
