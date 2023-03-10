package user;

import java.util.List;
import java.util.ArrayList;

/**
 * A class that represents a list of users.
 */
public class UserList {
    /**
     * The list of users.
     */
    final private List<User> users;

    /**
     * Constructs a new UserList object.
     */
    public UserList() {
        users = new ArrayList<>();
    }

    /**
     * Adds a user to the list of users.
     *
     * @param user the user to add
     */
    public void addUser(User user) {
        users.add(user);
    }

    /**
     * Removes a user from the list of users.
     *
     * @param user the user to remove
     */
    public void removeUser(User user) {
        users.remove(user);
    }

    /**
     * Gets a user from the list of users.
     *
     * @param userID the ID of the user to get
     * @return the user with the specified ID, or null if no such user exists
     */
    public User getUser(String userID) {
        for (User user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        return null;
    }

    /**
     * Removes a user from the list of users.
     *
     * @param userID the ID of the user to remove
     * @return true if the user was removed, false otherwise
     */
    public boolean removeUser(String userID) {
        User user = getUser(userID);
        if (user != null) {
            removeUser(user);
            return true;
        }
        return false;
    }
}
