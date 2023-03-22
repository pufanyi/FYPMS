package main.repository.user;

import main.model.user.Student;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

public class StudentRepository extends Repository<Student> {
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
}
