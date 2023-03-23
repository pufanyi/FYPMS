package main.model.user;

import main.model.user.password.PasswordManager;
import main.utils.parameters.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a coordinator
 */
public class Coordinator extends User {
    /**
     * The ID of the coordinator
     */
    private String coordinatorID;
    /**
     * The name of the coordinator
     */
    private String coordinatorName;
    /**
     * The password of a coordinator
     */
    private PasswordManager passwordManager;
    /**
     * The email of a coordinator
     */
    private String email;

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
    public Coordinator(String coordinatorID, String coordinatorName, String email, @NotNull String password) {
        super(password);
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
    public String getID() {
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
     * gets the email of the user
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
        ansMap.put("coordinatorID", this.coordinatorID);
        ansMap.put("coordinatorName", this.coordinatorName);
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
        setCoordinatorName(map.get("coordinatorName"));
        setCoordinatorID(map.get("coordinatorID"));
        setEmail(map.get("email"));
        setPassword(map.get("password"));
    }

    public void setCoordinatorID(String coordinatorID) {
        this.coordinatorID = coordinatorID;
    }

    public void setCoordinatorName(String coordinatorName) {
        this.coordinatorName = coordinatorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Converts the map to a coordinator object
     *
     * @param map the map
     */
    public Coordinator(Map<String, String> map) {
        this.fromMap(map);
    }

    public static User getUser(Map<String, String> map) {
        return new Coordinator(map);
    }

    public Coordinator() {
        this.coordinatorID = "";
        this.coordinatorName = "";
        this.passwordManager = new PasswordManager();
        this.email = "";
    }

    public boolean equals(Object o) {
        if (o instanceof Coordinator c) {
            return o.hashCode() == this.hashCode();
        }
        return false;
    }
}
