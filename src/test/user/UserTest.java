package test.user;

import org.junit.Test;

import static org.junit.Assert.*;

import user.Coordinator;
import user.Student;
import user.Supervisor;
import user.User;

public class UserTest {
    public void testUserID(User user, String id) {
        assertEquals(id, user.getUserID());
        assertNotEquals("hahaha", user.getUserID());
    }

    public void testUserPassword(User user, String password) {
        assertTrue(user.checkPassword(password));
        user.setPassword("123456");
        assertTrue(user.checkPassword("123456"));
        assertFalse(user.checkPassword("697298730"));
        assertFalse(user.checkPassword(null));
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
