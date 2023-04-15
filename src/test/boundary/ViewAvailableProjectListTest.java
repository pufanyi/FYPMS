package test.boundary;

import main.boundary.modelviewer.ProjectViewer;
import main.controller.project.ProjectManager;
import main.model.user.Student;
import main.repository.project.ProjectRepository;
import main.utils.exception.ModelAlreadyExistsException;
import main.utils.exception.PageBackException;

/**

 This class tests the {@link ProjectViewer#viewAvailableProjectList(Student)} method
 by creating a new project, registering a student, and displaying the list of available projects
 for the student to choose from.
 */
public class ViewAvailableProjectListTest {
    public static void main(String[] args) throws ModelAlreadyExistsException, PageBackException {
        ProjectRepository.getInstance().clear();
        ProjectManager.createProject("1", "Haha", "Haha");
        Student student = new Student("FPU001", "Pu Fanyi", "haha@email.com");
        ProjectViewer.viewAvailableProjectList(student);
    }
}
