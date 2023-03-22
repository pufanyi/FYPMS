package test.repository.user;

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
        supervisors[1] = new Supervisor("12345", "pufanyi", "pufanyi@e.ntu.edu.sg");
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
    public void addUserTest() throws IOException {
        FacultyRepository facultyRepository = new FacultyRepository();
        assertTrue(facultyRepository.isEmpty());
        facultyRepository.add(supervisors[0]);
        assertFalse(facultyRepository.isEmpty());
        assertEquals(1, facultyRepository.size());
        assertNotSame(supervisors[1], facultyRepository.getByID(supervisors[0].getID()));
        assertThrows(NoSuchElementException.class, () -> facultyRepository.getByID("hahaha"));
    }

    @Test
    @DisplayName("Test get all")
    public void getAllTest() throws IOException {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(3, facultyRepository.getAll().size());
        assertEquals(supervisors[0], facultyRepository.getAll().get(0));
        assertEquals(supervisors[1], facultyRepository.getAll().get(1));
        assertEquals(supervisors[2], facultyRepository.getAll().get(2));
    }

    /**
     * Test remove user
     */
    @Test
    @DisplayName("Test remove user")
    public void removeUserTest() throws IOException {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(3, facultyRepository.size());
        assertTrue(facultyRepository.contains(supervisors[0].getID()));
        assertTrue(facultyRepository.contains(supervisors[1].getID()));
        assertTrue(facultyRepository.contains(supervisors[2].getID()));
        facultyRepository.remove(supervisors[1].getID());
        assertEquals(2, facultyRepository.size());
        assertTrue(facultyRepository.contains(supervisors[0].getID()));
        assertFalse(facultyRepository.contains(supervisors[1].getID()));
        assertTrue(facultyRepository.contains(supervisors[2].getID()));
        assertThrows(NoSuchElementException.class, () -> facultyRepository.remove("hahaha"));
    }

    /**
     * Test contains user
     */
    @Test
    @DisplayName("Test contains user")
    public void containUserTest() throws IOException {
        FacultyRepository facultyRepository = createFacultyList();
        assertTrue(facultyRepository.contains(supervisors[0].getID()));
        assertTrue(facultyRepository.contains(supervisors[1].getID()));
        assertTrue(facultyRepository.contains(supervisors[2].getID()));
        assertFalse(facultyRepository.contains("hahaha"));
    }

    @Test
    @DisplayName("Test get user")
    public void getUserTest() throws IOException {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(supervisors[0], facultyRepository.getByID(supervisors[0].getID()));
        assertEquals(supervisors[1], facultyRepository.getByID(supervisors[1].getID()));
        assertEquals(supervisors[2], facultyRepository.getByID(supervisors[2].getID()));
        assertThrows(NoSuchElementException.class, () -> facultyRepository.getByID("hahaha"));
    }

    @Test
    @DisplayName("Test user size")
    public void userSizeTest() throws IOException {
        FacultyRepository facultyRepository = new FacultyRepository();
        assertEquals(0, facultyRepository.size());
        facultyRepository.add(supervisors[0]);
        assertEquals(1, facultyRepository.size());
        facultyRepository.add(supervisors[1]);
        assertEquals(2, facultyRepository.size());
        facultyRepository.add(supervisors[2]);
        assertEquals(3, facultyRepository.size());
        facultyRepository.remove(supervisors[0].getID());
        assertEquals(2, facultyRepository.size());
        facultyRepository.remove(supervisors[1].getID());
        assertEquals(1, facultyRepository.size());
        facultyRepository.remove(supervisors[2].getID());
        assertEquals(0, facultyRepository.size());
    }
}