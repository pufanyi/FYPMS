package main.boundary.account;

import main.boundary.BoundaryStrings;
import main.boundary.account.getter.DomainGetter;
import main.boundary.account.getter.PasswordGetter;
import main.boundary.account.getter.UserIDGetter;
import main.controller.account.AccountManager;
import main.model.user.User;
import main.model.user.UserType;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;

import java.util.Scanner;

public class LoginUI {
    public static void login() throws PageBackException {
        System.out.println(BoundaryStrings.separator);
        UserType domain = DomainGetter.getDomain();
        String userID = UserIDGetter.getUserID();
        String password = PasswordGetter.getPassword();
        try {
            User user = AccountManager.login(domain, userID, password);
            // TODO: redirect to the correct page
            System.err.println("TODO: redirect to the correct page");
            return;
        } catch (PasswordIncorrectException e) {
            System.out.println("Password incorrect.");
        } catch (ModelNotFoundException e) {
            System.out.println("User not found.");
        }
        System.out.println("Enter [b] to go back, or any other key to try again.");
        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine();
        if (choice.equals("b")) {
            throw new PageBackException();
        } else {
            System.out.println("Please try again.");
            login();
        }
    }
}
