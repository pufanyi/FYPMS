package main.boundary;

import main.boundary.welecome.Welcome;
import main.controller.account.AccountManager;
import main.controller.project.ProjectManager;
import main.model.user.Student;
import main.repository.user.StudentRepository;

public class UIEntry {
    private static boolean firstStart() {
        return AccountManager.repositoryIsEmpty() && ProjectManager.repositoryIsEmpty();
    }

    public static void start() {
        if (firstStart()) {
            AccountManager.loadUsers();
            ProjectManager.loadProjects();
        }
        Welcome.welcome();
    }
}
