package main.utils.exception.model;

import main.model.user.StudentStatus;

public class StudentStatusException extends IllegalStateException{
    StudentStatus status;
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
