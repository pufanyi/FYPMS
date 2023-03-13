package test.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import user.singleuser.User;
import user.userlist.UserList;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class UserListTest {
    private UserList<User> userList;

    @BeforeEach
    void setUp() {
        userList = new UserList<>();
    }

    @Test
    void addUser() {
        User user1 = createUser("1", "User 1", "user1@example.com", "password1");
        User user2 = createUser("2", "User 2", "user2@example.com", "password2");

        userList.addUser(user1);
        userList.addUser(user2);

        assertEquals(2, userList.getUser().size());
        assertTrue(userList.getUser().contains(user1));
        assertTrue(userList.getUser().contains(user2));
    }

    @Test
    void removeUser() {
        User user1 = createUser("1", "User 1", "user1@example.com", "password1");
        User user2 = createUser("2", "User 2", "user2@example.com", "password2");

        userList.addUser(user1);
        userList.addUser(user2);

        userList.removeUser(user1);

        assertEquals(1, userList.getUser().size());
        assertFalse(userList.getUser().contains(user1));
        assertTrue(userList.getUser().contains(user2));
    }

    @Test
    void getUser() {
        User user1 = createUser("1", "User 1", "user1@example.com", "password1");
        User user2 = createUser("2", "User 2", "user2@example.com", "password2");

        userList.addUser(user1);
        userList.addUser(user2);

        User result = userList.getUser("2");

        assertEquals(user2, result);
    }

    @Test
    void removeUserWithUserID() {
        User user1 = createUser("1", "User 1", "user1@example.com", "password1");
        User user2 = createUser("2", "User 2", "user2@example.com", "password2");

        userList.addUser(user1);
        userList.addUser(user2);

        boolean result = userList.removeUser("1");

        assertTrue(result);
        assertEquals(1, userList.getUser().size());
        assertFalse(userList.getUser().contains(user1));
        assertTrue(userList.getUser().contains(user2));
    }

    @Test
    void removeUserWithUserIDReturnsFalseWhenUserNotFound() {
        User user1 = createUser("1", "User 1", "user1@example.com", "password1");
        User user2 = createUser("2", "User 2", "user2@example.com", "password2");

        userList.addUser(user1);

        boolean result = userList.removeUser("2");

        assertFalse(result);
        assertEquals(1, userList.getUser().size());
        assertTrue(userList.getUser().contains(user1));
        assertFalse(userList.getUser().contains(user2));
    }

    private User createUser(String id, String name, String email, String password) {
        return new User() {
            @Override
            public String getUserID() {
                return id;
            }

            @Override
            public String getUserName() {
                return name;
            }

            @Override
            public void setPassword(String password) {
                // do nothing
            }

            @Override
            public boolean checkPassword(String password) {
                return password.equals(password);
            }

            @Override
            public String getEmail() {
                return email;
            }
        };
    }
}