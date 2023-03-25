package main.model.request.coordinatorrequest;

import main.model.request.Request;
import main.model.request.RequestView;

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

    }
}
