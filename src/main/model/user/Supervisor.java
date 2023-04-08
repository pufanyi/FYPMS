package main.model.user;

import main.utils.parameters.NotNull;

import java.util.Map;

/**
 * This class represents a supervisor, which is a type of user.
 * It extends the User class and includes a supervisor ID field.
 */
public class Supervisor implements User {
    /**
     * The ID of the supervisor.
     */
    private String supervisorID;
    /**
     * The name of the supervisor
     */
    private String supervisorName;
    /**
     * The email of a supervisor
     */
    private String email;
    private String hashedPassword;

    private int numofSupeprvisingProject=0;

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
        this.email = email;
    }

    /**
     * Constructs a new Supervisor object with the specified supervisor ID and password.
     *
     * @param supervisorID   the ID of the supervisor.
     * @param supervisorName the name of the supervisor.
     * @param email          the email of the supervisor.
     * @param hashedPassword the password of the supervisor.
     */
    public Supervisor(String supervisorID, String supervisorName, String email, @NotNull String hashedPassword) {
        this.hashedPassword = hashedPassword;
        this.supervisorID = supervisorID;
        this.supervisorName = supervisorName;
        this.email = email;
    }

    /**
     * Constructs a new Supervisor object from a map
     *
     * @param map the map
     */
    public Supervisor(Map<String, String> map) {
        this.fromMap(map);
    }

    /**
     * default constructor for Supervisor class
     */
    public Supervisor() {
        this.supervisorID = "";
        this.supervisorName = "";
        this.email = "";
    }

    public static User getUser(Map<String, String> map) {
        return new Supervisor(map);
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

    @Override
    public String getHashedPassword() {
        return this.hashedPassword;
    }

    @Override
    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
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

    public void incNumofSupervisingProject(){
        this.numofSupeprvisingProject++;
    }

    public void decNumofSupervisingProject(){
        this.numofSupeprvisingProject++;
    }

    public int getNumofSupervisingProject(){
        return this.numofSupeprvisingProject;
    }
}
