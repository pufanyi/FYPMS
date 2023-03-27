package main.controller.request;

import main.controller.project.ProjectManager;
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
    public static void transferToNewSupervisor(Request request, String newSupervisor) throws ModelNotFoundException {
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            Project project = ProjectRepository.getInstance().getByID(projectID);
            String studentID = request.getStudentID();
            Student student = StudentRepository.getInstance().getByID(studentID);
            project.setSupervisorID(newSupervisor);
            ProjectRepository.getInstance().update(project);
        }
    }

    public static void deregisterStudent(Request request) throws ModelNotFoundException, IllegalStateException, StudentStatusException {
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            String studentID = request.getStudentID();
            Student student = StudentRepository.getInstance().getByID(studentID);
            ProjectManager.deallocateProject(projectID);
            student.setStatus(StudentStatus.DEREGISTERED);
            StudentRepository.getInstance().update(student);
        }
    }

    public static void registerStudent(Request request) throws ModelNotFoundException {
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            String studentID = request.getStudentID();
            Student student = StudentRepository.getInstance().getByID(studentID);
            ProjectManager.allocateProject(projectID, studentID);
            student.setStatus(StudentStatus.REGISTERED);
            StudentRepository.getInstance().update(student);
        }
    }

    public static void changeProjectTitle(String requestID, String newtitle) throws ModelNotFoundException, ModelAlreadyExistsException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() == RequestStatus.APPROVED){
            String projectID = request.getProjectID();
            ProjectManager.changeProjectTitle(projectID, newtitle);
        }
    }

    public void viewAllRequest() {
        for (Request request : RequestRepository.getInstance()) {
            request.display();
        }
    }

    public void viewPendingRequest() {
        for (Request request : RequestRepository.getInstance().findByRules(request -> request.getStatus() == RequestStatus.PENDING)) {
            request.display();
        }
    }

    public void approveRequest(String requestID) throws ModelNotFoundException, IllegalStateException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() != RequestStatus.PENDING){
            throw new IllegalStateException("Request is not pending");
        }
        request.setStatus(RequestStatus.APPROVED);
        RequestRepository.getInstance().update(request);
    }

    public void rejectRequest(String requestID) throws ModelNotFoundException, IllegalStateException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() != RequestStatus.PENDING){
            throw new IllegalStateException("Request is not pending");
        }
        request.setStatus(RequestStatus.DENIED);
        RequestRepository.getInstance().update(request);
    }
}
