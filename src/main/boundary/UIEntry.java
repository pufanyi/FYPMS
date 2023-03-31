package main.boundary;

import main.boundary.welcome.Welcome;
import main.controller.account.AccountManager;
import main.controller.project.ProjectManager;

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
