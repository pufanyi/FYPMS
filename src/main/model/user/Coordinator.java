package main.model.user;

import main.utils.parameters.NotNull;

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
     * The email of a coordinator
     */
    private String email;
    /**
     * The password of a coordinator
     */
    private String hashedPassword;

    /**
     * constructor of a new Coordinator object with the specified coordinator ID,Name and email
     *
     * @param coordinatorID the ID of the coordinator
     * @param coordinatorName the name of the coordinator
     * @param email the email of the coordinator
     */
    public Coordinator(String coordinatorID, String coordinatorName, String email) {
        this.coordinatorID = coordinatorID;
        this.coordinatorName = coordinatorName;
        this.email = email;
    }

    /**
     * constructor of a new Coordinator object with the specified coordinator ID and password
     */
    public Coordinator(String coordinatorID, String coordinatorName, String email, @NotNull String password) {
        this.coordinatorID = coordinatorID;
        this.coordinatorName = coordinatorName;
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

    @Override
    public String getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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
        this.email = "";
    }

    public boolean equals(Object o) {
        if (o instanceof Coordinator c) {
            return o.hashCode() == this.hashCode();
        }
        return false;
    }
}
