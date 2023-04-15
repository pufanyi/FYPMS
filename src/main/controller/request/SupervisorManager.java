package main.controller.request;

import main.model.project.ProjectStatus;
import main.model.request.Request;
import main.model.request.RequestStatus;
import main.model.request.RequestType;
import main.model.request.TransferStudentRequest;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.FacultyRepository;
import main.utils.exception.ModelAlreadyExistsException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * SupervisorManager class
 */
public class SupervisorManager {
    /**
     * The maximum number of students that a supervisor can supervise
     */
    public static int MAX_NUM_OF_STUDENTS_PER_SUPERVISOR = 2;

    /**
     * Transfer a student to a new supervisor
     *
     * @param projectID       the project ID of the project that the student is currently in
     * @param supervisorID    the supervisor ID of the supervisor that the student is currently in
     * @param newSupervisorID the supervisor ID of the supervisor that the student is going to be transferred to
     * @param studentID       the student ID of the student that is going to be transferred
     */
    public static void transferToNewSupervisor(String projectID, String supervisorID, String newSupervisorID, String studentID) {
        String requestID = RequestManager.getNewRequestID();
        Request request = new TransferStudentRequest(requestID, projectID, supervisorID, newSupervisorID, studentID);
        try {
            RequestRepository.getInstance().add(request);
        } catch (ModelAlreadyExistsException e) {
            e.printStackTrace();
        }
    }

    /**
     * View all requests
     *
     * @param supervisorID the supervisor ID of the supervisor that is going to view all requests
     */
    public static List<Request> viewRequest(String supervisorID) {
        return RequestRepository.getInstance().findByRules(request -> request.getID().equals(supervisorID));
    }

    /**
     * get all pending requests by supervisor
     * @param supervisorID the supervisor ID of the supervisor that is going to view all pending requests
     * @return list of pending requests
     */
    public static List<Request> getPendingRequestsBySupervisor(String supervisorID) {
        if (!FacultyRepository.getInstance().contains(supervisorID)) {
            throw new IllegalArgumentException("Supervisor does not exist");
        }
        return RequestRepository.getInstance().findByRules(
                request -> request.getSupervisorID().equals(supervisorID),
                request -> request.getStatus() == RequestStatus.PENDING,
                request -> request.getRequestType() != RequestType.SUPERVISOR_TRANSFER_STUDENT
        );
    }

    /**
     * get all request history by supervisor
     * @param supervisor the supervisor that is going to view all request history
     *
     * @return list of request history
     */
    public static List<Request> getAllRequestHistory(Supervisor supervisor) {
        return RequestRepository.getInstance().findByRules(
                request -> Objects.equals(request.getSupervisorID(), supervisor.getID()),
                request -> request.getRequestType() != RequestType.STUDENT_REGISTRATION,
                request -> request.getRequestType() != RequestType.STUDENT_DEREGISTRATION
        );
    }

    /**
     * get number of students that a supervisor is supervising
     * @param supervisorID the supervisor ID of the supervisor
     *
     * @return number of students that a supervisor is supervising
     */
    public static int getNumOfStudents(String supervisorID) {
        return ProjectRepository.getInstance().findByRules(
                project -> project.getSupervisorID().equalsIgnoreCase(supervisorID),
                project -> project.getStatus() == ProjectStatus.ALLOCATED ||
                        project.getStatus() == ProjectStatus.RESERVED
        ).size();
    }

    /**
     * get all supervisors that are not available
     *
     * @return list of supervisors that are not available
     */
    public static List<Supervisor> getAllUnavailableSupervisors() {
        List<Supervisor> supervisors = new ArrayList<>();
        for (Supervisor supervisor : FacultyRepository.getInstance()) {
            if (getNumOfStudents(supervisor.getID()) >= MAX_NUM_OF_STUDENTS_PER_SUPERVISOR) {
                supervisors.add(supervisor);
            }
        }
        return supervisors;
    }
}
