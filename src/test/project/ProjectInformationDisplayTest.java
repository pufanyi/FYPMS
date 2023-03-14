package test.project;

import org.junit.jupiter.api.*;
import project.Project;
import user.singleuser.Student;
import user.singleuser.Supervisor;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertLinesMatch;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ProjectInformationDisplayTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeAll
    public void setUpOutputStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterAll
    public void restoreOutputStreams() {
        System.setOut(originalOut);
    }

    @Test
    @DisplayName("Display Information of Available Project")
    public void testDisplayAvailableProjectInformation() {
        outContent.reset();
        Project project = new Project(233, "Project 1", new Supervisor("a", "b", "pfy@e.ntu.edu.sg"));
        String expectedOutput = """
                Project ID: 233
                Supervisor Name: b
                Supervisor Email Address: pfy@e.ntu.edu.sg
                Project Title: Project 1
                Project Status: AVAILABLE
                """;
        project.displayProject();
        assertLinesMatch(expectedOutput.lines(), outContent.toString().lines());
    }

    @Test
    @DisplayName("Display Information of Assigned Project")
    public void testDisplayAssignedProjectInformation() {
        outContent.reset();
        Project project = new Project(233, "Project 1", new Supervisor("a", "b", "pfy@e.ntu.edu.sg"));
        project.assignStudent(new Student("e", "f", "g@e.ntu.edu.sg"));
        String expectedOutput = """
                Project ID: 233
                Supervisor Name: b
                Supervisor Email Address: pfy@e.ntu.edu.sg
                Student Name: f
                Student Email Address: g@e.ntu.edu.sg
                Project Title: Project 1
                Project Status: ALLOCATED
                """;
        project.displayProject();
        assertLinesMatch(expectedOutput.lines(), outContent.toString().lines());
    }
}
