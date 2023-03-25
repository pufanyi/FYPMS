package main.model.request.coordinatorrequest;

import main.model.request.Request;
import main.model.request.RequestRepository;
import main.model.request.RequestStatus;
import main.model.request.ViewRequest;

import java.util.List;
import java.util.Map;

public class ViewPendingRequest extends CoordinatorRequest implements ViewRequest {
    /**
     * The request to be viewed
     */
    private Request request;
    /**
     * The type of the request
     */
    private static final String requestType = "View pending request";

    public ViewPendingRequest() {
        super();
    }

    @Override
    public Map<String, String> toMap() {
        //TODO: fill in the map
        return null;
    }

    @Override
    public void fromMap(Map<String, String> map) {
        //TODO: fill in the map
    }

    /**
     * View all the pending requests -- by displaying the ID of the request
     */
    @Override
    public void view() {
        List<Request> requestList = RequestRepository.getInstance().findByRules(request1 -> request1.getStatus() == RequestStatus.PENDING);
        for(Request request : requestList) {
            request.displayRequest();
            System.out.println("Request type: " + requestType);
        }
    }
}
