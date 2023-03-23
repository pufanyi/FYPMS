package main.model.user;

import main.model.user.password.PasswordManager;
import main.utils.parameters.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a supervisor, which is a type of user.
 * It extends the User class and includes a supervisor ID field.
 */
public class Supervisor extends User {
    /**
     * The ID of the supervisor.
     */
    private String supervisorID;
    /**
     * The name of the supervisor
     */
    private String supervisorName;
    /**
     * The password of a supervisor
     */
    private PasswordManager passwordManager;
    /**
     * The email of a supervisor
     */
    private String email;

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
    public Supervisor(String supervisorID, String supervisorName, String email, @NotNull String password) {
        super(password);
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
    public String getID() {
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
     * Gets the email of the user
     *
     * @return the email of the user
     */
    @Override
    public String getEmail() {
        return this.email;
    }


    /**
     * Converts the object to a map
     *
     * @return the map
     */
    @Override
    public Map<String, String> toMap() {
        Map<String, String> ansMap = new HashMap<>();
        ansMap.put("supervisorID", this.supervisorID);
        ansMap.put("supervisorName", this.supervisorName);
        ansMap.put("email", this.email);
        ansMap.put("password", this.passwordManager.getPassword());
        return ansMap;
    }

    /**
     * Converts the map to an object
     *
     * @param map the map
     */
    @Override
    public void fromMap(Map<String, String> map) {
        this.supervisorID = map.get("supervisorID");
        this.supervisorName = map.get("supervisorName");
        this.email = map.get("email");
        if (this.passwordManager == null) {
            this.passwordManager = new PasswordManager();
        }
        this.passwordManager.setPassword(map.get("password"));
    }

    /**
     * Constructs a new Supervisor object from a map
     *
     * @param map the map
     */
    public Supervisor(Map<String, String> map) {
        this.fromMap(map);
    }

    public static User getUser(Map<String, String> map) {
        return new Supervisor(map);
    }

    public Supervisor() {
        this.supervisorID = "";
        this.supervisorName = "";
        this.email = "";
    }

    public boolean equals(Object o) {
        if (o instanceof Supervisor supervisor) {
            return o.hashCode() == this.hashCode();
        }
        return false;
    }
}
