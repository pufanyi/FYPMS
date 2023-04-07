package main.boundary.modelviewer;

import main.model.request.Request;

import java.util.List;

/**
 * Displays the request details.
 */
public class RequestViewer {
    /**
     * Displays the single request details.
     * @param request the request.
     */
    private static void viewRequest(Request request) {
        request.display();
    }

    /**
     * Displays a list of request details.
     * @param requests the list of requests.
     */
    public static void viewRequests(List<Request> requests) {
        if (requests.isEmpty()) {
            System.out.println("No requests found");
            return;
        }
        for (Request request : requests) {
            System.out.println("===================================");
            viewRequest(request);
        }
        System.out.println("===================================");
    }
}
