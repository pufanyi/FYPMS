package main.controller.account.user;

import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.ModelAlreadyExistsException;

/**
 * The UserAdder class provides a utility for adding users to the database.
 */
public class UserAdder {
    /**
     * Adds the specified user to the database.
     *
     * @param user the user to be added
     * @throws ModelAlreadyExistsException if the user already exists in the database
     */
    public static void addUser(User user) throws ModelAlreadyExistsException {
        if (user instanceof Student student) {
            addStudent(student);
        } else if (user instanceof Supervisor supervisor) {
            addSupervisor(supervisor);
        } else if (user instanceof Coordinator coordinator) {
            addCoordinator(coordinator);
        }
    }

    /**
     * Adds the specified coordinator to the database.
     *
     * @param coordinator the coordinator to be added
     * @throws ModelAlreadyExistsException if the coordinator already exists in the database
     */
    private static void addCoordinator(Coordinator coordinator) throws ModelAlreadyExistsException {
        CoordinatorRepository.getInstance().add(coordinator);
    }

    /**
     * Adds the specified supervisor to the database.
     *
     * @param supervisor the supervisor to be added
     * @throws ModelAlreadyExistsException if the supervisor already exists in the database
     */
    private static void addSupervisor(Supervisor supervisor) throws ModelAlreadyExistsException {
        FacultyRepository.getInstance().add(supervisor);
    }

    /**
     * Adds the specified student to the database.
     *
     * @param student the student to be added
     * @throws ModelAlreadyExistsException if the student already exists in the database
     */
    private static void addStudent(Student student) throws ModelAlreadyExistsException {
        StudentRepository.getInstance().add(student);
    }
}
