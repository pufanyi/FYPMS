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

    public void testUserPassword(User user) {
        assertTrue(user.checkPassword("password"));
        user.setPassword("123456");
        assertTrue(user.checkPassword("123456"));
        assertFalse(user.checkPassword("697298730"));
        assertFalse(user.checkPassword(null));
    }

    public void testUserName(User user, String name) {
        assertEquals(name, user.getUserName());
        assertNotEquals("hahaha", user.getUserName());
    }

    @Test
    public void testStudent() {
        String id = "Son";
        String name = "Jin Qingyang";
        User user = new Student(id, name);
        testUserID(user, id);
        testUserName(user, name);
        testUserPassword(user);
    }

    @Test
    public void testCoordinator() {
        String id = "Son";
        String name = "Jin Qingyang";
        User user = new Coordinator(id, name);
        testUserID(user, id);
        testUserName(user, name);
        testUserPassword(user);
    }

    @Test
    public void testSupervisor() {
        String id = "Son";
        String name = "Jin Qingyang";
        User user = new Supervisor(id, name);
        testUserID(user, id);
        testUserName(user, name);
        testUserPassword(user);
    }
}
