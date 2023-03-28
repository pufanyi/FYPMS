package main.controller.account.user;

import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

public class UserUpdater {
    private static void updateStudent(Student student) throws ModelNotFoundException {
        StudentRepository.getInstance().update(student);
    }

    private static void updateSupervisor(Supervisor supervisor) throws ModelNotFoundException {
        FacultyRepository.getInstance().update(supervisor);
    }

    private static void updateCoordinator(Coordinator coordinator) throws ModelNotFoundException {
        CoordinatorRepository.getInstance().update(coordinator);
    }

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
