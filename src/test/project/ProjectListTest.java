package test.project;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import project.Project;
import project.ProjectList;
import user.singleuser.Supervisor;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjectListTest {
    Project[] projects;

    @BeforeAll
    public void addAllProjects() {
        Project project1 = new Project(233, "Project 1", new Supervisor("a", "b", "c"));
        Project project2 = new Project(234, "Project 2", new Supervisor("ea", "f", "g"));
        Project project3 = new Project(235, "Project 3", new Supervisor("h", "i", "j"));
        projects = new Project[]{project1, project2, project3};
    }

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
