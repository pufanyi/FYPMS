package main.boundary.supervisor.details;

import main.model.project.Project;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.utils.exception.repository.ModelNotFoundException;

public class ViewerBySupervisor {
    public static void viewProjectBySupervisorA(String SupervisorId) throws ModelNotFoundException {
        for (Project p1: ProjectRepository.getInstance().findByRules(p1->p1.getSupervisorID().equals(SupervisorId)))
            p1.displayProject();
    }

    public static void viewAllPendingRequest(){
        for (Request q1: RequestRepository.getInstance().findByRules(q1->q1.getStatus()== RequestStatus.PENDING))
            q1.display();
    }
}
