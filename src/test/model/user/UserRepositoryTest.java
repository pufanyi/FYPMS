package test.model.user;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import model.user.Supervisor;
import repository.user.FacultyRepository;

import java.io.IOException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserRepositoryTest {
    private Supervisor[] supervisors;

    @BeforeAll
    public void setUp() {
        supervisors = new Supervisor[3];
        supervisors[0] = new Supervisor("A1234567A", "Lucas", "Lucas@e.ntu.edu.sg");
        supervisors[1] = new Supervisor("12345", "pufanyi","pufanyi@e.ntu.edu.sg");
        supervisors[2] = new Supervisor("78687", "jinqingyang", "jinqingyang@e.ntu.edu.sg");
    }

    private FacultyRepository createFacultyList() throws IOException {
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.add(supervisors[0]);
        facultyRepository.add(supervisors[1]);
        facultyRepository.add(supervisors[2]);
        return facultyRepository;
    }

    /**
     * Test add user
     */
    @Test
    @DisplayName("Test add user")
    public void addUserTest() {
        FacultyRepository facultyRepository = new FacultyRepository();
        assertTrue(facultyRepository.isEmpty());
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