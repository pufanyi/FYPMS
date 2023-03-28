package test.controller;

import main.controller.request.CoordinatorRequestManager;
import main.controller.request.StudentRequestManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentRegistrationMangerTest {
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
        Supervisor supervisor1 = new Supervisor("BOAN001","BO AN", "boan@ntu.edu.sg");
        Project project1 = new Project("1", "Blockchain technology", "BOAN001");
        StudentRepository.getInstance().add(student1);
        StudentRepository.getInstance().add(student2);
        ProjectRepository.getInstance().add(project1);
        FacultyRepository.getInstance().add(supervisor1);
    }

    @Test
    @DisplayName("Register Student")
    public void testRegisterStudent() throws ModelNotFoundException, ModelAlreadyExistsException {
        String studentID = "JQY001";
        String projectID = "1";
        Student student = StudentRepository.getInstance().getByID(studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(student.getStatus(), StudentStatus.UNREGISTERED);
        assertEquals(project.getStatus(), ProjectStatus.AVAILABLE);
        String supervisorID = project.getSupervisorID();
        String requestID = StudentRequestManager.registerStudent(projectID, studentID, supervisorID);
        Request request = RequestRepository.getInstance().getByID(requestID);
        student = StudentRepository.getInstance().getByID(studentID);
        assertEquals(student.getStatus(), StudentStatus.PENDING);
        project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(project.getStatus(), ProjectStatus.RESERVED);
    }

    @Test
    @DisplayName("Approve Student Registration Request")
    public void ApproveStudentRegistration() throws ModelAlreadyExistsException, ModelNotFoundException {
        String studentID = "JQY001";
        String projectID = "1";
        Student student = StudentRepository.getInstance().getByID(studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(student.getStatus(), StudentStatus.UNREGISTERED);
        assertEquals(project.getStatus(), ProjectStatus.AVAILABLE);
        String supervisorID = project.getSupervisorID();
        String requestID = StudentRequestManager.registerStudent(projectID, studentID, supervisorID);
        Request request = RequestRepository.getInstance().getByID(requestID);
        student = StudentRepository.getInstance().getByID(studentID);
        assertEquals(student.getStatus(), StudentStatus.PENDING);
        project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(project.getStatus(), ProjectStatus.RESERVED);
        CoordinatorRequestManager.approveRequest(requestID);
        request = RequestRepository.getInstance().getByID(requestID);
        assertEquals(request.getStatus(), RequestStatus.APPROVED);
        CoordinatorRequestManager.approveRegisterStudent(studentID, projectID, supervisorID);
        student = StudentRepository.getInstance().getByID(studentID);
        assertEquals(student.getStatus(), StudentStatus.REGISTERED);
        project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(project.getStatus(), ProjectStatus.ALLOCATED);
    }

    @Test
    @DisplayName("Reject Student Registration Request")
    public void RejectStudentRegistration() throws ModelAlreadyExistsException, ModelNotFoundException {
        String studentID = "JQY001";
        String projectID = "1";
        Student student = StudentRepository.getInstance().getByID(studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(student.getStatus(), StudentStatus.UNREGISTERED);
        assertEquals(project.getStatus(), ProjectStatus.AVAILABLE);
        String supervisorID = project.getSupervisorID();
        String requestID = StudentRequestManager.registerStudent(projectID, studentID, supervisorID);
        Request request = RequestRepository.getInstance().getByID(requestID);
        student = StudentRepository.getInstance().getByID(studentID);
        assertEquals(student.getStatus(), StudentStatus.PENDING);
        project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(project.getStatus(), ProjectStatus.RESERVED);
        CoordinatorRequestManager.rejectRequest(requestID);
        request = RequestRepository.getInstance().getByID(requestID);
        assertEquals(request.getStatus(), RequestStatus.DENIED);
        CoordinatorRequestManager.rejectRegisterStudent(studentID, projectID, supervisorID);
        student = StudentRepository.getInstance().getByID(studentID);
        assertEquals(student.getStatus(), StudentStatus.UNREGISTERED);
        project = ProjectRepository.getInstance().getByID(projectID);
        assertEquals(project.getStatus(), ProjectStatus.AVAILABLE);
    }
}
