package test.model.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    public void testUserID(User user, String id) {
        assertEquals(id, user.getID());
        assertNotEquals("hahaha", user.getID());
    }

    public void testUserPassword(User user, String password) {
        assertTrue(user.checkPassword(password));
        user.setPassword("123456");
        assertTrue(user.checkPassword("123456"));
        assertFalse(user.checkPassword("697298730"));
    }

    public void testUserName(User user, String name) {
        assertEquals(name, user.getUserName());
        assertNotEquals("hahaha", user.getUserName());
    }

    public void testUserEmail(User user, String email) {
        assertEquals(email, user.getEmail());
        assertNotEquals("hahaha", user.getEmail());
    }

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
