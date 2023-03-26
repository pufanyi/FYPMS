package main.model.request.supervirsorrequest;

import main.model.request.ChangeRequest;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.repository.request.RequestRepository;

import java.util.HashMap;
import java.util.Map;

public class ApproveRequest extends SupervisorRequest implements ChangeRequest {
    /**
     * The requestID to be approved
     */
    private String approvingRequestID;
    /**
     * The type of the request
     */
    private static final String requestType = "Approve request";
    /**
     * The status of the request
     */
    private RequestStatus status;
    /**
     * The request to be approved
     */
    private Request approvingRequest;

    /**
     * Constructs a new ApproveRequest object with the specified request.
     * @param requestID the requestID to be approved
     */
    public ApproveRequest(String requestID, String approvingRequestID) {
        super(requestID);
        this.approvingRequestID = approvingRequestID;
        approvingRequest = RequestRepository.getInstance().getByID(approvingRequestID);
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> ans = new HashMap<>();
        ans.put("requestType", requestType);
        ans.put("requestID", approvingRequestID);
        ans.put("status", approvingRequest.getStatus().toString());
        return ans;
    }

    /**
     * Approve the request
     * @throws IllegalStateException if the request is not pending
     */
    public void approve() throws IllegalStateException{
        if(approvingRequest.getStatus() != RequestStatus.PENDING)
            throw new IllegalStateException("Request is not pending");
        else if(approvingRequest.getStatus() == RequestStatus.APPROVED)
            throw new IllegalStateException("Request is already approved");
        approvingRequest.setStatus(RequestStatus.APPROVED);
    }
}
