package main.model.user;

import main.utils.parameters.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a student, which is a type of user.
 * It extends the User class and includes a student ID field.
 */
public class Student implements User {
    /**
     * The ID of the student.
     */
    private String studentID;
    /**
     * The name of a student
     */
    private String studentName;
    /**
     * The email of a student
     */
    private String email;
    /**
     * The status of a student
     */
    private StudentStatus status;
    /**
     * The ID of the supervisor
     */
    private String supervisorID;
    /**
     * The ID of the project
     */
    private String projectID;
    private String hashedPassword;

    /**
     * Constructs a new Student object with the specified student ID and default password.
     *
     * @param studentID   the ID of the student.
     * @param studentName the name of the student.
     * @param email       the email of the student.
     */
    public Student(String studentID, String studentName, String email) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.email = email;
        this.status = StudentStatus.UNREGISTERED;
        supervisorID = "NULL";
        projectID = "NULL";
    }

    /**
     * Constructs a new Student object with the specified student ID and password.
     *
     * @param studentID      the ID of the student.
     * @param studentName    the name of the student.
     * @param email          the email of the student.
     * @param hashedPassword the password of the student.
     */
    public Student(String studentID, String studentName, String email, @NotNull String hashedPassword) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.email = email;
        this.status = StudentStatus.UNREGISTERED;
        supervisorID = "NULL";
        projectID = "NULL";
        this.hashedPassword = hashedPassword;
    }

    /**
     * Gets the email of the user
     *
     * @return the email of the user
     */
    @Override
    public String getID() {
        return this.studentID;
    }

    /**
     * Gets the username of the user
     *
     * @return the name of the user
     */
    @Override
    public String getUserName() {
        return this.studentName;
    }

    /**
     * Gets the email of the user
     *
     * @return the email of the user
     */
    @Override
    public String getEmail() {
        return this.email;
    }

    /**
     * Gets the status of the student
     *
     * @return the status of the student
     */
    public StudentStatus getStatus() {
        return this.status;
    }

    /**
     * Sets the status of the student
     *
     * @param status the new status of the student
     */
    public void setStatus(StudentStatus status) {
        this.status = status;
    }

    /**
     * Constructs a new Student object with the specified student ID and password.
     *
     * @param informationMap the map
     */
    public Student(Map<String, String> informationMap) {
        fromMap(informationMap);
    }

    public static User getUser(Map<String, String> informationMap) {
        return new Student(informationMap);
    }

    /**
     *  default constructor for Student class
     */
    public Student() {
        super();
        this.email = "";
        this.studentID = "";
        this.studentName = "";
        this.status = StudentStatus.UNREGISTERED;
    }

    public boolean equals(Object o) {
        if (o instanceof Student otherStudent) {
            return o.hashCode() == this.hashCode();
        }
        return false;
    }

    /**
     * Gets the ID of the supervisor
     *
     * @return the ID of the supervisor
     */
    public String getSupervisorID() {
        return supervisorID;
    }

    /**
     * Sets the ID of the supervisor
     *
     * @param supervisorID the ID of the supervisor
     */
    public void setSupervisorID(String supervisorID) {
        this.supervisorID = supervisorID;
    }

    /**
     * Gets the ID of the project
     *
     * @return the ID of the project
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * Sets the ID of the project
     *
     * @param projectID the ID of the project
     */
    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    /**
     * getter for the password
     * @return hashedPassword
     */
    public String getHashedPassword() {
        return hashedPassword;
    }

    /**
     * setter for the password
     * @param hashedPassword the password that to be set
     */
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }
}
