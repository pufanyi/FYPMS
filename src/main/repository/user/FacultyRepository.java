package main.repository.user;

import main.model.user.Supervisor;
import main.repository.Repository;
import main.utils.config.Location;

import java.util.List;
import java.util.Map;

/**
 * The FacultyRepository class is a repository for storing and managing Supervisor objects in a file
 * through file I/O operations.
 * It extends the Repository class, which provides basic CRUD operations for the repository.
 */
public class FacultyRepository extends Repository<Supervisor> {

    /**
     * The path of the repository file.
     */
    final private static String FILE_PATH = "/data/user/faculty.txt";

    /**
     * Constructor for creating a new instance of the FacultyRepository class.
     */
    public FacultyRepository() {
        super();
        load();
    }

    /**
     * Gets a new instance of the FacultyRepository class.
     *
     * @return a new instance of the FacultyRepository class
     */
    public static FacultyRepository getInstance() {
        return new FacultyRepository();
    }

    /**
     * Gets the path of the repository file.
     *
     * @return the path of the repository file
     */
    @Override
    public String getFilePath() {
        return Location.LOCATION + FILE_PATH;
    }

    /**
     * Sets the list of mappable objects to a list of Supervisor objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Supervisor(map));
        }
    }
}
