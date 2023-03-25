package main.model.request;

import main.model.Model;
import main.repository.request.RequestRepository;

public abstract class Request extends Model {
    /**
     * The status of the request.
     */
    private RequestStatus status;

    private String requestID;

    protected Request() {
        requestID = generateID();
    }

    public String generateID() {
        return RequestRepository.getInstance().size() + "";
    }

    public String getID() {
        return requestID;
    }
}
