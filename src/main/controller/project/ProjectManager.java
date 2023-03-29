package main.controller.project;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.parameters.NotNull;

import java.util.Scanner;

public class ProjectManager {
    /**
     * Change the title of a project
     * @param projectID the ID of the project
     * @param newTitle the new title of the project
     * @throws ModelNotFoundException if the project is not found
     * @throws ModelAlreadyExistsException if the new title is already used by another project
     */
    public static void changeProjectTitle(String projectID, String newTitle) throws ModelNotFoundException, ModelAlreadyExistsException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setProjectTitle(newTitle);
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * View all the projects that are available
     * @throws ModelNotFoundException if the project is not found
     */
    public static void viewAvailableProjects() throws ModelNotFoundException {
        for(Project p : ProjectRepository.getInstance().findByRules(p -> p.getStatus() == ProjectStatus.AVAILABLE))
            p.displayProject();
    }

    /**
     * create a new project
     * @param projectID the ID of the project
     * @param projectTitle the title of the project
     * @param supervisorID the ID of the supervisor
     * @throws ModelAlreadyExistsException if the project already exists
     */
    public static void createProject(String projectID, String projectTitle, String supervisorID) throws ModelAlreadyExistsException {
        Project p1 = new Project(projectID, projectTitle, supervisorID);
        ProjectRepository.getInstance().add(p1);
    }

    /**
     * transfer a student to a new supervisor
     * @param projectID the ID of the project
     * @param supervisorID the ID of the supervisor
     * @throws ModelNotFoundException if the project is not found
     */
    public static void transferToNewSupervisor(String projectID, String supervisorID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setSupervisorID(supervisorID);
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * View all the projects
     * @throws ModelNotFoundException if the project is not found
     */
    public static void viewAllProject() throws ModelNotFoundException {
        for(Project p : ProjectRepository.getInstance())
            p.displayProject();
    }

    /**
     * deallocate a project
     * @param projectID the ID of the project
     * @throws ModelNotFoundException if the project is not found
     */
    public static void deallocateProject(String projectID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID("");
        ProjectRepository.getInstance().update(p1);
    }

    /**
     * allocate a project
     * @param projectID the ID of the project
     * @param studentID the ID of the student
     * @throws ModelNotFoundException if the project is not found
     */
    public static void allocateProject(String projectID, String studentID) throws ModelNotFoundException {
        Project p1 = ProjectRepository.getInstance().getByID(projectID);
        p1.setStudentID(studentID);
        ProjectRepository.getInstance().update(p1);
    }

    public static void generateProjectDetails() throws ModelNotFoundException {
        System.out.println("1. By ProjectID");
        System.out.println("1. By SupervisorID");
        System.out.println("1. By Student");
        System.out.println("1. By Status");
        Scanner sc=new Scanner(System.in);
        int c;
        int ctrl=1;
        while (ctrl==1) {
            c=sc.nextInt();
            switch (c) {
                case 1 -> {
                    generateDetailsByProjectID();
                    ctrl = 0;
                }
                case 2 -> {
                    generateDetailsBySupervisorID();
                    ctrl = 0;
                }
                case 3 -> {
                    generateDetailsByStudentID();
                    ctrl = 0;
                }
                case 4 -> {
                    generateDetailsByStatus();
                    ctrl = 0;
                }
                default -> System.out.println("Invalid choice. Enter again");
            }
        }
    }

    public static void generateDetailsByProjectID() throws ModelNotFoundException{
        System.out.println("Enter the ProjectID to search");
        String s1 = new Scanner(System.in).next();
        Project p1=ProjectRepository.getInstance().getByID(s1);
        p1.displayProject();
    }
    public static void generateDetailsBySupervisorID() throws ModelNotFoundException{
        System.out.println("Enter the SupervisorID to search");
        String s1 = new Scanner(System.in).next();
        for(Project p:ProjectRepository.getInstance().findByRules(p->p.getSupervisorID()==s1))
            p.displayProject();
    }
    public static void generateDetailsByStudentID() throws ModelNotFoundException{
        System.out.println("Enter the StudentID to search");
        String s1 =  new Scanner(System.in).next();
        for(Project p:ProjectRepository.getInstance().findByRules(p->p.getStudentID()==s1))
            p.displayProject();
    }

    public static void generateDetailsByStatus() throws ModelNotFoundException{
        System.out.println("Enter the Status to search");
        String s1 = new Scanner(System.in).next();
        for(Project p:ProjectRepository.getInstance().findByRules(p->p.getStatus()==ProjectStatus.valueOf(s1)))
            p.displayProject();
    }



}
