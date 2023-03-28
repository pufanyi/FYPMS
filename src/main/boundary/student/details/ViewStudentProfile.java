package main.boundary.student.details;

import main.model.user.Student;
import main.utils.ui.ChangePage;

public class ViewStudentProfile {
    public static void viewStudentProfile(Student student) {
        ChangePage.changePage();
        System.out.println("Welcome to View Student Profile");
        System.out.println("Name: " + student.getUserName());
        System.out.println("Email: " + student.getEmail());
        System.out.println("Student ID: " + student.getID());

    }
}
