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
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

public class StudentRequestManager {
    public void deregisterStudent(String projectID, String studentID, String supervisorID) throws IllegalStateException, StudentStatusException, ModelAlreadyExistsException, ModelNotFoundException {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new StudentDeregistrationRequest(requestID, projectID, studentID, supervisorID);
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Student student = StudentRepository.getInstance().getByID(studentID);
        RequestRepository.getInstance().add(request);
        if (project.getStatus() != ProjectStatus.ALLOCATED) {
            throw new IllegalStateException("Project is not allocated");
        }
        if (!project.getStudentID().equals(studentID)) {
            throw new IllegalStateException("Student is not allocated to this project");
        }
        if (student.getStatus() == StudentStatus.UNREGISTERED) {
            throw new StudentStatusException(student.getStatus());
        }
    }

    public void registerStudent(String projectID, String studentID, String supervisorID) throws ModelNotFoundException, StudentStatusException, IllegalStateException {
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
            throw new StudentStatusException(student.getStatus());
        }
        project.setStatus(ProjectStatus.RESERVED);
        project.setStudentID(studentID);
        student.setStatus(StudentStatus.PENDING);
        ProjectRepository.getInstance().update(project);
        StudentRepository.getInstance().update(student);
    }

    public void viewAllRequestByStudent(String studentID) {

    }
}

