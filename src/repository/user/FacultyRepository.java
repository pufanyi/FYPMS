package repository.user;

import model.user.Supervisor;
import repository.Repository;
import utils.config.Location;

import java.util.List;
import java.util.Map;

public class FacultyRepository extends Repository<Supervisor> {
    final private static String FILE_PATH = "/data/faculty.txt";

    /**
     * Gets the file path.
     *
     * @return the file path
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
