package user;

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
     * constructor of a new Coordinator object with the specified coordinator ID
     *
     * @param coordinatorID the ID of the coordinator
     */
    public Coordinator(String coordinatorID, String coordinatorName) {
        this.coordinatorID = coordinatorID;
        this.coordinatorName = coordinatorName;
        passwordManager = new PasswordManager();
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
}
