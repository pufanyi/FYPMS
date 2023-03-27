package main.controller.account.user;

import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelNotFoundException;

import java.util.NoSuchElementException;

public class UserFinder {
    private static User findStudent(String userID) throws ModelNotFoundException {
        return StudentRepository.getInstance().getByID(userID);
    }

    private static User findFaculty(String userID) throws NoSuchElementException, ModelNotFoundException {
        return FacultyRepository.getInstance().getByID(userID);
    }

    private static User findCoordinator(String userID) throws NoSuchElementException, ModelNotFoundException {
        return CoordinatorRepository.getInstance().getByID(userID);
    }

    public static User findUser(String userID, UserType userType) throws ModelNotFoundException {
        return switch (userType) {
            case STUDENT -> findStudent(userID);
            case FACULTY -> findFaculty(userID);
            case COORDINATOR -> findCoordinator(userID);
        };
    }
}
