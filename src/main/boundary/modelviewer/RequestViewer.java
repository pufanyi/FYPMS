package main.boundary.modelviewer;

import main.model.request.Request;

import java.util.List;

public class RequestViewer {
    private static void viewRequest(Request request) {
        request.display();
    }

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
