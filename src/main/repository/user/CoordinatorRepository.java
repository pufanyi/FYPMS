package main.repository.user;

import main.model.user.Coordinator;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

public class CoordinatorRepository extends Repository<Coordinator> {
    @Override
    public String getFilePath() {
        // TODO: Implement this method
        return null;
    }

    /**
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        // TODO: Implement this method
    }

    CoordinatorRepository() {
        super();
        load();
    }

    public static CoordinatorRepository getInstance() {
        return new CoordinatorRepository();
    }
}
