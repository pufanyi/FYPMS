package main.controller.request;

import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.TransferStudentRequest;
import main.model.user.Supervisor;
import main.repository.request.RequestRepository;
import main.repository.user.FacultyRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;

import java.util.List;
import java.util.Objects;

public class SupervisorManager {
    /**
     * Transfer a student to a new supervisor
     *
     * @param projectID       the project ID of the project that the student is currently in
     * @param supervisorID    the supervisor ID of the supervisor that the student is currently in
     * @param newSupervisorID the supervisor ID of the supervisor that the student is going to be transferred to
     * @param studentID       the student ID of the student that is going to be transferred
     */
    public static void transferToNewSupervisor(String projectID, String supervisorID, String newSupervisorID, String studentID) {
        String requestID = RequestManager.getNewRequestID();
        Request request = new TransferStudentRequest(requestID, projectID, supervisorID, newSupervisorID, studentID);
        try {
            RequestRepository.getInstance().add(request);
        } catch (ModelAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    /**
     * View all requests
     *
     * @param supervisorID the supervisor ID of the supervisor that is going to view all requests
     */
    public static List<Request> viewRequest(String supervisorID) {
        return RequestRepository.getInstance().findByRules(request -> request.getID().equals(supervisorID));
    }

    public static List<Request> getPendingRequestsBySupervisor(String supervisorID) {
        if (!FacultyRepository.getInstance().contains(supervisorID)) {
            throw new IllegalArgumentException("Supervisor does not exist");
        }
        return RequestRepository.getInstance().findByRules(
                request -> request.getSupervisorID().equals(supervisorID),
                request -> request.getStatus() == RequestStatus.PENDING
        );
    }

    public static List<Request> getAllRequestHistory(Supervisor supervisor) {
        return RequestRepository.getInstance().findByRules(
                request -> Objects.equals(request.getSupervisorID(), supervisor.getID())
        );
    }
}