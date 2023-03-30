/**
 * The `main.utils.exception.model` package contains classes related to exception handling.
 */
package main.utils.exception.model;

import main.model.user.StudentStatus;

/**
 * The `StudentStatusException` class is a custom exception that is thrown when a student's status prevents them from
 * performing an action related to selecting a project for their FYP.
 * It extends the `IllegalStateException` class.
 */
public class StudentStatusException extends IllegalStateException{

    /**
     * The `status` instance variable holds the `StudentStatus` of the student that caused the exception.
     */
    StudentStatus status;

    /**
     * Creates a new instance of the `StudentStatusException` class with a custom error message based on the student's status.
     * @param status The `StudentStatus` of the student that caused the exception.
     */
    public StudentStatusException(StudentStatus status) {
        this.status = status;
        if (status == StudentStatus.REGISTERED) {
            System.out.println("You are currently allocated to a FYP and do not have access to available projects");
        }
        else if (status == StudentStatus.DEREGISTERED) {
            System.out.println("You are not allowed to make selection again as you deregistered from your FYP");
        }
        else if(status == StudentStatus.UNREGISTERED) {
            System.out.println("You have not registered a project");
        }
    }
}
