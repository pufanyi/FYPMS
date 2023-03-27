package main.controller.request;

import main.model.request.Request;
import main.model.request.supervirsorrequest.TransferStudentRequest;
import main.repository.request.RequestRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;

public class SupervisorRequestManager {
    public void transferToNewSupervisor(String projectID, String supervisorID, String newSupervisorID, String studentID) {
        String requestID = RequestRepository.getInstance().size() + "";
        Request request = new TransferStudentRequest(requestID, projectID, supervisorID, newSupervisorID, studentID);
        try {
            RequestRepository.getInstance().add(request);
        } catch (ModelAlreadyExistsException e) {
            e.printStackTrace();
        }
    }
}
