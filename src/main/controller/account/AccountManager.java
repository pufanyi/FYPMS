package main.controller.account;

import main.controller.account.password.PasswordManager;
import main.controller.account.user.UserAdder;
import main.controller.account.user.UserFinder;
import main.controller.account.user.UserUpdater;
import main.model.user.*;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.config.Location;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.iocontrol.CSVReader;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class AccountManager {
    public static User login(UserType userType, String userID, String password)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserFinder.findUser(userID, userType);
//        System.err.println("User found: " + user.getUserName() + " " + user.getID());
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
                student -> student.checkUsername(userName)
        );
        List<Coordinator> coordinatorList = CoordinatorRepository.getInstance().findByRules(
                coordinator -> coordinator.checkUsername(userName)
        );
        List<Supervisor> supervisorList = FacultyRepository.getInstance().findByRules(
                supervisor -> supervisor.checkUsername(userName)
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
//        if (userType == UserType.COORDINATOR) {
//            System.err.println("Registering coordinator...");
//            System.err.println("Coordinator ID: " + userID);
//            System.err.println("Coordinator name: " + name);
//            System.err.println("Coordinator email: " + email);
//        }
        return register(userType, userID, "password", name, email);
    }

    private static String getID(String email) {
        return email.split("@")[0];
    }

    private static void loadStudents() {
        List<List<String>> studentList = CSVReader.read(Location.RESOURCE_LOCATION + "/resources/StudentList.csv", true);
        for (List<String> row : studentList) {
            String name = row.get(0);
            String email = row.get(1);
            String userID = getID(email);
            try {
                register(UserType.STUDENT, userID, name, email);
            } catch (ModelAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadCoordinators() {
        List<List<String>> coordinatorList = CSVReader.read(Location.RESOURCE_LOCATION + "/resources/CoordinatorList.csv", true);
        for (List<String> row : coordinatorList) {
            String name = row.get(0);
            String email = row.get(1);
            String userID = getID(email);
            try {
                register(UserType.COORDINATOR, userID, name, email);
            } catch (ModelAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    private static void loadFaculties() {
        List<List<String>> facultyList = CSVReader.read(Location.RESOURCE_LOCATION + "/resources/FacultyList.csv", true);
        for (List<String> row : facultyList) {
            String name = row.get(0);
            String email = row.get(1);
            String userID = getID(email);
            try {
                register(UserType.FACULTY, userID, name, email);
            } catch (ModelAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadUsers() {
        loadStudents();
        loadCoordinators();
        loadFaculties();
    }

    public static boolean repositoryIsEmpty() {
        return StudentRepository.getInstance().isEmpty() &&
                CoordinatorRepository.getInstance().isEmpty() &&
                FacultyRepository.getInstance().isEmpty();
    }

    public static User getByDomainAndID(UserType userType, String ID) throws ModelNotFoundException {
        return switch (userType) {
            case STUDENT -> StudentRepository.getInstance().getByID(ID);
            case COORDINATOR -> CoordinatorRepository.getInstance().getByID(ID);
            case FACULTY -> FacultyRepository.getInstance().getByID(ID);
            default -> null;
        };
    }
}
