package main.repository.project;

import main.model.project.Project;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

import static main.utils.config.Location.LOCATION;

public class ProjectRepository extends Repository<Project> {
    private static final String FILE_PATH = "/data/project/project.txt";

    ProjectRepository() {
        super();
        load();
    }

    public static ProjectRepository getInstance() {
        return new ProjectRepository();
    }

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
            getAll().add(new Project(map));
        }
    }
}
