package main.repository.project;

import main.model.project.Project;
import main.repository.Repository;

import java.util.List;
import java.util.Map;

public class ProjectRepository extends Repository<Project> {
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

    ProjectRepository() {
        super();
        load();
    }

    public static ProjectRepository getInstance() {
        return new ProjectRepository();
    }
}
