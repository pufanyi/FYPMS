package main.model.request.coordinatorrequest;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.model.request.ChangeRequest;
import main.model.user.Student;
import main.model.user.StudentStatus;
import main.repository.project.ProjectRepository;
import main.repository.user.StudentRepository;

import java.util.HashMap;
import java.util.Map;

public class DeregisterStudent extends CoordinatorRequest implements ChangeRequest {


//    /**
//     * Deregister the student and update the status of the student and the project
//     * @throws IllegalStateException if the student is not registered or the project is not allocated
//     */
//    public void deregisterStudent() throws IllegalStateException {
//        if(studentStatus != StudentStatus.REGISTERED)
//            throw new IllegalStateException("Student is not registered");
//        if(projectStatus != ProjectStatus.ALLOCATED)
//            throw new IllegalStateException("Project is not allocated");
//        student.setStatus(StudentStatus.DEREGISTERED);
//        project.setStatus(ProjectStatus.AVAILABLE);
//        project.setStudentID(null);
//    }
}
