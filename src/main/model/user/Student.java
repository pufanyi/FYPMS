package main.model.user;

import main.utils.parameters.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a student, which is a type of user.
 * It extends the User class and includes a student ID field.
 */
public class Student extends User {
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
    }

    /**
     * Constructs a new Student object with the specified student ID and password.
     *
     * @param studentID   the ID of the student.
     * @param studentName the name of the student.
     * @param email       the email of the student.
     * @param password    the password of the student.
     */
    public Student(String studentID, String studentName, String email, @NotNull String password) {
        super(password);
        this.studentID = studentID;
        this.studentName = studentName;
        this.email = email;
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
     * Converts the object to a map
     *
     * @return the map
     */
    @Override
    public Map<String, String> toMap() {
        Map<String, String> ans = new HashMap<>();
        ans.put("studentID", studentID);
        ans.put("studentName", studentName);
        ans.put("email", email);
        ans.put("status", status.toString());
        ans.put("password", getPassword());
        return ans;
    }

    /**
     * Converts the map to an object
     *
     * @param informationMap the map
     */
    @Override
    public void fromMap(Map<String, String> informationMap) {
        StudentStatus status = StudentStatus.valueOf(informationMap.get("status"));
        String password = informationMap.get("password");
        this.status = status;
        setPassword(password);
        this.studentID = informationMap.get("studentID");
        this.studentName = informationMap.get("studentName");
        this.email = informationMap.get("email");
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
}
