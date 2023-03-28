package test.ui.student.details;

import main.boundary.student.details.ViewAvailableProjectList;
import main.controller.project.ProjectManager;
import main.utils.exception.repository.ModelAlreadyExistsException;

public class ViewAvailableProjectListTest {
    public static void main(String[] args) throws ModelAlreadyExistsException {
        ProjectManager.createProject("1", "Haha", "Haha");
        ViewAvailableProjectList.viewAvailableProjectList();
    }
}
