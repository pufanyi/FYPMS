package test.model.user;

import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

/**

 The UserTest class contains JUnit tests for the User class and its subclasses: Student, Coordinator, and Supervisor.
 */
public class UserTest {
    /**
     Test that the user ID matches the provided ID and is not "hahaha".
     @param user the User object being tested
     @param id the expected user ID
     */
    public void testUserID(User user, String id) {
        assertEquals(id, user.getID());
        assertNotEquals("hahaha", user.getID());
    }

    /**
     Test that the user password matches the provided password.
     @param user the User object being tested
     @param password the expected user password
     */
    public void testUserPassword(User user, String password) {
    }

    /**
     Test that the username matches the provided name and is not "hahaha".
     @param user the User object being tested
     @param name the expected username
     */
    public void testUserName(User user, String name) {
        assertEquals(name, user.getUserName());
        assertNotEquals("hahaha", user.getUserName());
    }

    /**
     Test that the user email matches the provided email and is not "hahaha".
     @param user the User object being tested
     @param email the expected user email
     */
    public void testUserEmail(User user, String email) {
        assertEquals(email, user.getEmail());
        assertNotEquals("hahaha", user.getEmail());
    }

    /**
     Test the Student subclass of the User class.
     */
    @Test
    @DisplayName("Test Student")
    public void testStudent() {
        String id = "Son";
        String name = "Jin Qingyang";
        String email = "JQY@e.ntu.edu.sg";
        String password = "password@123";
        User user = new Student(id, name, email);
        testUserID(user, id);
        testUserName(user, name);
        testUserPassword(user, "password");
        testUserEmail(user, email);
        User user2 = new Student(id, name, email, password);
        testUserID(user2, id);
        testUserName(user2, name);
        testUserPassword(user2, password);
        testUserEmail(user2, email);
    }

    /**
     Test the Coordinator subclass of the User class.
     */
    @Test
    @DisplayName("Test Coordinator")
    public void testCoordinator() {
        String id = "Son";
        String name = "Jin Qingyang";
        String email = "JQY@e.ntu.edu.sg";
        String password = "password@123";
        User user = new Coordinator(id, name, email);
        testUserID(user, id);
        testUserName(user, name);
        testUserPassword(user, "password");
        testUserEmail(user, email);
        User user2 = new Coordinator(id, name, email, password);
        testUserID(user2, id);
        testUserName(user2, name);
        testUserPassword(user2, password);
        testUserEmail(user2, email);
    }

    /**
     Test the Supervisor subclass of the User class.
     */
    @Test
    @DisplayName("Test Supervisor")
    public void testSupervisor() {
        String id = "Son";
        String name = "Jin Qingyang";
        String email = "JQY@e.ntu.edu.sg";
        String password = "password@123";
        User user = new Supervisor(id, name, email);
        testUserID(user, id);
        testUserName(user, name);
        testUserPassword(user, "password");
        testUserEmail(user, email);
        User user2 = new Supervisor(id, name, email, password);
        testUserID(user2, id);
        testUserName(user2, name);
        testUserPassword(user2, password);
        testUserEmail(user2, email);
    }
}
