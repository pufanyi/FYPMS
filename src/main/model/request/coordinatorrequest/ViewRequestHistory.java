package main.model.request.coordinatorrequest;

import main.model.request.Request;
import main.model.request.ViewRequest;
import main.repository.request.RequestRepository;

import java.util.Map;

public class ViewRequestHistory extends CoordinatorRequest implements ViewRequest {
    /**
     * The request to be viewed
     */
    private Request request;
    /**
     * The type of the request
     */
    private static final String requestType = "View request history";

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
        for(Request request : requestRepository){
            request.displayRequest();
            System.out.println("Request type: " + requestType);
        }
    }
}
