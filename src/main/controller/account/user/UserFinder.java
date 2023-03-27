package main.controller.account.user;

import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;

import java.util.NoSuchElementException;

public class UserFinder {
    private static User findStudent(String userID) throws NoSuchElementException {
        return StudentRepository.getInstance().getByID(userID);
    }

    private static User findFaculty(String userID) throws NoSuchElementException {
        return FacultyRepository.getInstance().getByID(userID);
    }

    private static User findCoordinator(String userID) throws NoSuchElementException {
        return FacultyRepository.getInstance().getByID(userID);
    }

    public static User findUser(String userID, UserType userType) throws NoSuchElementException {
        return switch (userType) {
            case STUDENT -> findStudent(userID);
            case FACULTY -> findFaculty(userID);
            case COORDINATOR -> findCoordinator(userID);
        };
    }
}
