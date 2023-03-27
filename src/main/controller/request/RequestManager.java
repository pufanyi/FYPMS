package main.controller.request;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.studentrequest.StudentDeregistrationRequest;
import main.model.request.studentrequest.StudentRegistrationRequest;
import main.model.request.supervirsorrequest.TransferStudentRequest;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.model.StudentStatusException;
import main.utils.exception.repository.ModelNotFoundException;

public class RequestManager {
    public void transferToNewSupervisor(String projectID, String supervisorID, String newSupervisorID, String studentID) throws ModelNotFoundException {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new TransferStudentRequest(requestID, projectID, supervisorID, newSupervisorID, studentID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        project.setSupervisorID(newSupervisorID);
        ProjectRepository.getInstance().update(project);
    }

    public void deregisterStudent(String projectID, String studentID, String supervisorID) throws ModelNotFoundException, IllegalStateException, StudentStatusException, ProjectStatusException {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new StudentDeregistrationRequest(requestID, projectID, studentID, supervisorID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Student student = StudentRepository.getInstance().getByID(studentID);
        if (project.getStatus() != ProjectStatus.ALLOCATED) {
            throw new IllegalStateException("Project is not allocated");
        }
        if (!project.getStudentID().equals(studentID)) {
            throw new IllegalStateException("Student is not allocated to this project");
        }
        if (student.getStatus() != StudentStatus.REGISTERED) {
            throw new StudentStatusException("Student is not registered");
        }
        project.setStatus(ProjectStatus.AVAILABLE);
        project.setStudentID("");
        student.setStatus(StudentStatus.DEREGISTERED);
        ProjectRepository.getInstance().update(project);
        StudentRepository.getInstance().update(student);
    }

    public void registerStudent(String projectID, String studentID, String supervisorID) throws ModelNotFoundException, StudentStatusException {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new StudentRegistrationRequest(requestID, projectID, studentID, supervisorID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Student student = StudentRepository.getInstance().getByID(studentID);
        if (project.getStatus() != ProjectStatus.AVAILABLE) {
            throw new IllegalStateException("Project is not available");
        }
        if (student.getStatus() == StudentStatus.REGISTERED) {
            throw new StudentStatusException(student.getStatus());
        }
        if (student.getStatus() == StudentStatus.DEREGISTERED) {
            throw new IllegalStateException("Student is already registered to project");
        }
    }

    public void changeProjectTitle(String projectID, String newTitle) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    public void viewAllRequest() {

    }

    public void viewAllRequestByStudent(String studentID) {

    }

    public void viewPendingRequest() {

    }

    public void approveRequest(String requestID) {

    }

    public void rejectRequest(String requestID) {

    }
}
