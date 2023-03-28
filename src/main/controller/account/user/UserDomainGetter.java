package main.controller.account.user;

import main.model.user.*;

public class UserDomainGetter {
    public static UserType getUserDomain(User user) {
        if (user instanceof Student) {
            return UserType.STUDENT;
        } else if (user instanceof Coordinator) {
            return UserType.COORDINATOR;
        } else if (user instanceof Supervisor) {
            return UserType.FACULTY;
        } else {
            throw new RuntimeException("No such domain");
        }
    }
}
