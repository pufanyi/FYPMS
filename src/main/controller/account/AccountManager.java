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
import main.utils.exception.PasswordIncorrectException;
import main.utils.exception.ModelAlreadyExistsException;
import main.utils.exception.ModelNotFoundException;
import main.utils.iocontrol.CSVReader;

import java.util.ArrayList;
import java.util.List;

/**
 * A class manages the account of a user
 */
public class AccountManager {
    /**
     * Adds a new user to the database
     *
     * @param userType the type of the user to be added
     * @param userID   the ID of the user to be added
     * @param password the password of the user to be added
     * @return the user that is added
     * @throws PasswordIncorrectException if the password is incorrect
     * @throws ModelNotFoundException     if the user is not found
     */
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

    /**
     * Changes the password of the user
     *
     * @param userType    the type of the user to be changed
     * @param userID      the ID of the user to be changed
     * @param oldPassword the old password of the user to be changed
     * @param newPassword the new password of the user to be changed
     * @throws PasswordIncorrectException if the old password is incorrect
     * @throws ModelNotFoundException     if the user is not found
     */
    public static void changePassword(UserType userType, String userID, String oldPassword, String newPassword)
            throws PasswordIncorrectException, ModelNotFoundException {
        User user = UserFinder.findUser(userID, userType);
        PasswordManager.changePassword(user, oldPassword, newPassword);
        UserUpdater.updateUser(user);
    }

    /**
     * Gets the user by the user name
     *
     * @param userName the user name of the user
     * @return the user with the user name
     */
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

    /**
     * Registers a new user
     *
     * @param userType the type of the user to be registered
     * @param userID   the ID of the user to be registered
     * @param password the password of the user to be registered
     * @param name     the name of the user to be registered
     * @param email    the email of the user to be registered
     * @return the user that is registered
     * @throws ModelAlreadyExistsException if the user already exists
     */
    public static User register(UserType userType, String userID, String password, String name, String email)
            throws ModelAlreadyExistsException {
        User user = UserFactory.create(userType, userID, password, name, email);
        UserAdder.addUser(user);
        return user;
    }

    /**
     * Registers a new user
     *
     * @param userType the type of the user to be registered
     * @param userID   the ID of the user to be registered
     * @param name     the name of the user to be registered
     * @param email    the email of the user to be registered
     * @return the user that is registered
     * @throws ModelAlreadyExistsException if the user already exists
     */
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

    /**
     * Loads the students and coordinators from the CSV file
     *
     * @param email the email of the user
     * @return the ID of the user
     */
    private static String getID(String email) {
        return email.split("@")[0];
    }

    /**
     * Loads the students from the CSV resource file
     */
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

    /**
     * Loads the coordinators from the CSV resource file
     */
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

    /**
     * Loads the faculties from the CSV resource file
     */
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

    /**
     * Loads all users from the CSV resource file
     */
    public static void loadUsers() {
        loadStudents();
        loadCoordinators();
        loadFaculties();
    }

    /**
     * Checks if the repository is empty
     *
     * @return true if the repository is empty, false otherwise
     */
    public static boolean repositoryIsEmpty() {
        return StudentRepository.getInstance().isEmpty() &&
                CoordinatorRepository.getInstance().isEmpty() &&
                FacultyRepository.getInstance().isEmpty();
    }

    /**
     * Gets the user by the domain and ID
     *
     * @param userType the type of the user
     * @param ID       the ID of the user
     * @return the user with the domain and ID
     * @throws ModelNotFoundException if the user is not found
     */
    public static User getByDomainAndID(UserType userType, String ID) throws ModelNotFoundException {
        return switch (userType) {
            case STUDENT -> StudentRepository.getInstance().getByID(ID);
            case COORDINATOR -> CoordinatorRepository.getInstance().getByID(ID);
            case FACULTY -> FacultyRepository.getInstance().getByID(ID);
        };
    }
}
