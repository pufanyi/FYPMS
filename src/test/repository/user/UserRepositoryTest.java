package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import org.junit.jupiter.api.*;

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

    private FacultyRepository createFacultyList() throws ModelAlreadyExistsException {
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
    public void addUserTest() throws ModelNotFoundException, ModelAlreadyExistsException {
        FacultyRepository facultyRepository = new FacultyRepository();
        assertTrue(facultyRepository.isEmpty());
        facultyRepository.add(supervisors[0]);
        assertFalse(facultyRepository.isEmpty());
        assertEquals(1, facultyRepository.size());
        assertNotSame(supervisors[1], facultyRepository.getByID(supervisors[0].getID()));
        assertThrows(ModelNotFoundException.class, () -> facultyRepository.getByID("hahaha"));
    }

    /**
     * Test remove user
     */
    @Test
    @DisplayName("Test remove user")
    public void removeUserTest() throws ModelNotFoundException, ModelAlreadyExistsException {
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
        assertThrows(ModelNotFoundException.class, () -> facultyRepository.remove("hahaha"));
    }

    /**
     * Test contains user
     */
    @Test
    @DisplayName("Test contains user")
    public void containUserTest() throws ModelAlreadyExistsException {
        FacultyRepository facultyRepository = createFacultyList();
        assertTrue(facultyRepository.contains(supervisors[0].getID()));
        assertTrue(facultyRepository.contains(supervisors[1].getID()));
        assertTrue(facultyRepository.contains(supervisors[2].getID()));
        assertFalse(facultyRepository.contains("hahaha"));
    }

    @Test
    @DisplayName("Test get user")
    public void getUserTest() throws ModelNotFoundException, ModelAlreadyExistsException {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(supervisors[0], facultyRepository.getByID(supervisors[0].getID()));
        assertEquals(supervisors[1], facultyRepository.getByID(supervisors[1].getID()));
        assertEquals(supervisors[2], facultyRepository.getByID(supervisors[2].getID()));
        assertThrows(ModelNotFoundException.class, () -> facultyRepository.getByID("hahaha"));
    }

    @Test
    @DisplayName("Test user size")
    public void userSizeTest() throws ModelNotFoundException, ModelAlreadyExistsException {
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
    public void findByRulesTest() throws ModelAlreadyExistsException {
        FacultyRepository facultyRepository = createFacultyList();
        assertEquals(3, facultyRepository.findByRules(supervisor -> true).size());
        assertEquals(1, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A")).size());
        assertEquals(2, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A") || supervisor.getID().equals("12345")).size());
        assertEquals(0, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A") && supervisor.getID().equals("12345")).size());
        assertEquals(0, facultyRepository.findByRules(supervisor -> supervisor.getID().equals("A1234567A"), supervisor -> supervisor.getID().equals("12345")).size());
    }
}