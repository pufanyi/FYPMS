package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.RequestChange;
import main.model.user.Student;
import main.model.user.StudentStatus;

import java.util.Map;

public class AllocateProject extends CoordinatorRequest implements RequestChange{
    private Student student;
    private Project project;
    private StudentStatus studentStatus;
    private ProjectStatus projectStatus;

    /**
     *  Constructor for AllocateProject
     * @param student The student to be allocated
     * @param project The project to be allocated
     */
    public AllocateProject(Student student, Project project) {
        super();
        this.student = student;
        this.project = project;
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

    public void allocateProject() throws IllegalStateException {
        if(student.getStatus() == StudentStatus.REGISTERED)
            throw new IllegalStateException("Student is already registered");
        if(project.getStatus() == ProjectStatus.ALLOCATED)
            throw new IllegalStateException("Project is already allocated");
        student.setStatus(StudentStatus.REGISTERED);
        project.setStatus(ProjectStatus.ALLOCATED);
        project.setStudentID(student.getID());
    }
}
