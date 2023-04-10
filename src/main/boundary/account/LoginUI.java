package main.boundary.account;

import main.boundary.mainpage.CoordinatorMainPage;
import main.boundary.mainpage.StudentMainPage;
import main.boundary.mainpage.SupervisorMainPage;
import main.controller.account.AccountManager;
import main.model.user.User;
import main.model.user.UserType;
import main.utils.exception.model.PasswordIncorrectException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.util.Scanner;

/**
 * This class provides a user interface (UI) for the user to login.
 */
public class LoginUI {
    /**
     * Displays a login page.
     *
     * @throws PageBackException if the user chooses to go back to the previous page.
     */
    public static void login() throws PageBackException {
        ChangePage.changePage();
        UserType domain = AttributeGetter.getDomain();
        String userID = AttributeGetter.getUserID();
        if (userID.equals("")) {
            try {
                ForgetUserID.forgotUserID();
            } catch (PageBackException e) {
                login();
            }
        }
        String password = AttributeGetter.getPassword();
//        System.err.println("Logging in...");
//        System.err.println("Domain: " + domain);
//        System.err.println("User ID: " + userID);
//        System.err.println("Password: " + password);
//        new Scanner(System.in).nextLine();
        try {
            User user = AccountManager.login(domain, userID, password);
            switch (domain) {
                case STUDENT -> StudentMainPage.studentMainPage(user);
                case FACULTY -> SupervisorMainPage.supervisorMainPage(user);
                case COORDINATOR -> CoordinatorMainPage.coordinatorMainPage(user);
                default -> throw new IllegalStateException("Unexpected value: " + domain);
            }
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
