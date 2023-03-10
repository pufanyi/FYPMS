package user;

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
     * The password of a student
     */
    private final PasswordManager passwordManager;

    /**
     * Constructs a new Student object with the specified student ID.
     *
     * @param studentID the ID of the student.
     */
    Student(String studentID) {
        this.studentID = studentID;
        passwordManager = new PasswordManager();
    }

    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    @Override
    public String getUserID() {
        return studentID;
    }

    /**
     * Sets the password for the user.
     *
     * @param password the new password for the user.
     */
    @Override
    public void setPassword(String password) {
        passwordManager.setPassword(password);
    }

    /**
     * check the user input of password
     *
     * @param input the password to check
     * @return whether the password entered is correct
     */
    public boolean checkPassword(String input){
        return passwordManager.checkPassword(input);
    }
}
