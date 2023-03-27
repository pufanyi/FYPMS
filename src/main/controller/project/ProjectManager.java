package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.repository.project.ProjectRepository;

import java.util.List;

public class ProjectManager {
    public void changeProjectTitle(String projectID,String newTitle){
        Project p1= ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    public List<Project> viewAvailableProjects(){
        return ProjectRepository.getInstance().findByRules(
                project -> project.getStatus() == ProjectStatus.AVAILABLE
        );
    }

    public void createProject(){

    }
}
