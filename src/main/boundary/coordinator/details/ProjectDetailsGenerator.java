package main.boundary.coordinator.details;

import main.model.project.Project;
import main.model.project.ProjectStatus;
import main.repository.project.ProjectRepository;
import main.utils.exception.repository.ModelNotFoundException;

import java.util.Scanner;

public class ProjectDetailsGenerator {
    /**
     * generate details of project by ProjectID
     *
     * @throws ModelNotFoundException
     */
    public static void generateDetailsByProjectID() throws ModelNotFoundException {
        System.out.println("Enter the ProjectID to search");
        String s1 = new Scanner(System.in).next();
        Project p1 = ProjectRepository.getInstance().getByID(s1);
        p1.displayProject();
    }

    /**
     * generate details of project by SupervisorID
     *
     * @throws ModelNotFoundException
     */
    public static void generateDetailsBySupervisorID() throws ModelNotFoundException {
        System.out.println("Enter the SupervisorID to search");
        String s1 = new Scanner(System.in).next();
        for (Project p : ProjectRepository.getInstance().findByRules(p -> p.getSupervisorID() == s1))
            p.displayProject();
    }

    /**
     * generate details of project by StudentID
     *
     * @throws ModelNotFoundException
     */
    public static void generateDetailsByStudentID() throws ModelNotFoundException {
        System.out.println("Enter the StudentID to search");
        String s1 = new Scanner(System.in).next();
        for (Project p : ProjectRepository.getInstance().findByRules(p -> p.getStudentID() == s1))
            p.displayProject();
    }

    /**
     * generate details of project by Status
     *
     * @throws ModelNotFoundException if the model object with the given ID does not exist
     */
    public static void generateDetailsByStatus() throws ModelNotFoundException {
        System.out.println("Enter the Status to search");
        String s1 = new Scanner(System.in).next();
        for (Project p : ProjectRepository.getInstance().findByRules(p -> p.getStatus() == ProjectStatus.valueOf(s1)))
            p.displayProject();
    }


    /**
     * generate details of project
     *
     * @throws ModelNotFoundException
     */
    public static void generateProjectDetails() throws ModelNotFoundException {
        System.out.println("1. By ProjectID");
        System.out.println("1. By SupervisorID");
        System.out.println("1. By Student");
        System.out.println("1. By Status");
        Scanner sc = new Scanner(System.in);
        int c;
        int ctrl = 1;
        while (ctrl == 1) {
            c = sc.nextInt();
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

}
