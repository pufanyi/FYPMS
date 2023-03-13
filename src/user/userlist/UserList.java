package user.userlist;

import org.jetbrains.annotations.NotNull;
import user.singleuser.User;

import java.util.List;
import java.util.ArrayList;

/**
 * A class that represents a list of users.
 */
public class UserList <UserType extends User> {
    /**
     * The list of users.
     */
    final private List<UserType> users;

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
    public void addUser(@NotNull UserType user) {
        users.add(user);
    }

    /**
     * Removes a user from the list of users.
     *
     * @param user the user to remove
     */
    public void removeUser(@NotNull UserType user) {
        users.remove(user);
    }

    /**
     * Gets a user from the list of users.
     *
     * @param userID the ID of the user to get
     * @return the user with the specified ID, or null if no such user exists
     */
    public UserType getUser(String userID) {
        for (UserType user : users) {
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
        UserType user = getUser(userID);
        if (user != null) {
            removeUser(user);
            return true;
        }
        return false;
    }
}
