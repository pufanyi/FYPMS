package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

public class ProjectManager {
    /**
     * Change the title of a project
     * @param projectID the ID of the project
     * @param newTitle the new title of the project
     * @throws ModelNotFoundException if the project is not found
     * @throws ModelAlreadyExistsException if the new title is already used by another project
     */
    public static void changeProjectTitle(String projectID, String newTitle) throws ModelNotFoundException, ModelAlreadyExistsException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * View all the projects that are available
     * @throws ModelNotFoundException if the project is not found
     */
    public static void viewAvailableProjects() throws ModelNotFoundException {
        for(Project p : ProjectRepository.getInstance().findByRules(p -> p.getStatus() == ProjectStatus.AVAILABLE))
            p.displayProject();
    }

    /**
     * create a new project
     * @param projectID the ID of the project
     * @param projectTitle the title of the project
     * @param supervisorID the ID of the supervisor
     * @throws ModelAlreadyExistsException if the project already exists
     */
    public static void createProject(String projectID, String projectTitle, String supervisorID) throws ModelAlreadyExistsException {
        Project p1 = new Project(projectID, projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    /**
     * transfer a student to a new supervisor
     * @param projectID the ID of the project
     * @param supervisorID the ID of the supervisor
     * @throws ModelNotFoundException if the project is not found
     */
    public static void transferToNewSupervisor(String projectID, String supervisorID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setSupervisorID(supervisorID);
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * View all the projects
     * @throws ModelNotFoundException if the project is not found
     */
    public static void viewAllProject() throws ModelNotFoundException {
        for(Project p : ProjectRepository.getInstance())
            p.displayProject();
    }

    /**
     * deallocate a project
     * @param projectID the ID of the project
     * @throws ModelNotFoundException if the project is not found
     */
    public static void deallocateProject(String projectID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID("");
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * allocate a project
     * @param projectID the ID of the project
     * @param studentID the ID of the student
     * @throws ModelNotFoundException if the project is not found
     */
    public static void allocateProject(String projectID, String studentID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID(studentID);
        ProjectRepository.getInstance().update(p1);
    }

}
