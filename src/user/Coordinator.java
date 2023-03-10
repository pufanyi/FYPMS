package user;

/**
 * A class that represents a coordinator
 */
public class Coordinator implements User {
    /**
     * The ID of the coordinator
     */
    private final String coordinatorID;
    /**
     * The name of the coordinator
     */
    private final String coordinatorName;
    /**
     * The password of a coordinator
     */
    private final PasswordManager passwordManager;
    /**
     * The email of a coordinator
     */
    private final String email;

    /**
     * constructor of a new Coordinator object with the specified coordinator ID
     *
     * @param coordinatorID the ID of the coordinator
     */
    public Coordinator(String coordinatorID, String coordinatorName, String email) {
        this.coordinatorID = coordinatorID;
        this.coordinatorName = coordinatorName;
        this.passwordManager = new PasswordManager();
        this.email = email;
    }

    /**
     * constructor of a new Coordinator object with the specified coordinator ID and password
     */
    public Coordinator(String coordinatorID, String coordinatorName, String email, String password) {
        this.coordinatorID = coordinatorID;
        this.coordinatorName = coordinatorName;
        this.passwordManager = new PasswordManager(password);
        this.email = email;
    }

    /**
     * gets the user ID of the user
     *
     * @return the ID of the user
     */
    public String getUserID() {
        return this.coordinatorID;
    }

    /**
     * gets the name of the user
     *
     * @return name of the user
     */
    public String getUserName() {
        return this.coordinatorName;
    }

    /**
     * sets the password for the user
     *
     * @param password the new password for the user.
     */
    public void setPassword(String password) {
        passwordManager.setPassword(password);
    }

    /**
     * check the user input of the password
     *
     * @param input the password to check
     * @return whether the password entered is correct
     */
    public boolean checkPassword(String input) {
        return passwordManager.checkPassword(input);
    }

    /**
     * gets the email of the user
     *
     * @return the email of the user
     */
    @Override
    public String getEmail() {
        return this.email;
    }
}
