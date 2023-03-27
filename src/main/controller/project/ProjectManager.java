package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

import java.util.List;

public class ProjectManager {
    public static void changeProjectTitle(String projectID, String newTitle) throws ModelNotFoundException, ModelAlreadyExistsException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    public static List<Project> viewAvailableProjects() {
        return ProjectRepository.getInstance().findByRules(
                project -> project.getStatus() == ProjectStatus.AVAILABLE
        );
    }

    public static void createProject(String projectID, String projectTitle, String supervisorID) throws ModelAlreadyExistsException {
        Project p1 = new Project(projectID, projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    public static void transferToNewSupervisor(String projectID, String supervisorID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setSupervisorID(supervisorID);
        ProjectRepository.getInstance().update(p1);
    }

    public static List<Project> viewAllProject() {
        return ProjectRepository.getInstance().getList();
    }

    public static void deallocateProject(String projectID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID("");
        ProjectRepository.getInstance().update(p1);
    }

    public static void allocateProject(String projectID, String studentID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID(studentID);
        ProjectRepository.getInstance().update(p1);
    }

}
