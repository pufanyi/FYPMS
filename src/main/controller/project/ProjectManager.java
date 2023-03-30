package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.user.FacultyRepository;
import main.utils.config.Location;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.iocontrol.CSVReader;

import java.util.List;

import java.util.Scanner;

public class ProjectManager {
    /**
     * Change the title of a project
     *
     * @param projectID the ID of the project
     * @param newTitle  the new title of the project
     * @throws ModelNotFoundException      if the project is not found
     * @throws ModelAlreadyExistsException if the new title is already used by another project
     */
    public static void changeProjectTitle(String projectID, String newTitle) throws ModelNotFoundException, ModelAlreadyExistsException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * View all the projects that are available
     *
     * @throws ModelNotFoundException if the project is not found
     */
    public static void viewAvailableProjects() throws ModelNotFoundException {
        for (Project p : ProjectRepository.getInstance().findByRules(p -> p.getStatus() == ProjectStatus.AVAILABLE))
            p.displayProject();
    }

    /**
     * create a new project
     *
     * @param projectID    the ID of the project
     * @param projectTitle the title of the project
     * @param supervisorID the ID of the supervisor
     * @throws ModelAlreadyExistsException if the project already exists
     */
    public static void createProject(String projectID, String projectTitle, String supervisorID) throws ModelAlreadyExistsException {
        Project p1 = new Project(projectID, projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    public static void createProject(String projectTitle, String supervisorID) throws ModelAlreadyExistsException {
        Project p1 = new Project(getNewProjectID(), projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    public static String getNewProjectID() {
        int max = 0;
        for (Project p : ProjectRepository.getInstance()) {
            int id = Integer.parseInt(p.getID().substring(1));
            if (id > max) {
                max = id;
            }
        }
        return "P" + (max + 1);
    }

    /**
     * transfer a student to a new supervisor
     *
     * @param projectID    the ID of the project
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
     *
     * @throws ModelNotFoundException if the project is not found
     */
    public static void viewAllProject() throws ModelNotFoundException {
        for (Project p : ProjectRepository.getInstance())
            p.displayProject();
    }

    public void viewProjectSubmittedBySupervisor(String SupervisorID){
        Project p1=Pr
    }
    /**
     * deallocate a project
     *
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
     *
     * @param projectID the ID of the project
     * @param studentID the ID of the student
     * @throws ModelNotFoundException if the project is not found
     */
    public static void allocateProject(String projectID, String studentID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID(studentID);
        ProjectRepository.getInstance().update(p1);
    }

    public static void loadProjects() {
        List<List<String>> projects = CSVReader.read(Location.LOCATION + "\\resources\\ProjectList.csv", true);
        for (List<String> project : projects) {
            try {
                String supervisorName = project.get(0);
                String projectName = project.get(1);
                List<Supervisor> supervisors = FacultyRepository.getInstance().findByRules(s -> s.getUserName().equals(supervisorName));
                if (supervisors.size() == 0) {
                    System.out.println("Load project " + projectName + " failed: supervisor " + supervisorName + " not found");
                } else if (supervisors.size() == 1) {
                    ProjectManager.createProject(projectName, supervisors.get(0).getID());
                } else {
                    System.out.println("Load project " + projectName + " failed: multiple supervisors found");
                }
            } catch (ModelAlreadyExistsException e) {
                e.printStackTrace();
            }
        }
    }
    public static boolean repositoryIsEmpty() {
        return ProjectRepository.getInstance().isEmpty();
    }
}
