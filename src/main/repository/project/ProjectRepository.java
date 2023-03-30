package main.repository.project;

import main.model.project.Project;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

import static main.utils.config.Location.LOCATION;

/**
 * The ProjectRepository class is a repository that manages the persistence and retrieval of Project objects
 * through file I/O operations.
 * It extends the Repository class, which provides basic CRUD operations for the repository.
 */
public class ProjectRepository extends Repository<Project> {

    /**
     * The file path of the project data file.
     */
    private static final String FILE_PATH = "/data/project/project.txt";

    /**
     * Constructs a new ProjectRepository object and loads the data from the project data file.
     */
    ProjectRepository() {
        super();
        load();
    }

    /**
     * Gets a new ProjectRepository object.
     * @return a new ProjectRepository object
     */
    public static ProjectRepository getInstance() {
        return new ProjectRepository();
    }

    /**
     * Gets the file path of the project data file.
     * @return the file path of the project data file
     */
    @Override
    public String getFilePath() {
        return LOCATION + FILE_PATH;
    }

    /**
     * Sets the list of mappable objects by converting a list of maps to a list of Project objects.
     * @param listOfMappableObjects the list of mappable objects
     */
    @Override
    public void setAll(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            getAll().add(new Project(map));
        }
    }
}
