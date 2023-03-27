package main.repository.user;

import main.model.user.Supervisor;
import main.repository.Repository;
import main.utils.config.Location;

import java.util.List;
import java.util.Map;

public class FacultyRepository extends Repository<Supervisor> {
    final private static String FILE_PATH = "/data/user/faculty.txt";

    public FacultyRepository() {
        super();
        load();
    }

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
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of model objects
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Supervisor(map));
        }
    }
}
