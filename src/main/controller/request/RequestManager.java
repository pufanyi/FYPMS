package main.controller.request;

import main.controller.project.ProjectManager;
import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.*;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.model.user.Supervisor;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.ModelNotFoundException;
import main.utils.exception.PageBackException;
import main.utils.exception.SupervisorStudentsLimitExceedException;

import java.util.Objects;
import java.util.Scanner;

public class RequestManager {
    public static String getNewRequestID() {
        int max = 0;
        for (Request p : RequestRepository.getInstance()) {
            int id = Integer.parseInt(p.getID().substring(1));
            if (id > max) {
                max = id;
            }
        }
        return "R" + (max + 1);
    }

    public static void approveRequestForStatus(String requestID) throws ModelNotFoundException {
        Request r1 = RequestRepository.getInstance().getByID(requestID);
        try {
            r1.setStatus(RequestStatus.APPROVED);
            RequestRepository.getInstance().update(r1);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void rejectRequestForStatus(String requestID) throws ModelNotFoundException {
        Request r1 = RequestRepository.getInstance().getByID(requestID);
        try {
            r1.setStatus(RequestStatus.DENIED);
            RequestRepository.getInstance().update(r1);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void approveStudentChangeTitleRequest(Request request) {
        if (request instanceof StudentChangeTitleRequest studentChangeTitleRequest) {
            String projectID = studentChangeTitleRequest.getProjectID();
            String newTitle = studentChangeTitleRequest.getNewTitle();
            try {
                ProjectManager.changeProjectTitle(projectID, newTitle);
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Request is not a StudentChangeTitleRequest");
        }
    }

    private static void approveTransferStudentRequest(Request request) throws SupervisorStudentsLimitExceedException {
        if (request instanceof TransferStudentRequest transferStudentRequest) {
            String projectID = transferStudentRequest.getProjectID();
            String newSupervisorID = transferStudentRequest.getNewSupervisorID();
            if (SupervisorManager.getNumOfStudents(newSupervisorID) >= SupervisorManager.MAX_NUM_OF_STUDENTS_PER_SUPERVISOR) {
                throw new SupervisorStudentsLimitExceedException();
            }
            try {
                ProjectManager.transferToNewSupervisor(projectID, newSupervisorID);
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Request is not a TransferStudentRequest");
        }
    }

    private static void approveStudentDeregistrationRequest(Request request) {
        if (request instanceof StudentDeregistrationRequest studentDeregistrationRequest) {
            String projectID = studentDeregistrationRequest.getProjectID();
            try {
                ProjectManager.deallocateProject(projectID);
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Request is not a StudentDeregistrationRequest");
        }
    }

    private static void approveStudentRegistrationRequest(Request request) throws ModelNotFoundException {
        if (request instanceof StudentRegistrationRequest studentRegistrationRequest) {
            String projectID = studentRegistrationRequest.getProjectID();
            String studentID = studentRegistrationRequest.getStudentID();
            String supervisorID = studentRegistrationRequest.getSupervisorID();
            Supervisor supervisor = FacultyRepository.getInstance().getByID(supervisorID);
            try {
                ProjectManager.allocateProject(projectID, studentID);
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException("Request is not a StudentRegistrationRequest");
        }
    }

    public static void approveRequest(Request request) throws ModelNotFoundException, SupervisorStudentsLimitExceedException {
        switch (request.getRequestType()) {
            case STUDENT_CHANGE_TITLE -> approveStudentChangeTitleRequest(request);
            case SUPERVISOR_TRANSFER_STUDENT -> approveTransferStudentRequest(request);
            case STUDENT_DEREGISTRATION -> approveStudentDeregistrationRequest(request);
            case STUDENT_REGISTRATION -> approveStudentRegistrationRequest(request);
        }
    }

    public static void approveRequest(String requestID) throws SupervisorStudentsLimitExceedException {
        try {
            Request request = RequestRepository.getInstance().getByID(requestID);
            approveRequest(request);
            approveRequestForStatus(requestID);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void rejectStudentRegistrationRequest(Request request) {
        if (request instanceof StudentRegistrationRequest studentRegistrationRequest) {
            String projectID = studentRegistrationRequest.getProjectID();
            String studentID = studentRegistrationRequest.getStudentID();
            try {
                Student student = StudentManager.getByID(studentID);
                student.setStatus(StudentStatus.UNREGISTERED);
                StudentRepository.getInstance().update(student);
                Project project = ProjectManager.getByID(projectID);
                project.setStatus(ProjectStatus.AVAILABLE);
                ProjectRepository.getInstance().update(project);
                ProjectManager.updateProjectsStatus();
            } catch (ModelNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            throw new IllegalArgumentException("Request is not a StudentRegistrationRequest");
        }
    }

    public static void rejectRequest(String requestID) throws PageBackException {
        try {
            Request request = RequestRepository.getInstance().getByID(requestID);
            if (request.getStatus() != RequestStatus.PENDING) {
                System.out.println("Request is not pending");
                System.out.println("Press enter to go back.");
                new Scanner(System.in).nextLine();
                throw new PageBackException();
            }
            if (Objects.requireNonNull(request.getRequestType()) == RequestType.STUDENT_REGISTRATION) {
                rejectStudentRegistrationRequest(request);
            }
            rejectRequestForStatus(requestID);
        } catch (ModelNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Request getRequest(String requestID) throws ModelNotFoundException {
        return RequestRepository.getInstance().getByID(requestID);
    }
}
