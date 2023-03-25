package main.model.request.coordinatorrequest;

import main.model.request.Request;
import main.model.request.ViewRequest;
import main.model.request.RequestRepository;

import java.util.Map;

public class ViewRequestHistory extends CoordinatorRequest implements ViewRequest {
    /**
     * The request to be viewed
     */
    private Request request;

    /**
     * Constructs a new ViewRequestHistory object with the specified request.
     */
    public ViewRequestHistory() {
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

    public void view() {
        RequestRepository requestRepository = RequestRepository.getInstance();
        for(Request request : requestRepository)
            request.displayRequest();
    }
}
