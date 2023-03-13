package user.singleuser;

import org.jetbrains.annotations.NotNull;
import user.password.PasswordManager;

/**
 * This class represents a student, which is a type of user.
 * It extends the User class and includes a student ID field.
 */
public class Student implements User {

    /**
     * The ID of the student.
     */
    private final String studentID;
    /**
     * The name of a student
     */
    private final String studentName;
    /**
     * The password of a student
     */
    private final PasswordManager passwordManager;
    /**
     * The email of a student
     */
    private final String email;
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
        this.passwordManager = new PasswordManager();
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
        this.studentID = studentID;
        this.studentName = studentName;
        this.passwordManager = new PasswordManager(password);
        this.email = email;
    }

    /**
     * Gets the email of the user
     *
     * @return the email of the user
     */
    @Override
    public String getUserID() {
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
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    @Override
    public void setPassword(@NotNull String password) {
        passwordManager.setPassword(password);
    }

    /**
     * check the user input of password
     *
     * @param input the password to check
     * @return whether the password entered is correct
     */
    @Override
    public boolean checkPassword(@NotNull String input) {
        return passwordManager.checkPassword(input);
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
}
