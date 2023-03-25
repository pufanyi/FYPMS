package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.ChangeRequest;
import main.model.user.Student;
import main.model.user.StudentStatus;

import java.util.Map;

public class DeregisterStudent extends CoordinatorRequest implements ChangeRequest {
    /**
     * The student to be deregistered
     */
    private Student student;
    /**
     * The project to be deregistered
     */
    private Project project;
    /**
     * The status of the student
     */
    private StudentStatus studentStatus;
    private ProjectStatus projectStatus;

    /**
     *
     * @param student
     * @param project
     */
    public DeregisterStudent(Student student, Project project) {
        super();
        this.student = student;
        this.project = project;
    }

    /**
     *
     * @param studentMap
     */
    public DeregisterStudent(Map<String, Object> studentMap) {
        this.student = (Student) studentMap.get("student");
        this.project = (Project) studentMap.get("project");
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
        if(student.getStatus() != StudentStatus.REGISTERED)
            throw new IllegalStateException("Student is not registered");
        if(project.getStatus() != ProjectStatus.ALLOCATED)
            throw new IllegalStateException("Project is not allocated");
        student.setStatus(StudentStatus.DEREGISTERED);
        project.setStatus(ProjectStatus.AVAILABLE);
        project.setStudentID(null);
    }
}
