package main.model.request.coordinatorrequest;

import main.model.request.ChangeRequest;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.repository.request.RequestRepository;

import java.util.HashMap;
import java.util.Map;

public class ApproveRequest extends CoordinatorRequest implements ChangeRequest {
    /**
     * The requestID to be approved
     */
    private String requestID;
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
    private Request request;

    /**
     * Constructs a new ApproveRequest object with the specified request.
     * @param requestID the requestID to be approved
     */
    public ApproveRequest(String requestID) {
        super();
        this.requestID = requestID;
        request = RequestRepository.getInstance().getByID(requestID);
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
     * Approve the request
     * @throws IllegalStateException if the request is not pending
     */
    public void approve() throws IllegalStateException{
        if(request.getStatus() != RequestStatus.PENDING)
            throw new IllegalStateException("Request is not pending");
        else if(request.getStatus() == RequestStatus.APPROVED)
            throw new IllegalStateException("Request is already approved");
        request.setStatus(RequestStatus.APPROVED);
    }
}
