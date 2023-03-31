package main.model.project;

import main.model.Model;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelNotFoundException;

import java.util.Map;

/**
 * The class of the project
 */
public class Project implements Model {
    /**
     * the status of the project
     */
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

    public Project(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Display the information of the supervisor
     */
    private void displaySupervisorInformation() throws ModelNotFoundException {
        Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
        System.out.println("Supervisor Name: " + supervisor.getUserName());
        System.out.println("Supervisor Email Address: " + supervisor.getEmail());
    }

    /**
     * Display the information of the student
     */
    private void displayStudentInformation() throws ModelNotFoundException {
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
    public void displayProject() throws ModelNotFoundException {
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

    /**
     * Get the student ID of the project
     *
     * @return the student ID of the project
     */
    public String getStudentID() {
        return studentID;
    }

    /**
     * Set the student ID of the project
     *
     * @param studentID the student ID of the project
     */
    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    /**
     * Get the supervisor ID of the project
     *
     * @return the supervisor ID of the project
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Set the supervisor ID of the project
     *
     * @param supervisorID the supervisor ID of the project
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * @return the title of the project
     */
    public String getProjectTitle() {
        return projectTitle;
    }

    /**
     * Set the title of the project
     *
     * @param projectTitle the title of the project
     */
    public void setProjectTitle(String projectTitle) {
        this.projectTitle = projectTitle;
    }

    /**
     * Get the status of the project
     *
     * @return the status of the project
     */
    public ProjectStatus getStatus() {
        return status;
    }

    /**
     * Set the status of the project
     *
     * @param status the status of the project
     */
    public void setStatus(ProjectStatus status) {
        this.status = status;
    }

    /**
     * Get the ID of the project
     *
     * @return the ID of the project
     */
    @Override
    public String getID() {
        return projectID;
    }
}
