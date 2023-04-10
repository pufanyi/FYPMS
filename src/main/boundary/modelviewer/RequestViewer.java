package main.boundary.modelviewer;

import main.model.request.Request;

import java.util.List;

/**
 * Displays the request details.
 */
public class RequestViewer {
    /**
     * This method takes a Request object as a parameter and displays its details by calling the display() method on the request object. The display() method is assumed to be a method in the Request class that displays the details of the request.
     *
     * @param request the request.
     */
    private static void viewRequest(Request request) {
        request.display();
    }

    /**
     * This method takes a list of Request objects as a parameter and displays the details of each request in the list. If the list is empty, it prints "No requests found". Otherwise, it iterates through the list using a for-each loop, and for each request, it displays a separator line (consisting of equal signs) before calling the viewRequest() method to display the details of the request. After displaying all requests, it prints another separator line.
     *
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
