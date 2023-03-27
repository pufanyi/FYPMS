package main.controller.request;

import main.controller.project.ProjectManager;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.supervirsorrequest.TransferStudentRequest;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

public class SupervisorRequestManager {
    public static void transferToNewSupervisor(String projectID, String supervisorID, String newSupervisorID, String studentID) {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new TransferStudentRequest(requestID, projectID, supervisorID, newSupervisorID, studentID);
        try {
            RequestRepository.getInstance().add(request);
        } catch (ModelAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    public static void changeProjectTitle(String newtitle, String projectID) throws ModelNotFoundException, ModelAlreadyExistsException {
        ProjectManager.changeProjectTitle(projectID, newtitle);
    }

    public static void approveRequest(String requestID) throws ModelNotFoundException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() == RequestStatus.PENDING){
            request.setStatus(RequestStatus.APPROVED);
            RequestRepository.getInstance().update(request);
        }
    }

    public static void rejectRequest(String requestID) throws ModelNotFoundException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() == RequestStatus.PENDING){
            request.setStatus(RequestStatus.DENIED);
            RequestRepository.getInstance().update(request);
        }
    }

    public static void viewRequest(String supervisorID) {

    }

    public static void createProject(String supervisorID, String title) throws ModelAlreadyExistsException {
        String projectID = ProjectRepository.getInstance().size() + "";
        ProjectManager.createProject(projectID, supervisorID, title);
    }
}
