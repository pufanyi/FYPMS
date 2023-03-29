package test;

import main.Main;
import main.boundary.UIEntry;
import main.boundary.welecome.Welcome;
import main.controller.account.AccountManager;
import main.controller.project.ProjectManager;
import main.model.request.Request;
import main.model.user.Coordinator;
import main.model.user.Student;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;

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
