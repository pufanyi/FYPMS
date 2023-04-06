package main.controller.request;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.studentrequest.StudentChangeTitleRequest;
import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.model.StudentStatusException;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;

import java.util.List;

public class CoordinatorRequestManager{
    /**
     * approve a student deregistration request
     *
     * @param studentID    the student ID of the student that is going to deregister from the project
     * @param projectID    the project ID of the project that the student is going to deregister from
     * @param supervisorID the supervisor ID of the supervisor that the student is going to deregister from
     * @throws ModelNotFoundException if the student, project or supervisor is not found
     * @throws IllegalStateException  if the project is not allocated to a student
     * @throws StudentStatusException if the student is not registered
     */
    public static void approveDeregisterStudent(String studentID, String projectID, String supervisorID) throws ModelNotFoundException, IllegalStateException, StudentStatusException {
        Student student = StudentRepository.getInstance().getByID(studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
        if (student.getStatus() != StudentStatus.REGISTERED) {
            throw new StudentStatusException(student.getStatus());
        }
        if (project.getStatus() != ProjectStatus.ALLOCATED) {
            throw new IllegalStateException("Project has not been allocated to a student yet");
        }
        student.setStatus(StudentStatus.DEREGISTERED);
        project.setStudentID(null);
        project.setSupervisorID(null);
        project.setStatus(ProjectStatus.AVAILABLE);
        ProjectRepository.getInstance().update(project);
        StudentRepository.getInstance().update(student);
    }

    /**
     * register a student to a project
     *
     * @param studentID    the student ID
     * @param projectID    the project ID
     * @param supervisorID the supervisor ID
     * @throws ModelNotFoundException if the student, project or supervisor is not found
     */
    public static void approveRegisterStudent(String studentID, String projectID, String supervisorID) throws ModelNotFoundException {
        Student student = StudentRepository.getInstance().getByID(studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
        student.setStatus(StudentStatus.REGISTERED);
        project.setStudentID(studentID);
        project.setSupervisorID(supervisorID);
        project.setStatus(ProjectStatus.ALLOCATED);
        ProjectRepository.getInstance().update(project);
        StudentRepository.getInstance().update(student);
    }

    public static void rejectRegisterStudent(String studentID, String projectID, String supervisorID) throws ModelNotFoundException {
        Student student = StudentRepository.getInstance().getByID(studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
        student.setStatus(StudentStatus.UNREGISTERED);
        project.setStudentID(null);
        project.setSupervisorID(null);
        project.setStatus(ProjectStatus.AVAILABLE);
        ProjectRepository.getInstance().update(project);
        StudentRepository.getInstance().update(student);
    }

    /**
     * change the project title
     *
     * @param requestID the request ID
     * @param newtitle  the new title
     * @throws ModelNotFoundException      if the request is not found
     * @throws ModelAlreadyExistsException if the project title already exists
     */
    public static void changeProjectTitle(String requestID, String newtitle) throws ModelNotFoundException, ModelAlreadyExistsException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if (request.getStatus() == RequestStatus.APPROVED) {
            String projectID = request.getProjectID();
            ProjectManager.changeProjectTitle(projectID, newtitle);
        }
    }

    /**
     * display all the requests
     */
    public static List<Request> viewAllRequest() throws PageBackException {
        return RequestRepository.getInstance().getList();
    }


    /**
     * display all the pending requests
     */
    public static List<Request> viewPendingRequest() {
        return RequestRepository.getInstance().findByRules(request -> request.getStatus() == RequestStatus.PENDING);
    }


    /**
     * approve a request
     *
     * @param requestID the request ID
     * @throws ModelNotFoundException if the request is not found
     * @throws IllegalStateException  if the request is not pending
     */
    public static void approveRequest(String requestID) throws ModelNotFoundException, IllegalStateException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Request is not pending");
        }
        request.setStatus(RequestStatus.APPROVED);
        RequestRepository.getInstance().update(request);
    }

    /**
     * reject a request
     *
     * @param requestID the request ID
     * @throws ModelNotFoundException if the request is not found
     * @throws IllegalStateException  if the request is not pending
     */
    public static void rejectRequest(String requestID) throws ModelNotFoundException, IllegalStateException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if (request.getStatus() != RequestStatus.PENDING) {
            throw new IllegalStateException("Request is not pending");
        }
        request.setStatus(RequestStatus.DENIED);
        RequestRepository.getInstance().update(request);
    }

    public static List<Request> getPendingRequests() {
        return RequestRepository.getInstance().findByRules(request -> request.getStatus() == RequestStatus.PENDING);
    }
}
