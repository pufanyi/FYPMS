package test.repository.user;

import main.model.user.Supervisor;
import main.repository.user.FacultyRepository;

/**
 * This class is used to test loading the faculty repository.
 */
public class FacultyLoadTest {
    /**
     * This method is used to test loading the faculty repository.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        for (Supervisor supervisor : FacultyRepository.getInstance()) {
            System.out.println(supervisor.toMap());
        }
    }
}
