package project;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ProjectList {
    private final List<Project> projectList;

    public ProjectList() {
        projectList = new ArrayList<>();
    }

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

    public Project getProject(Integer projectID) throws NoSuchElementException {
        for (Project project : projectList) {
            if (project.getProjectID().equals(projectID)) {
                return project;
            }
        }
        throw new NoSuchElementException("No project with ID " + projectID + " exists.");
    }

    public boolean contains(Project project) {
        return projectList.contains(project);
    }

    public boolean contains(Integer projectID) {
        for (Project project : projectList) {
            if (project.getProjectID().equals(projectID)) {
                return true;
            }
        }
        return false;
    }
}
