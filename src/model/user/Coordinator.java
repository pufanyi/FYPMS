package model.user;

import model.user.password.PasswordManager;
import utils.parameters.NotNull;

import java.util.HashMap;
import java.util.Map;

/**
 * A class that represents a coordinator
 */
public class Coordinator implements User {
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
     * sets the password for the user
     *
     * @param password the new password for the user.
     */
    public void setPassword(@NotNull String password) {
        passwordManager.setPassword(password);
    }

    /**
     * check the user input of the password
     *
     * @param input the password to check
     * @return whether the password entered is correct
     */
    public boolean checkPassword(@NotNull String input) {
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
        this.coordinatorID = map.get("coordinatorID");
        this.coordinatorName = map.get("coordinatorName");
        this.email = map.get("email");
        if (this.passwordManager == null) {
            this.passwordManager = new PasswordManager();
        }
        this.passwordManager.setHashedPassword(map.get("password"));
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
            return this.coordinatorID.equals(c.coordinatorID);
        }
        return false;
    }
}
