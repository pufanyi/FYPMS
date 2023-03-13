package user.userlist;

import org.jetbrains.annotations.NotNull;
import user.singleuser.User;

import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * A class that represents a list of users.
 */
public class UserList<UserType extends User> {
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
     * @throws NoSuchElementException if no such user exists
     */
    public void removeUser(@NotNull UserType user) throws NoSuchElementException {
        users.remove(user);
    }

    /**
     * Gets a user from the list of users.
     *
     * @param userID the ID of the user to get
     * @return the user with the specified ID, or null if no such user exists
     * @throws NoSuchElementException if no such user exists
     */
    public UserType getUser(@NotNull String userID) throws NoSuchElementException {
        for (UserType user : users) {
            if (user.getUserID().equals(userID)) {
                return user;
            }
        }
        throw new NoSuchElementException("No user with ID " + userID + " exists.");
    }

    /**
     * Removes a user from the list of users.
     *
     * @param userID the ID of the user to remove
     * @throws NoSuchElementException if no such user exists
     */
    public void removeUser(@NotNull String userID) throws NoSuchElementException {
        removeUser(getUser(userID));
    }

    /**
     * Checks if a user is in the list of users.
     *
     * @param userID the ID of the user to check
     * @return true if the user is in the list of users, false otherwise
     */
    public boolean containsUser(@NotNull String userID) {
        try {
            getUser(userID);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Checks if a user is in the list of users.
     *
     * @param user the user to check
     * @return true if the user is in the list of users, false otherwise
     */
    public boolean containsUser(@NotNull UserType user) {
        try {
            getUser(user.getUserID());
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    /**
     * Gets the number of users in the list.
     *
     * @return the number of users in the list
     */
    public int size() {
        return users.size();
    }
}
