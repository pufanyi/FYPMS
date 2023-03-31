package test;

import main.Main;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;

/**
 * This class is used to clear the database and reload the main class.
 * This is used to test the whole application.
 */
public class HardReload {
    public static void main(String[] args) {
        StudentRepository.getInstance().clear();
        FacultyRepository.getInstance().clear();
        CoordinatorRepository.getInstance().clear();
        ProjectRepository.getInstance().clear();
        RequestRepository.getInstance().clear();
        Main.main(null);
    }
}
