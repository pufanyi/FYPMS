package main.boundary.project;

import main.model.project.Project;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelNotFoundException;

public class AllProjectViewer {
    public static void viewAllProject() {
        for (Project p : ProjectRepository.getInstance()) {
            p.displayProject();
        }
    }
}
