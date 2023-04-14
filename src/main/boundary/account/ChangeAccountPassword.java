package main.boundary.account;

import main.controller.account.AccountManager;
import main.model.user.UserType;
import main.utils.exception.PasswordIncorrectException;
import main.utils.exception.ModelNotFoundException;
import main.utils.exception.PageBackException;
import main.utils.ui.ChangePage;
import main.utils.ui.PasswordReader;
import main.utils.ui.UserTypeGetter;

import java.util.Scanner;

/**
 * This class is responsible for handling the change password feature for a given user account. It contains
 * two methods, {@code changePassword} and {@code askToRetry}, that are used for this purpose.
 */
public class ChangeAccountPassword {
    /**
     * This method is called when the user enters an incorrect password and wants to retry the password change. It
     * prompts the user to either go back or try again, and throws a {@code PageBackException} if the user chooses to
     * go back.
     *
     * @param userType the type of user who wants to change their password
     * @param userID   the ID of the user who wants to change their password
     * @throws PageBackException if the user chooses to go back
     */
    public static void askToRetry(UserType userType, String userID) throws PageBackException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter [b] to go back, or any other key to try again.");
        String choice = scanner.nextLine();
        if (choice.equals("b")) {
            throw new PageBackException();
        } else {
            System.out.println("Please try again.");
            changePassword(userType, userID);
        }
    }

    /**
     * This method is called to change the password for a given user account. It prompts the user for their old password
     * and verifies it, then prompts the user for their new password and verifies that it is entered correctly. If
     * successful, it updates the user's password and displays a success message before throwing a
     * {@code PageBackException} to return to the previous page.
     *
     * @param userType the type of user who wants to change their password
     * @param userID   the ID of the user who wants to change their password
     * @throws PageBackException if the user chooses to go back
     */
    public static void changePassword(UserType userType, String userID) throws PageBackException {
        ChangePage.changePage();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Change " + UserTypeGetter.getUserTypeInCamelCase(userType) + " Password");
        System.out.print("Please enter your old password: ");
        String oldPassword = PasswordReader.getPassword();

        try {
            AccountManager.login(userType, userID, oldPassword);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        } catch (PasswordIncorrectException e) {
            System.out.println("Password incorrect.");
            askToRetry(userType, userID);
        }

        String newPassword;
        String newPasswordAgain;

        do {
            // read the new password
            System.out.print("Please enter your new password: ");
            newPassword = PasswordReader.getPassword();
            // read the new password again
            System.out.print("Please enter your new password again: ");
            newPasswordAgain = PasswordReader.getPassword();
            // if the new password is not the same as the new password again, ask the user to enter again

            if (!newPassword.equals(newPasswordAgain)) {
                System.out.println("Two passwords are not the same.");
                askToRetry(userType, userID);
            } else if (newPassword.equals(oldPassword)) {
                System.out.println("New password cannot be the same as the old password.");
                askToRetry(userType, userID);
            }
        } while (!newPassword.equals(newPasswordAgain));

        if (newPassword.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            askToRetry(userType, userID);
        }

        try {
            AccountManager.changePassword(userType, userID, oldPassword, newPassword);

            System.out.println("Password changed successfully.");

            System.out.println("Press [Enter] to go back to the main page.");
            scanner.nextLine();
            throw new PageBackException();
        } catch (PasswordIncorrectException | ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
