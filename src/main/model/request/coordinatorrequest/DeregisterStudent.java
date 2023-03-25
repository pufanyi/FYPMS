package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.ChangeRequest;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.repository.project.ProjectRepository;
import main.repository.user.StudentRepository;

import java.util.Map;

public class DeregisterStudent extends CoordinatorRequest implements ChangeRequest {
    /**
     * The student to be deregistered
     */
    private Student student;
    /**
     * The ID of the student
     */
    private String studentID;
    /**
     * The project to be deregistered
     */
    private Project project;
    /**
     * The ID of the project
     */
    private String projectID;
    /**
     * The status of the student
     */
    private StudentStatus studentStatus;
    /**
     * The status of the project
     */
    private ProjectStatus projectStatus;

    /**
     *
     * @param studentID the ID of the student
     * @param projectID the ID of the project
     */
    public DeregisterStudent(String studentID, String projectID) {
        super();
        this.studentID = studentID;
        this.projectID = projectID;
    }

    @Override
    public Map<String, String> toMap() {
        // TODO: fill in the map
        return null;
    }
    @Override
    public void fromMap(Map<String, String> studentMap) {
        // TODO: fill in the map
    }

    public void deregisterStudent() throws IllegalStateException {
        student = StudentRepository.getInstance().getByID(studentID);
        if(student.getStatus() != StudentStatus.REGISTERED)
            throw new IllegalStateException("Student is not registered");
        if(project.getStatus() != ProjectStatus.ALLOCATED)
            throw new IllegalStateException("Project is not allocated");
        student.setStatus(StudentStatus.DEREGISTERED);
        project.setStatus(ProjectStatus.AVAILABLE);
        project.setStudentID(null);
    }
}
