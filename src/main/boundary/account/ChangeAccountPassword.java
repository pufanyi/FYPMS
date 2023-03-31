package main.boundary.account;

import main.controller.account.AccountManager;
import main.model.user.UserType;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.PasswordReader;

import java.util.Scanner;

public class ChangeAccountPassword {
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

    public static void changePassword(UserType userType, String userID) throws PageBackException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Change Student Password");
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

        try {
            AccountManager.changePassword(UserType.STUDENT, userID, oldPassword, newPassword);

            System.out.println("Password changed successfully.");
            throw new PageBackException();
        } catch (PasswordIncorrectException | ModelNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
