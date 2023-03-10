package user;

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
     * Constructs a new Student object with the specified student ID.
     *
     * @param studentID the ID of the student.
     */
    public Student(String studentID) {
        super(studentID);
        this.studentID = studentID;
    }

    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    @Override
    public String getUserID() {
        return null;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    @Override
    public void setPassword(String password) {

    }
}
