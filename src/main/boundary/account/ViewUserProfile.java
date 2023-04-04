package main.boundary.account;

import main.model.user.User;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;
import main.utils.ui.UserTypeGetter;

import java.util.Scanner;

public class ViewUserProfile {
    public static void viewUserProfile(User user) throws PageBackException {
        ChangePage.changePage();
        System.out.println("Welcome to View " + UserTypeGetter.getUserTypeInCamelCase(user) + " Profile");
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