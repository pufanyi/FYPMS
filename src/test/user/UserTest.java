package test.user;

import org.junit.Test;
import static org.junit.Assert.*;

import user.Coordinator;
import user.Student;
import user.Supervisor;
import user.User;
public class UserTest {
    public void testUser(User user){
        assertEquals("122", user.getUserID());
        assertNotEquals("hahaha", user.getUserID());
        assertTrue(user.checkPassword("password"));
        user.setPassword("123456");
        assertTrue(user.checkPassword("123456"));
        assertFalse(user.checkPassword("697298730"));
        assertFalse(user.checkPassword(null));
    }

    @Test
    public void testStudent() {
        testUser(new Student("122"));
    }

    @Test
    public void testCoordinator(){
        testUser(new Coordinator("122"));
    }

    @Test
    public void testSupervisor(){
        testUser(new Supervisor("122"));
    }
}
