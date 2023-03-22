package test.model.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import model.project.Project;
import model.project.ProjectList;
import model.user.Supervisor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjectListTest {
    /**
     * An array of projects
     */
    Project[] projects;

    /**
     * Add all projects to the project list
     */
    @BeforeAll
    public void addAllProjects() {
        Project project1 = new Project(233, "Project 1", new Supervisor("a", "b", "c"));
        Project project2 = new Project(234, "Project 2", new Supervisor("ea", "f", "g"));
        Project project3 = new Project(235, "Project 3", new Supervisor("h", "i", "j"));
        projects = new Project[]{project1, project2, project3};
    }

    /**
     * Test add project
     */
    @Test
    @DisplayName("Test Add Project")
    public void testAddProject() {
        ProjectList projectList = new ProjectList();
        for (Project project : projects) {
            projectList.addProject(project);
        }
        assertEquals(projects[0], projectList.getProject(projects[0].getProjectID()));
    }
}
