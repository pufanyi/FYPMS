package main.controller.account.user;

import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelNotFoundException;

/**
 * The UserUpdater class provides a utility for updating users in the database.
 */
public class UserUpdater {
    /**
     * Updates the specified user in the database.
     *
     * @param student the user to be updated
     * @throws ModelNotFoundException if the user is not found in the database
     */
    private static void updateStudent(Student student) throws ModelNotFoundException {
        StudentRepository.getInstance().update(student);
    }

    /**
     * Updates the specified supervisor in the database.
     *
     * @param supervisor the supervisor to be updated
     * @throws ModelNotFoundException if the supervisor is not found in the database
     */
    private static void updateSupervisor(Supervisor supervisor) throws ModelNotFoundException {
        FacultyRepository.getInstance().update(supervisor);
    }

    /**
     * Updates the specified coordinator in the database.
     *
     * @param coordinator the coordinator to be updated
     * @throws ModelNotFoundException if the coordinator is not found in the database
     */
    private static void updateCoordinator(Coordinator coordinator) throws ModelNotFoundException {
        CoordinatorRepository.getInstance().update(coordinator);
    }

    /**
     * Updates the specified user in the database.
     *
     * @param user the user to be updated
     * @throws ModelNotFoundException if the user is not found in the database
     */
    public static void updateUser(User user) throws ModelNotFoundException {
        if (user instanceof Student student) {
            updateStudent(student);
        } else if (user instanceof Supervisor supervisor) {
            updateSupervisor(supervisor);
        } else if (user instanceof Coordinator coordinator) {
            updateCoordinator(coordinator);
        }
    }
}
