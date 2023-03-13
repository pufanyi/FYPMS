package test.project;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import project.ProjectList;
import project.Project;
import user.singleuser.Supervisor;
import static org.junit.jupiter.api.Assertions.*;

public class ProjectListTest {
    @Test
    @DisplayName("Test Add Project")
    public void testAddProject() {
        ProjectList projectList = new ProjectList();
        Project project1 = new Project(233, "Project 1", new Supervisor("a", "b", "c"));
        Project project2 = new Project(234, "Project 2", new Supervisor("ea", "f", "g"));
        Project project3 = new Project(235, "Project 3", new Supervisor("h", "i", "j"));
        projectList.addProject(project1);
        projectList.addProject(project2);
        projectList.addProject(project3);
        assertEquals(projectList.getProject(233), project1);
    }
}
