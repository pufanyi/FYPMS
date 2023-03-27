package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;
import org.junit.jupiter.api.*;

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

    @BeforeEach
    public void setUpEach() {
        FacultyRepository.getInstance().clear();
    }

    private FacultyRepository createFacultyList() {
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
        facultyRepository.add(supervisors[0]);
        assertFalse(facultyRepository.isEmpty());
        assertEquals(1, facultyRepository.size());
        assertNotSame(supervisors[1], facultyRepository.getByID(supervisors[0].getID()));
        assertThrows(NoSuchElementException.class, () -> facultyRepository.getByID("hahaha"));
    }

    /**
     * Test remove user
     */
    @Test
    @DisplayName("Test remove user")
    public void removeUserTest() {
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
    public void containUserTest() {
        FacultyRepository facultyRepository = createFacultyList();
        assertTrue(facultyRepository.contains(supervisors[0].getID()));
        assertTrue(facultyRepository.contains(supervisors[1].getID()));
        assertTrue(facultyRepository.contains(supervisors[2].getID()));
        assertFalse(facultyRepository.contains("hahaha"));
    }

    @Test
    @DisplayName("Test get user")
    public void getUserTest() {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(supervisors[0], facultyRepository.getByID(supervisors[0].getID()));
        assertEquals(supervisors[1], facultyRepository.getByID(supervisors[1].getID()));
        assertEquals(supervisors[2], facultyRepository.getByID(supervisors[2].getID()));
        assertThrows(NoSuchElementException.class, () -> facultyRepository.getByID("hahaha"));
    }

    @Test
    @DisplayName("Test user size")
    public void userSizeTest() {
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

    @Test
    @DisplayName("Find By Rules Test")
    public void findByRulesTest() {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(3, facultyRepository.findByRules(supervisor -> true).size());
        assertEquals(1, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A")).size());
        assertEquals(2, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A") || supervisor.getID().equals("12345")).size());
        assertEquals(0, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A") && supervisor.getID().equals("12345")).size());
        assertEquals(0, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A"), supervisor -> supervisor.getID().equals("12345")).size());
    }
}