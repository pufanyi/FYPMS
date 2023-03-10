package user;

/**
 * This class represents a supervisor, which is a type of user.
 * It extends the User class and includes a supervisor ID field.
 */
public class Supervisor implements User {
    /**
     * The ID of the supervisor.
     */
    private final String supervisorID;
    /**
     * The password of a supervisor
     */
    private final PasswordManager passwordManager;

    /**
     * Constructs a new Supervisor object with the specified supervisor ID.
     *
     * @param supervisorID the ID of the supervisor.
     */
    public Supervisor(String supervisorID) {
        this.supervisorID = supervisorID;
        passwordManager = new PasswordManager();
    }

    /**
     * Gets the user ID of the user.
     *
     * @return the ID of the user.
     */
    @Override
    public String getUserID() {
        return this.supervisorID;
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
     * Checks if the password is correct.
     *
     * @param password the password to check
     * @return true if the password is correct, false otherwise
     */
    @Override
    public boolean checkPassword(String password) {
        return passwordManager.checkPassword(password);
    }
}
