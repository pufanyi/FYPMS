package main.model.request.coordinatorrequest;

import main.model.request.Request;
import main.model.request.RequestRepository;
import main.model.request.RequestStatus;
import main.model.request.RequestView;

import java.util.List;
import java.util.Map;

public class ViewPendingRequest extends CoordinatorRequest implements RequestView{
    /**
     * The request to be viewed
     */
    private Request request;

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
     * View all the pending requests
     */
    @Override
    public void view() {
        List<Request> requestList = RequestRepository.getInstance().findByRules(request1 -> request1.getStatus() == RequestStatus.PENDING);

    }
}
