package test.boundary.student.details;

import main.boundary.modelviewer.ProjectViewer;
import main.controller.project.ProjectManager;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.ui.PageBackException;

public class ViewAvailableProjectListTest {
    public static void main(String[] args) throws ModelAlreadyExistsException, PageBackException {
        ProjectRepository.getInstance().clear();
        ProjectManager.createProject("1", "Haha", "Haha");
        ProjectViewer.viewAvailableProjectList();
    }
}
