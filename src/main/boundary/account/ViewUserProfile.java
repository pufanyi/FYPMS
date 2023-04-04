package main.boundary.account;

import main.model.user.User;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;
import main.utils.ui.UserTypeGetter;

import java.util.Scanner;

/**
 * This class provides a UI for the user to view his/her profile.
 */
public class ViewUserProfile {
    /**
     * Displays the user's profile.
     *
     * @param user the user whose profile is to be displayed.
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
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