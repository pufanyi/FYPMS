package main.model.project;

import main.model.Model;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;

import java.util.Map;

public class Project extends Model {
    ProjectStatus status;

    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * the supervisor of the project
     */
    private String supervisorID;

    /**
     * the student of the project
     */
    private String studentID;
    /**
     * the title of the project
     */
    private String projectTitle;

    /**
     * the constructor of the project
     *
     * @param projectID    the ID of the project
     * @param projectTitle the title of the project
     * @param supervisorID the supervisor of the project
     */
    public Project(String projectID, String projectTitle, String supervisorID) {
        this.projectID = projectID;
        this.projectTitle = projectTitle;
        this.supervisorID = supervisorID;
        this.studentID = "NULL";
        this.status = ProjectStatus.AVAILABLE;
    }

    /**
     * Get the ID of the project
     *
     * @return the ID of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Display the information of the supervisor
     */
    private void displaySupervisorInformation() {
        Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
        System.out.println("Supervisor Name: " + supervisor.getUserName());
        System.out.println("Supervisor Email Address: " + supervisor.getEmail());
    }

    /**
     * Display the information of the student
     */
    private void displayStudentInformation() {
        Student student = StudentRepository.getInstance().getByID(studentID);
        System.out.println("Student Name: " + student.getUserName());
        System.out.println("Student Email Address: " + student.getEmail());
    }

    /**
     * Display the ID of the project
     */
    private void displayProjectID() {
        System.out.println("Project ID: " + projectID);
    }

    /**
     * Display the information of the project
     */
    private void displayProjectInformation() {
        System.out.println("Project Title: " + projectTitle);
        System.out.println("Project Status: " + status);
    }

    /**
     * Display the whole information of the project
     */
    public void displayProject() {
        if (status == ProjectStatus.AVAILABLE) {
            displayProjectID();
            displaySupervisorInformation();
            displayProjectInformation();
        } else if (status == ProjectStatus.ALLOCATED) {
            displayProjectID();
            displaySupervisorInformation();
            displayStudentInformation();
            displayProjectInformation();
        } else {
            throw new IllegalStateException("Project status is not AVAILABLE or ALLOCATED.");
        }
    }

    /**
     * Assign a student to the project
     *
     * @param studentID the student to be assigned
     * @throws IllegalStateException if the project is not available for allocation
     */
    public void assignStudent(String studentID) throws IllegalStateException {
        if (status != ProjectStatus.AVAILABLE) {
            throw new IllegalStateException("Project is not available for allocation.");
        }
        this.studentID = studentID;
        this.status = ProjectStatus.ALLOCATED;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Set the status of the project
     */
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    /**
     * Get the status of the project
     */
    public ProjectStatus getStatus() {
        return status;
    }

    @Override
    public String getID() {
        return projectID;
    }

    /**
     * Converts the object to a map
     *
     * @return the map
     */
    @Override
    public Map<String, String> toMap() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Converts the map to an object
     *
     * @param map the map
     */
    @Override
    public void fromMap(Map<String, String> map) {
        // TODO: Implement this method
    }
}
