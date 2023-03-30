package main.boundary.project;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.ui.ChangePage;

import java.util.List;

public class ViewAvailableProjectList {
    public static void viewAvailableProjectList() {
        ChangePage.changePage();
        System.out.println("View Available Project List");
        try {
            List<Project> projectList = ProjectManager.viewAvailableProjects();
            for (Project p : projectList) {
                p.displayProject();
            }
        } catch (ModelNotFoundException e) {
            System.out.println("No available projects.");
            throw new RuntimeException(e);
        }
    }
}
