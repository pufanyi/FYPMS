package user;

public class User {
    /**
     * userID of a user
     */
    String userID;
    /**
     * password of a user
     */
    private String password;

    /**
     * constructor of a user
     *
     * @param userID of the user
     */
    public User(String userID) {
        this.userID = userID;
        password = "password";
    }

    /**
     * get the userID of the user
     *
     * @return userID of the user
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param password - set the password for the user
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
