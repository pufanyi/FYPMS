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
    /**
     * Transfer a student to a new supervisor
     * @param projectID the project ID of the project that the student is currently in
     * @param supervisorID the supervisor ID of the supervisor that the student is currently in
     * @param newSupervisorID the supervisor ID of the supervisor that the student is going to be transferred to
     * @param studentID the student ID of the student that is going to be transferred
     */
    public static void transferToNewSupervisor(String projectID, String supervisorID, String newSupervisorID, String studentID) {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new TransferStudentRequest(requestID, projectID, supervisorID, newSupervisorID, studentID);
        try {
            RequestRepository.getInstance().add(request);
        } catch (ModelAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    /**
     * Change the title of a project
     * @param newtitle the new title of the project
     * @param projectID the project ID of the project that is going to be changed
     * @throws ModelNotFoundException if the project is not found
     * @throws ModelAlreadyExistsException if the project title already exists
     */
    public static void changeProjectTitle(String newtitle, String projectID) throws ModelNotFoundException, ModelAlreadyExistsException {
        ProjectManager.changeProjectTitle(projectID, newtitle);
    }

    /**
     * Approve a request
     * @param requestID the request ID of the request that is going to be approved
     * @throws ModelNotFoundException if the request is not found
     */
    public static void approveRequest(String requestID) throws ModelNotFoundException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() == RequestStatus.PENDING){
            request.setStatus(RequestStatus.APPROVED);
            RequestRepository.getInstance().update(request);
        }
    }

    /**
     * Reject a request
     * @param requestID the request ID of the request that is going to be rejected
     * @throws ModelNotFoundException if the request is not found
     */
    public static void rejectRequest(String requestID) throws ModelNotFoundException {
        Request request = RequestRepository.getInstance().getByID(requestID);
        if(request.getStatus() == RequestStatus.PENDING){
            request.setStatus(RequestStatus.DENIED);
            RequestRepository.getInstance().update(request);
        }
    }

    /**
     * View all requests
     * @param supervisorID the supervisor ID of the supervisor that is going to view all requests
     */
    public static void viewRequest(String supervisorID) {
        for(Request request : RequestRepository.getInstance().findByRules(request -> request.getID().equals(supervisorID)))
            request.display();
    }

    /**
     * Create a project
     * @param supervisorID the supervisor ID of the supervisor that is going to create the project
     * @param title the title of the project
     * @throws ModelAlreadyExistsException if the project title already exists
     */
    public static void createProject(String supervisorID, String title) throws ModelAlreadyExistsException {
        String projectID = ProjectRepository.getInstance().size() + "";
        ProjectManager.createProject(projectID, supervisorID, title);
    }
}
