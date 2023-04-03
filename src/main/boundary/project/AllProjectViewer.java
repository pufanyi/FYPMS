package main.boundary.project;

import main.model.project.Project;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelNotFoundException;

public class AllProjectViewer {
    public static void viewAllProject() {
        try {
            for (Project p : ProjectRepository.getInstance()) {
                p.displayProject();
            }
        } catch (ModelNotFoundException e) {
            System.out.println("No available projects.");
            throw new RuntimeException(e);
        }
    }
}
