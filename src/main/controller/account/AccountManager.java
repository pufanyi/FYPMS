package main.controller.account;

import main.controller.account.password.PasswordManager;
import main.controller.account.user.UserAdder;
import main.controller.account.user.UserFinder;
import main.controller.account.user.UserUpdater;
import main.model.user.*;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

import java.util.ArrayList;
import java.util.List;

public class AccountManager {
    public static User login(UserType userType, String userID, String password)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserFinder.findUser(userID, userType);
        if (PasswordManager.checkPassword(user, password)) {
            return user;
        } else {
            throw new PasswordIncorrectException();
        }
    }

    public static void changePassword(UserType userType, String userID, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserFinder.findUser(userID, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserUpdater.updateUser(user);
    }

    public static List<User> getUsersByUserName(String userName) {
        List<Student> studentList = StudentRepository.getInstance().findByRules(
                student -> student.getUserName().equals(userName)
        );
        List<Coordinator> coordinatorList = CoordinatorRepository.getInstance().findByRules(
                coordinator -> coordinator.getUserName().equals(userName)
        );
        List<Supervisor> supervisorList = FacultyRepository.getInstance().findByRules(
                supervisor -> supervisor.getUserName().equals(userName)
        );
        List<User> userList = new ArrayList<>();
        userList.addAll(studentList);
        userList.addAll(coordinatorList);
        userList.addAll(supervisorList);
        return userList;
    }

    public static User register(UserType userType, String userID, String password, String name, String email)
            throws ModelAlreadyExistsException {
        User user = UserFactory.create(userType, userID, password, name, email);
        UserAdder.addUser(user);
        return user;
    }

    public static User register(UserType userType, String userID, String name, String email)
            throws ModelAlreadyExistsException {
        return register(userType, userID, "password", name, email);
    }
}
