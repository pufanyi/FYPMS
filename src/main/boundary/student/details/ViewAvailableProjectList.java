package main.boundary.student.details;

import main.controller.project.ProjectManager;
import main.controller.request.StudentRequestManager;
import main.model.user.Student;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.ui.ChangePage;

public class ViewAvailableProjectList {
    public static void viewAvailableProjectList() {
        ChangePage.changePage();
        System.out.println("View Available Project List");
        try {
            ProjectManager.viewAvailableProjects();
        } catch (ModelNotFoundException e) {
            System.out.println("No available projects.");
            throw new RuntimeException(e);
        }
    }
}
