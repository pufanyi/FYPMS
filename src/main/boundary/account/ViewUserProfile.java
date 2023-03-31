package main.boundary.account;

import main.model.user.Coordinator;
import main.model.user.Student;
import main.model.user.Supervisor;
import main.model.user.User;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.util.Scanner;

public class ViewUserProfile {
    private static String getUserType(User user) {
        if (user instanceof Supervisor) {
            return "Supervisor";
        } else if (user instanceof Student) {
            return "Student";
        } else if (user instanceof Coordinator) {
            return "Coordinator";
        } else {
            return "Unknown";
        }
    }
    public static void viewUserProfile(User user) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Welcome to View " + getUserType(user) + " Profile");
        System.out.println("┌---------------------------------------------------------------┐");
        System.out.printf("| %-15s | %-30s | %-10s |\n", "Name", "Email", "Student ID");
        System.out.println("|-----------------|--------------------------------|------------|");
        System.out.printf("| %-15s | %-30s | %-10s |\n", user.getUserName(), user.getEmail(), user.getID());
        System.out.println("└---------------------------------------------------------------┘");
        System.out.println("Press enter to go back.");
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
        throw new PageBackException();
    }
}