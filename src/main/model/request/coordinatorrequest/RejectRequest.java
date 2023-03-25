package main.model.request.coordinatorrequest;

import main.model.request.ChangeRequest;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.repository.request.RequestRepository;

import java.util.HashMap;
import java.util.Map;

public class RejectRequest extends CoordinatorRequest implements ChangeRequest {
    /**
     * The requestID to be rejected
     */
    private String requestID;
    /**
     * The type of the request
     */
    private static final String requestType = "Reject request";
    /**
     * The status of the request
     */
    private RequestStatus status;
    /**
     * The request to be rejected
     */
    private Request request;

    /**
     * Constructs a new RejectRequest object with the specified request.
     * @param requestID the requestID to be rejected
     */
    public RejectRequest(String requestID) {
        super();
        this.requestID = requestID;
        request = RequestRepository.getInstance().getByID(requestID);
        status = request.getStatus();
    }

    @Override
    public Map<String, String> toMap() {
        Map<String, String> ans = new HashMap<>();
        ans.put("requestType", requestType);
        ans.put("requestID", requestID);
        ans.put("status", request.getStatus().toString());
        return ans;
    }

    @Override
    public void fromMap(Map<String, String> map) {
        this.requestID = map.get("requestID");
        this.status = RequestStatus.valueOf(map.get("status"));
    }

    /**
     * Reject the request
     * @throws IllegalStateException if the request is not pending
     */
    public void reject() throws IllegalStateException{
        if(request.getStatus() != RequestStatus.PENDING)
            throw new IllegalStateException("Request is not pending");
        else if(request.getStatus() == RequestStatus.APPROVED) {
            throw new IllegalStateException("Request is already approved");
        }
        request.setStatus(RequestStatus.DENIED);
    }
}
