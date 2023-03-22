package test.model.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import model.user.Student;
import repository.userlist.UserList;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserListTest {
    /**
     * An array of students
     */
    private Student[] students;

    /**
     * Set up the students
     */
    @BeforeAll
    public void setUp() {
        students = new Student[3];
        students[0] = new Student("A1234567A", "Lucas", "Lucas@e.ntu.edu.sg");
        students[1] = new Student("12345", "pufanyi","pufanyi@e.ntu.edu.sg");
        students[2] = new Student("78687", "jinqingyang", "jinqingyang@e.ntu.edu.sg");
    }

    /**
     * Create a user list with 3 students
     * @return the user list
     */
    private UserList<Student> createStudentList() {
        UserList<Student> userList = new UserList<>();
        userList.addUser(students[0]);
        userList.addUser(students[1]);
        userList.addUser(students[2]);
        return userList;
    }

    /**
     * Test add user
     */
    @Test
    @DisplayName("Test add user")
    public void addUserTest() {
        UserList<Student> userList = createStudentList();
        assertSame(students[0], userList.getUser(students[0].getID()));
        assertNotSame(students[1], userList.getUser(students[0].getID()));
        assertThrows(NoSuchElementException.class, () -> userList.getUser("hahaha"));
    }

    /**
     * Test remove user
     */
    @Test
    @DisplayName("Test remove user")
    public void removeUserTest(){
        UserList<Student> userList = createStudentList();
        assertTrue(userList.containsUser(students[0]));
        assertTrue(userList.containsUser(students[1]));
        assertTrue(userList.containsUser(students[2]));
        userList.removeUser(students[1]);
        assertTrue(userList.containsUser(students[0]));
        assertFalse(userList.containsUser(students[1]));
        assertTrue(userList.containsUser(students[2]));
        assertThrows(NoSuchElementException.class, () -> userList.getUser(students[1].getID()));
    }

    /**
     * Test contains user
     */
    @Test
    @DisplayName("Test contains user")
    public void containUserTest(){
        UserList<Student> userList = new UserList<>();
        userList.addUser(students[0]);
        userList.addUser(students[2]);
        assertTrue(userList.containsUser(students[0]));
        assertFalse(userList.containsUser(students[1]));
        assertTrue(userList.containsUser(students[2]));
    }

    @Test
    @DisplayName("Test get user")
    public void getUserTest(){
        UserList<Student> userList = createStudentList();
        for (Student student : students) {
            assertSame(student, userList.getUser(student.getID()));
        }
        assertNotSame(students[0], userList.getUser(students[1].getID()));
        assertThrows(NoSuchElementException.class, () -> userList.getUser("hahaha"));
    }

    @Test
    @DisplayName("Test user size")
    public void userSizeTest(){
        UserList<Student> userList = new UserList<>();
        assertEquals(0, userList.size());
        assertNotEquals(1, userList.size());
        userList.addUser(students[0]);
        userList.addUser(students[1]);
        assertEquals(2, userList.size());
        assertNotEquals(1, userList.size());
    }
}