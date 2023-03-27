package main.controller.account.user;

import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;

public class UserAdder {
    public static void addUser(User user) throws IllegalArgumentException {
        if (user instanceof Student student) {
            addStudent(student);
        } else if (user instanceof Supervisor supervisor) {
            addSupervisor(supervisor);
        } else if (user instanceof Coordinator coordinator) {
            addCoordinator(coordinator);
        }
    }

    private static void addCoordinator(Coordinator coordinator) throws IllegalArgumentException {
        CoordinatorRepository.getInstance().add(coordinator);
    }

    private static void addSupervisor(Supervisor supervisor) throws IllegalArgumentException {
        FacultyRepository.getInstance().add(supervisor);
    }

    private static void addStudent(Student student) throws IllegalArgumentException {
        StudentRepository.getInstance().add(student);
    }
}
