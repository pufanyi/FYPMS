package main.repository.user;

import main.model.user.Student;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

import static main.utils.config.Location.LOCATION;

public class StudentRepository extends Repository<Student> {
    private static final String FILE_PATH = "/data/user/student.txt";

    StudentRepository() {
        super();
        load();
    }

    public static StudentRepository getInstance() {
        return new StudentRepository();
    }

    /**
     * Gets the path of the repository file.
     *
     * @return the path of the repository file
     */
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
            getAll().add(new Student(map));
        }
    }
}
