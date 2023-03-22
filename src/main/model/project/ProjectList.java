package main.model.project;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectList {
    /**
     * The list of projects.
     */
    private final List<Project> projectList;

    /**
     * Constructs a new ProjectList object with an empty list of projects.
     */
    public ProjectList() {
        projectList = new ArrayList<>();
    }

    /**
     * Adds a project to the list of projects.
     *
     * @param project the project to add
     */
    public void addProject(Project project) {
        projectList.add(project);
    }

    /**
     * Removes a project from the list of projects.
     *
     * @param project the project to remove
     * @throws NoSuchElementException if no such project exists
     */
    public void removeProject(Project project) {
        projectList.remove(project);
    }

    /**
     * Gets a project from the list of projects.
     *
     * @param projectID the ID of the project to get
     * @return the project with the specified ID
     * @throws NoSuchElementException if no such project exists
     */
    public Project getProject(Integer projectID) throws NoSuchElementException {
        for (Project project : projectList) {
            if (project.getProjectID().equals(projectID)) {
                return project;
            }
        }
        throw new NoSuchElementException("No project with ID " + projectID + " exists.");
    }

    /**
     * Checks if the list of projects contains a project.
     *
     * @param project the project to check
     * @return true if the list of projects contains the project, false otherwise
     */
    public boolean contains(Project project) {
        return projectList.contains(project);
    }

    /**
     * Checks if the list of projects contains a project with the specified ID.
     *
     * @param projectID the ID of the project to check
     * @return true if the list of projects contains a project with the specified ID, false otherwise
     */
    public boolean contains(Integer projectID) {
        try {
            getProject(projectID);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }
}
