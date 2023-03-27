package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.repository.project.ProjectRepository;

import java.util.List;

public class ProjectManager {
    public void changeProjectTitle(String projectID, String newTitle) {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    public List<Project> viewAvailableProjects() {
        return ProjectRepository.getInstance().findByRules(
                project -> project.getStatus() == ProjectStatus.AVAILABLE
        );
    }

    public void createProject(String projectID, String projectTitle, String supervisorID) {
        Project p1 = new Project(projectID, projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    public void transferToNewSupervisor(String projectID, String supervisorID) {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setSupervisorID(supervisorID);
        ProjectRepository.getInstance().update(p1);
    }

    public List<Project> viewAllProject() {
        return ProjectRepository.getInstance().getList();
    }

    public void deallocateProject(String projectID) {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID("");
        ProjectRepository.getInstance().update(p1);
    }

    public void allocateProject(String projectID, String studentID) {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID(studentID);
        ProjectRepository.getInstance().update(p1);
    }

}
