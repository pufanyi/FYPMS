package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;

/**
 * This class is used to test saving the faculty repository.
 */
public class FacultySaveTest {
    /**
     * The array of supervisors.
     */
    private static Supervisor[] supervisors;

    /**
     * This method is used to set up the supervisors.
     */
    public static void setUp() {
        supervisors = new Supervisor[3];
        supervisors[0] = new Supervisor("A1234567A", "Lucas", "Lucas@e.ntu.edu.sg");
        supervisors[1] = new Supervisor("12345", "pufanyi", "pufanyi@e.ntu.edu.sg");
        supervisors[2] = new Supervisor("78687", "jinqingyang", "jinqingyang@e.ntu.edu.sg");
    }

    /**
     * This method is used to test saving the faculty repository.
     *
     * @param args The command line arguments.
     * @throws ModelAlreadyExistsException If the model already exists.
     */
    public static void main(String[] args) throws ModelAlreadyExistsException {
        setUp();
        FacultyRepository facultyRepository = new FacultyRepository();
        facultyRepository.clear();
        facultyRepository.add(supervisors[0]);
        facultyRepository.add(supervisors[1]);
        facultyRepository.add(supervisors[2]);
    }
}
