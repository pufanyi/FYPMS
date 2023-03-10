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
     * The name of the supervisor
     */
    private final String supervisorName;
    /**
     * The password of a supervisor
     */
    private final PasswordManager passwordManager;
    /**
     * The email of a supervisor
     */
    private final String email;

    /**
     * Constructs a new Supervisor object with the specified supervisor ID.
     *
     * @param supervisorID   the ID of the supervisor.
     * @param supervisorName the name of the supervisor.
     * @param email          the email of the supervisor.
     */
    public Supervisor(String supervisorID, String supervisorName, String email) {
        this.supervisorID = supervisorID;
        this.supervisorName = supervisorName;
        this.passwordManager = new PasswordManager();
        this.email = email;
    }

    /**
     * Constructs a new Supervisor object with the specified supervisor ID and password.
     *
     * @param supervisorID   the ID of the supervisor.
     * @param supervisorName the name of the supervisor.
     * @param email          the email of the supervisor.
     * @param password       the password of the supervisor.
     */
    public Supervisor(String supervisorID, String supervisorName, String email, String password) {
        this.supervisorID = supervisorID;
        this.supervisorName = supervisorName;
        this.passwordManager = new PasswordManager(password);
        this.email = email;
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
     * Gets the username
     *
     * @return the name of the user
     */
    @Override
    public String getUserName() {
        return this.supervisorName;
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

    /**
     * Gets the email of the user
     *
     * @return the email of the user
     */
    @Override
    public String getEmail() {
        return this.email;
    }
}
