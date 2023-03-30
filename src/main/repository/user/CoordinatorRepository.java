package main.repository.user;

import main.model.user.Coordinator;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

import static main.utils.config.Location.LOCATION;

/**
 * The CoordinatorRepository class is a repository that stores Coordinator objects
 * through file I/O operations.
 * It extends the Repository class, which provides basic CRUD operations for the repository.
 */
public class CoordinatorRepository extends Repository<Coordinator> {

    /**
     * The path of the repository file.
     */
    private static final String FILE_PATH = "/data/user/coordinator.txt";

    /**
     * Constructor for creating a new CoordinatorRepository object.
     */
    CoordinatorRepository() {
        super();
        load();
    }

    /**
     * Gets a new instance of CoordinatorRepository.
     * @return a new instance of CoordinatorRepository
     */
    public static CoordinatorRepository getInstance() {
        return new CoordinatorRepository();
    }

    /**
     * Gets the file path of the repository.
     * @return the file path of the repository
     */
    @Override
    public String getFilePath() {
        return LOCATION + FILE_PATH;
    }

    /**
     * Sets the list of mappable objects in the repository.
     * @param listOfMappableObjects the list of mappable objects to set
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Coordinator(map));
        }
    }
}
