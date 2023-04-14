package test.controller;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.ModelAlreadyExistsException;
import org.junit.jupiter.api.BeforeEach;

public class SupervisorManagerTest {
    /**
     * Before all test, clear all data in the database
     * Create a student and a few projects
     */
    @BeforeEach
    public void setUp() throws ModelAlreadyExistsException {
        StudentRepository.getInstance().clear();
        CoordinatorRepository.getInstance().clear();
        FacultyRepository.getInstance().clear();
        RequestRepository.getInstance().clear();
        ProjectRepository.getInstance().clear();
        Student student1 = new Student("FPU001", "Pu Fanyi", "pufanyi@gmail.com");
        Student student2 = new Student("JQY001", "Jin Qingyang", "jinqingyang@gmail.com");
        Supervisor supervisor1 = new Supervisor("BOAN001", "BO AN", "boan@ntu.edu.sg");
        Supervisor supervisor2 = new Supervisor("LIYI001", "LI YI", "liyi@ntu.edu.sg");
        Project project1 = new Project("1", "Blockchain technology", "BOAN001");
        StudentRepository.getInstance().add(student1);
        StudentRepository.getInstance().add(student2);
        ProjectRepository.getInstance().add(project1);
        FacultyRepository.getInstance().add(supervisor1);
        FacultyRepository.getInstance().add(supervisor2);
        ProjectManager.updateProjectsStatus();
    }

}
