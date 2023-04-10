package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.config.Location;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.iocontrol.CSVReader;
import main.utils.parameters.EmptyID;

import java.util.List;
import java.util.Objects;

/**
 * A class manages the project
 */
public class ProjectManager {

    /**
     * Change the title of a project
     *
     * @param projectID the ID of the project
     * @param newTitle  the new title of the project
     * @throws ModelNotFoundException if the project is not found
     */
    public static void changeProjectTitle(String projectID, String newTitle) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * Change the supervisor of a project
     *
     * @return the new supervisor
     */
    public static List<Project> viewAllProject() {
        return ProjectRepository.getInstance().getList();
    }

    /**
     * View all the projects that are available
     *
     * @return the list of available projects
     */
    public static List<Project> viewAvailableProjects() {
        return ProjectRepository.getInstance().findByRules(p -> p.getStatus() == ProjectStatus.AVAILABLE);
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

    /**
     * create a new project
     *
     * @param projectTitle the title of the project
     * @param supervisorID the ID of the supervisor
     * @throws ModelAlreadyExistsException if the project already exists
     */
    public static void createProject(String projectTitle, String supervisorID) throws ModelAlreadyExistsException {
        Project p1 = new Project(getNewProjectID(), projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    /**
     * get the list of all projects
     *
     * @return the list of all projects
     */
    public static List<Project> getAllProject() {
        return ProjectRepository.getInstance().getList();
    }

    /**
     * get the list of all projects by status
     *
     * @param projectStatus the status of the project
     * @return the list of all projects
     */
    public static List<Project> getAllProjectByStatus(ProjectStatus projectStatus) {
        return ProjectRepository.getInstance().findByRules(project -> project.getStatus().equals(projectStatus));
    }

    /**
     * get the list of all projects by supervisor
     *
     * @return the list of all projects
     */
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
        if (!FacultyRepository.getInstance().contains(supervisorID)) {
            throw new IllegalStateException("Supervisor Not Found!");
        }
        Supervisor oldsupervisor=FacultyRepository.getInstance().getByID(p1.getSupervisorID());
        Supervisor newsupervisor=FacultyRepository.getInstance().getByID(supervisorID);
        oldsupervisor.decNumOfSupervisingProject();
        newsupervisor.incNumOfSupervisingProject();
        p1.setSupervisorID(supervisorID);
        ProjectRepository.getInstance().update(p1);
        FacultyRepository.getInstance().update(oldsupervisor);
        FacultyRepository.getInstance().update(newsupervisor);
        if (oldsupervisor.getNumOfSupervisingProject()==1) controlProjectStatus(oldsupervisor);
        if (newsupervisor.getNumOfSupervisingProject()==2) controlProjectStatus(newsupervisor);

    }


    /**
     * deallocate a project
     *
     * @param projectID the ID of the project
     * @throws ModelNotFoundException if the project is not found
     */
    public static void deallocateProject(String projectID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        if (p1.getStatus() != ProjectStatus.ALLOCATED) {
            throw new IllegalStateException("The project status is not ALLOCATED");
        }
        Student student;
        try {
            student = StudentRepository.getInstance().getByID(p1.getStudentID());
        } catch (ModelNotFoundException e) {
            throw new IllegalStateException("Student not found");
        }
        student.setProjectID(EmptyID.EMPTY_ID);
        student.setSupervisorID(EmptyID.EMPTY_ID);
        student.setStatus(StudentStatus.DEREGISTERED);
        p1.setStudentID(EmptyID.EMPTY_ID);
        p1.setSupervisorID(EmptyID.EMPTY_ID);
        p1.setStatus(ProjectStatus.AVAILABLE);
        String supervisorID=p1.getSupervisorID();
        Supervisor supervisor=FacultyRepository.getInstance().getByID(supervisorID);
        ProjectRepository.getInstance().update(p1);
        StudentRepository.getInstance().update(student);
        FacultyRepository.getInstance().update(supervisor);
        if (supervisor.getNumOfSupervisingProject()==1) controlProjectStatus(supervisor);
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
        Student student;
        try {
            student = StudentRepository.getInstance().getByID(studentID);
        } catch (ModelNotFoundException e) {
            throw new IllegalStateException("Student not found");
        }
        if (p1.getStatus() == ProjectStatus.ALLOCATED) {
            throw new IllegalStateException("Project is already allocated");
        }
        if (student.getStatus() == StudentStatus.REGISTERED) {
            throw new IllegalStateException("Student is already registered");
        }
        p1.setStatus(ProjectStatus.ALLOCATED);
        p1.setStudentID(studentID);
        student.setProjectID(projectID);
        student.setSupervisorID(p1.getSupervisorID());
        student.setStatus(StudentStatus.REGISTERED);
        String supervisorID=p1.getSupervisorID();
        Supervisor supervisor=FacultyRepository.getInstance().getByID(supervisorID);
        supervisor.incNumOfSupervisingProject();
        ProjectRepository.getInstance().update(p1);
        StudentRepository.getInstance().update(student);
        FacultyRepository.getInstance().update(supervisor);
        if (supervisor.getNumOfSupervisingProject()==2) controlProjectStatus(supervisor);
    }

    public static void controlProjectStatus(Supervisor supervisor) throws ModelNotFoundException {
        if (supervisor.getNumOfSupervisingProject()>=2){
            for (Project p:ProjectRepository.getInstance().findByRules(p1->(p1.getSupervisorID().equals(supervisor.getID()) && EmptyID.isEmptyID(p1.getStudentID())))){
                p.setStatus(ProjectStatus.UNAVAILABLE);
                ProjectRepository.getInstance().update(p);
            }
        }
        else {
            for (Project p:ProjectRepository.getInstance().findByRules(p1->p1.getSupervisorID().equals(supervisor.getID())&& EmptyID.isEmptyID(p1.getStudentID()))){
                p.setStatus(ProjectStatus.AVAILABLE);
                ProjectRepository.getInstance().update(p);
            }
        }
    }

    /**
     * load projects from csv resource file
     */
    public static void loadProjects() {
        List<List<String>> projects = CSVReader.read(Location.RESOURCE_LOCATION + "/resources/ProjectList.csv", true);
        for (List<String> project : projects) {
            try {
                String supervisorName = project.get(0);
                String projectName = project.get(1);
                List<Supervisor> supervisors = FacultyRepository.getInstance().findByRules(s -> s.checkUsername(supervisorName));
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

    /**
     * check if the repository is empty
     *
     * @return true if the repository is empty
     */
    public static boolean repositoryIsEmpty() {
        return ProjectRepository.getInstance().isEmpty();
    }

    /**
     * Check if the project is not in the repository
     *
     * @param projectID the ID of the project
     * @return true if the project is not in the repository
     */
    public static boolean notContainsProjectByID(String projectID) {
        return !ProjectRepository.getInstance().contains(projectID);
    }

    /**
     * Check if the project is in the repository
     *
     * @param projectID the ID of the project
     * @return true if the project is in the repository
     */
    public static boolean containsProjectByID(String projectID) {
        return ProjectRepository.getInstance().contains(projectID);
    }

    /**
     * get the project of a student
     *
     * @param student the student
     * @return the project of the student
     */
    public static Project getStudentProject(Student student) {
        if (EmptyID.isEmptyID(student.getProjectID())) {
            return null;
        } else {
            try {
                return ProjectRepository.getInstance().getByID(student.getProjectID());
            } catch (ModelNotFoundException e) {
                throw new IllegalStateException("Project " + student.getProjectID() + " not found");
            }
        }
    }

    /**
     * get the project of a supervisor
     *
     * @param projectID the ID of the project
     * @return the project of the supervisor
     * @throws ModelNotFoundException if the project is not found
     */
    public static Project getByID(String projectID) throws ModelNotFoundException {
        return ProjectRepository.getInstance().getByID(projectID);
    }

    public static List<Project> getAllAvailableProject() {
        return ProjectRepository.getInstance().findByRules(p -> p.getStatus() == ProjectStatus.AVAILABLE);
    }

    public static Project getProjectByID(String projectID) throws ModelNotFoundException {
        return ProjectRepository.getInstance().getByID(projectID);
    }
}
