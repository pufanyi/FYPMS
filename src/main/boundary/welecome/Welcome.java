package main.boundary.welecome;

import main.utils.ui.BoundaryStrings;
import main.boundary.account.ForgotUserID;
import main.boundary.account.LoginUI;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.util.Scanner;

public class Welcome {
    public static void welcome() {
        ChangePage.changePage();
        System.out.println(BoundaryStrings.separator);
        System.out.println("Welcome to the Final Year Project Management System!");
        System.out.println(BoundaryStrings.logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println(BoundaryStrings.separator);
        System.out.println("Please enter your choice to continue.");
        System.out.println("\t1. Login");
        System.out.println("\t2. Forget username");
        System.out.println("\t3. Exit");
        System.out.print("Your choice (1-3): ");
        try {
            while (true) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> LoginUI.login();
                    case 2 -> ForgotUserID.forgotUserID();
                    case 3 -> System.exit(0);
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (PageBackException e) {
            welcome();
        }
    }
}
