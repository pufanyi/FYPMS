package main.controller.request;

import main.model.request.Request;
import main.model.request.RequestStatus;
import main.repository.request.RequestRepository;
import main.utils.exception.repository.ModelNotFoundException;

public class RequestManager {
    public static String getNewRequestID() {
        int max = 0;
        for (Request p : RequestRepository.getInstance()) {
            int id = Integer.parseInt(p.getID().substring(1));
            if (id > max) {
                max = id;
            }
        }
        return "R" + (max + 1);
    }

    public static void approveRequest(String requestID) throws ModelNotFoundException {
        Request r1 = RequestRepository.getInstance().getByID(requestID);
        try {
            r1.setStatus(RequestStatus.APPROVED);
            RequestRepository.getInstance().update(r1);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void rejectRequest(String requestID) throws ModelNotFoundException {
        Request r1 = RequestRepository.getInstance().getByID(requestID);
        try {
            r1.setStatus(RequestStatus.DENIED);
            RequestRepository.getInstance().update(r1);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }
}
