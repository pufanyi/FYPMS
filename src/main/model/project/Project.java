package main.model.project;

import main.model.Displayable;
import main.model.Model;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.parameters.EmptyID;

import java.util.Map;

/**
 * The class of the project
 */
public class Project implements Model, Displayable {
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
        this.studentID = EmptyID.EMPTY_ID;
        this.status = ProjectStatus.AVAILABLE;
    }

    public Project(Map<String, String> map) {
        fromMap(map);
    }

    /**
     * Display the information of the supervisor
     */
    private void displaySupervisorInformation(String supervisorID) {
        try {
            Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
            System.out.println("Supervisor Name: " + supervisor.getUserName());
            System.out.println("Supervisor Email Address: " + supervisor.getEmail());
        } catch (ModelNotFoundException e) {
            System.out.println("No Supervisor Yet");
        }
    }

    /**
     * Display the information of the student
     */
    private void displayStudentInformation() {
        try {
            Student student = StudentRepository.getInstance().getByID(studentID);
            System.out.println("Student Name: " + student.getUserName());
            System.out.println("Student Email Address: " + student.getEmail());
        } catch (ModelNotFoundException e) {
            System.out.println("No Student Yet");
        }
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
        displayProjectID();
        displaySupervisorInformation(this.supervisorID);
        if (status == ProjectStatus.ALLOCATED) {
            displayStudentInformation();
        }
        displayProjectInformation();
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

    /**
     * Display the information of the supervisor.
     */
    private String getProjectSupervisorInformationString() {
        try {
            Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
            return String.format("| Supervisor Name             | %-30s |\n", supervisor.getUserName()) +
                    String.format("| Supervisor Email Address    | %-30s |\n", supervisor.getEmail());
        } catch (ModelNotFoundException e) {
            return "No Supervisor Yet";
        }
    }

    /**
     * Display the information of the student.
     */
    private String getProjectStudentInformationString() {
        if (EmptyID.isEmptyID(studentID)) {
            return "";
        }
        try {
            Student student = StudentRepository.getInstance().getByID(studentID);
            return String.format("| Student Name                | %-30s |\n", student.getUserName()) +
                    String.format("| Student Email Address       | %-30s |\n", student.getEmail());
        } catch (ModelNotFoundException e) {
            throw new IllegalStateException("Cannot find the student.");
        }
    }

    /**
     * Display the information of the project.
     */
    private String getProjectInformationString() {
        return String.format("| Project Status              | %-30s |\n", getStatus());
    }

    /**
     * Display the complete information of the project.
     */
    private String getSingleProjectString() {
        String projectTitle = getProjectTitle();
        int maxTitleLength = 60;
        String titleLine1;
        String titleLine2;

        if (projectTitle.length() <= maxTitleLength) {
            int leftPadding = (maxTitleLength - projectTitle.length()) / 2;
            int rightPadding = maxTitleLength - projectTitle.length() - leftPadding;
            titleLine1 = String.format("| %-" + leftPadding + "s%-" + projectTitle.length() + "s%-" + rightPadding + "s |\n", "", projectTitle, "");
            titleLine2 = "";
        } else {
            String[] words = projectTitle.split("\\s+");
            String firstLine = "";
            String secondLine = "";
            int remainingLength = maxTitleLength;
            int i = 0;
            while (i < words.length) {
                if (firstLine.length() + words[i].length() + 1 <= maxTitleLength) {
                    firstLine += words[i] + " ";
                    remainingLength = maxTitleLength - firstLine.length();
                    i++;
                } else {
                    break;
                }
            }
            for (; i < words.length; i++) {
                if (secondLine.length() + words[i].length() + 1 <= maxTitleLength) {
                    secondLine += words[i] + " ";
                } else {
                    break;
                }
            }
            int leftPadding1 = (maxTitleLength - firstLine.length()) / 2;
            int leftPadding2 = (maxTitleLength - secondLine.length()) / 2;
            int rightPadding1 = maxTitleLength - firstLine.length() - leftPadding1;
            int rightPadding2 = maxTitleLength - secondLine.length() - leftPadding2;
            titleLine1 = String.format("| %-" + leftPadding1 + "s%-" + firstLine.length() + "s%-" + rightPadding1 + "s |\n", "", firstLine.trim(), "");
            titleLine2 = String.format("| %-" + leftPadding2 + "s%-" + secondLine.length() + "s%-" + rightPadding2 + "s |\n", "", secondLine.trim(), "");
        }

        return titleLine1 + titleLine2 +
                "|--------------------------------------------------------------|\n" +
                String.format("| Project ID                  | %-30s |\n", getID()) +
                getProjectSupervisorInformationString() +
                getProjectStudentInformationString() +
                getProjectInformationString();
    }

    @Override
    public String getDisplayableString() {
        return getSingleProjectString();
    }

    final String splitter = "================================================================";

    @Override
    public String getSplitter() {
        return splitter;
    }
}
