package test.controller;

import main.controller.request.StudentRequestManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.studentrequest.StudentRegistrationRequest;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class StudentRequestMangerTest {
    /**
     * Before all test, clear all data in the database
     * Create a student and a few projects
     */
    @BeforeEach
    public void setUp() {
        StudentRepository.getInstance().clear();
        CoordinatorRepository.getInstance().clear();
        FacultyRepository.getInstance().clear();
        RequestRepository.getInstance().clear();
        ProjectRepository.getInstance().clear();
        Student student1 = new Student("FPU001", "Pu Fanyi", "pufanyi@gmail.com");
        Student student2 = new Student("JQY001", "Jin Qingyang", "jinqingyang@gmail.com");
        Supervisor supervisor1 = new Supervisor("BOAN001","BO AN", "boan@ntu.edu.sg");
        Project project1 = new Project("1", "Blockchain technology", "FPU001");
    }

    @Test
    @DisplayName("Register Student")
    public void testRegisterStudent() throws ModelNotFoundException {
        Student student = StudentRepository.getInstance().getByID("JQY001");
        Project project = ProjectRepository.getInstance().getByID("1");
        String supervisorID = project.getSupervisorID();
        StudentRequestManager.registerStudent(project.getID(), student.getID(), supervisorID);
        assertEquals(student.getStatus(), StudentStatus.PENDING);
        assertEquals(project.getStatus(), ProjectStatus.RESERVED);
    }
}
