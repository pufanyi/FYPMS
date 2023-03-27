package main.controller.request;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.studentrequest.StudentChangeTitleRequest;
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

public class CoordinatorRequestManager {
    public void transferToNewSupervisor(Request request, String newSupervisor) throws ModelNotFoundException {
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            Project project = ProjectRepository.getInstance().getByID(projectID);
            String studentID = request.getStudentID();
            Student student = StudentRepository.getInstance().getByID(studentID);
            project.setSupervisorID(newSupervisor);
            ProjectRepository.getInstance().update(project);
        }
    }

    public void deregisterStudent(Request request) throws ModelNotFoundException, IllegalStateException, StudentStatusException {
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            Project project = ProjectRepository.getInstance().getByID(projectID);
            String studentID = request.getStudentID();
            Student student = StudentRepository.getInstance().getByID(studentID);
            project.setStatus(ProjectStatus.AVAILABLE);
            project.setStudentID("");
            student.setStatus(StudentStatus.DEREGISTERED);
            ProjectRepository.getInstance().update(project);
            StudentRepository.getInstance().update(student);
        }
    }

    public void registerStudent(Request request) throws ModelNotFoundException {
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            Project project = ProjectRepository.getInstance().getByID(projectID);
            String studentID = request.getStudentID();
            Student student = StudentRepository.getInstance().getByID(studentID);
            project.setStatus(ProjectStatus.ALLOCATED);
            project.setStudentID(studentID);
            student.setStatus(StudentStatus.REGISTERED);
            ProjectRepository.getInstance().update(project);
            StudentRepository.getInstance().update(student);
        }
    }

    public void changeProjectTitle(String projectID, String newTitle, String studentID, String supervisorID) throws ModelNotFoundException {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new StudentChangeTitleRequest(requestID, projectID, newTitle, studentID, supervisorID);

    }

    public void viewAllRequest() {

    }

    public void viewPendingRequest() {

    }

    public void approveRequest(String requestID) {
        
    }

    public void rejectRequest(String requestID) {

    }
}
