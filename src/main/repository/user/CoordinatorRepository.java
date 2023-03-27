package main.repository.user;

import main.model.user.Coordinator;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

import static main.utils.config.Location.LOCATION;

public class CoordinatorRepository extends Repository<Coordinator> {
    /**
     * The path of the repository file.
     */
    private static final String FILE_PATH = "/data/user/coordinator.txt";

    @Override
    public String getFilePath() {
        return LOCATION + FILE_PATH;
    }

    /**
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Coordinator(map));
        }
    }

    /**
     * constructor
     */
    CoordinatorRepository() {
        super();
        load();
    }


    public static CoordinatorRepository getInstance() {
        return new CoordinatorRepository();
    }
}
