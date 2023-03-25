package main.model.request;

import main.repository.Repository;
import main.repository.user.FacultyRepository;
import main.utils.config.Location;

import java.util.List;
import java.util.Map;

public class RequestRepository extends Repository<Request> {
    final private static String FILE_PATH = "/data/request.txt";
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
            getAll().add(new Request() {
                @Override
                public Map<String, String> toMap() {
                    return null;
                }

                @Override
                public void fromMap(Map<String, String> map) {

                }
            });
    }}

    public RequestRepository() {
        super();
        load();
    }

    public static RequestRepository getInstance() {
        return new RequestRepository();
    }
}
