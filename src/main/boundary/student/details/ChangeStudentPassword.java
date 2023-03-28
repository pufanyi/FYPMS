package main.boundary.student.details;

import main.boundary.account.getter.PasswordGetter;
import main.controller.account.AccountManager;
import main.model.user.Student;
import main.model.user.UserType;
import main.repository.user.StudentRepository;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.PasswordReader;
import test.controller.AccountManagerTest;

import java.util.Scanner;

public class ChangeStudentPassword {
    public static void askToRetry(String userID) throws PageBackException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter [b] to go back, or any other key to try again.");
        String choice = scanner.nextLine();
        if (choice.equals("b")) {
            throw new PageBackException();
        } else {
            System.out.println("Please try again.");
            changeStudentPassword(userID);
        }
    }

    public static void changeStudentPassword(String userID) throws PageBackException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to Change Student Password");
        System.out.println("Please enter your old password: ");
        String oldPassword = PasswordReader.getPassword();

        try {
            AccountManager.login(UserType.STUDENT, userID, oldPassword);
        } catch (ModelNotFoundException e) {
            throw new RuntimeException(e);
        } catch (PasswordIncorrectException e) {
            System.out.println("Password incorrect.");
            askToRetry(userID);
        }

        String newPassword;
        String newPasswordAgain;

        do {
            // read the new password
            System.out.println("Please enter your new password: ");
            newPassword = PasswordReader.getPassword();
            // read the new password again
            System.out.println("Please enter your new password again: ");
            newPasswordAgain = PasswordReader.getPassword();
            // if the new password is not the same as the new password again, ask the user to enter again

            if (!newPassword.equals(newPasswordAgain)) {
                System.out.println("Two passwords are not the same.");
                askToRetry(userID);
            } else if (newPassword.equals(oldPassword)) {
                System.out.println("New password cannot be the same as the old password.");
                askToRetry(userID);
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
