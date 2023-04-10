package main.controller.request;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.*;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.repository.project.ProjectRepository;
import main.repository.request.RequestRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.model.StudentStatusException;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;

import java.util.List;

public class StudentManager {
    /**
     * student request to deregister from a project
     *
     * @param projectID    the project ID of the project that the student is going to deregister from
     * @param studentID    the student ID of the student that is going to deregister from the project
     * @throws IllegalStateException       if the project is not allocated
     * @throws StudentStatusException      if the student is not registered
     * @throws ModelAlreadyExistsException if the request already exists
     * @throws ModelNotFoundException      if the project or student is not found
     * @return The ID of the new reuquest
     */
    public static String deregisterStudent(String projectID, String studentID) throws IllegalStateException, StudentStatusException, ModelAlreadyExistsException, ModelNotFoundException {
        String requestID = RequestManager.getNewRequestID();
        Project project = ProjectRepository.getInstance().getByID(projectID);
        Student student = StudentRepository.getInstance().getByID(studentID);
        if (project.getStatus() != ProjectStatus.ALLOCATED) {
            throw new IllegalStateException("Project is not allocated");
        }
        if (!project.getStudentID().equals(studentID)) {
            throw new IllegalStateException("Student is not allocated to this project");
        }
        if (student.getStatus() == StudentStatus.UNREGISTERED) {
            throw new StudentStatusException(student.getStatus());
        }
        String supervisorID = project.getSupervisorID();
        Request request = new StudentDeregistrationRequest(requestID, projectID, studentID, supervisorID);
        RequestRepository.getInstance().add(request);
        return requestID;
    }

    /**
     * student request to register to a project
     *
     * @param projectID    the project ID of the project that the student is going to register to
     * @param studentID    the student ID of the student that is going to register to the project
     * @throws ModelNotFoundException if the project or student is not found
     * @throws StudentStatusException if the student is not unregistered
     * @throws IllegalStateException  if the project is not available
     * @return the ID of the new Request
     */
    public static String registerStudent(String projectID, String studentID) throws ModelNotFoundException, StudentStatusException, IllegalStateException, ModelAlreadyExistsException {

        Project project = ProjectRepository.getInstance().getByID(projectID);
        Student student = StudentRepository.getInstance().getByID(studentID);
        if (project.getStatus() != ProjectStatus.AVAILABLE) {
            throw new IllegalStateException("Project is not available");
        }
        if (student.getStatus() == StudentStatus.REGISTERED) {
            throw new StudentStatusException(student.getStatus());
        }
        if (student.getStatus() == StudentStatus.DEREGISTERED) {
            throw new StudentStatusException(student.getStatus());
        }
        String requestID = RequestManager.getNewRequestID();
        String supervisorID = project.getSupervisorID();
        Request request = new StudentRegistrationRequest(requestID, studentID, supervisorID, projectID);
        project.setStatus(ProjectStatus.RESERVED);
        ProjectRepository.getInstance().update(project);
        student.setStatus(StudentStatus.PENDING);
        StudentRepository.getInstance().update(student);
        RequestRepository.getInstance().add(request);
        return requestID;
    }

    /**
     * display all requests made by a student
     *
     * @param studentID the student ID of the student that made the requests
     */
    public static void viewAllRequestByStudent(String studentID) {
        for (Request request : RequestRepository.getInstance().findByRules(request -> request.getStudentID().equals(studentID))) {
            request.display();
        }
    }

    /**
     * student request to change the title of a project
     *
     * @param projectID    the project ID of the project that the student is going to change the title of
     * @param newTitle     the new title of the project
     * @param studentID    the student ID of the student that is going to change the title of the project
     * @throws ModelAlreadyExistsException if the request already exists
     */
    public static String changeProjectTitle(String projectID, String newTitle, String studentID) throws ModelAlreadyExistsException, ModelNotFoundException {
        String requestID = RequestManager.getNewRequestID();
        String supervisorID = ProjectRepository.getInstance().getByID(projectID).getSupervisorID();
        Request request = new StudentChangeTitleRequest(requestID, projectID, newTitle, studentID, supervisorID);
        RequestRepository.getInstance().add(request);
        return requestID;
    }

    public static List<Request> getStudentRequestHistory(String studentID) {
//        System.err.println("StudentRequestManager.getStudentRequestHistory studentID = " + studentID);
        return RequestRepository.getInstance().findByRules(request -> request.getStudentID().equals(studentID));
    }

    public static Student getByID(String studentID) throws ModelNotFoundException {
        return StudentRepository.getInstance().getByID(studentID);
    }
}

