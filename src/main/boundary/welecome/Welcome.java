package main.boundary.welecome;

import main.boundary.BoundaryStrings;
import main.boundary.account.LoginUI;
import main.utils.exception.ui.PageBackException;

import java.util.Scanner;

public class Welcome {
    public static void welcome() {
        System.out.println("Welcome to the Final Year Project Management System!");
        System.out.println(BoundaryStrings.logo);
        Scanner scanner = new Scanner(System.in);
        System.out.println(BoundaryStrings.separator);
        System.out.println("Please login to continue.");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Exit");
        try {
            while (true) {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> LoginUI.login();
                    case 2 -> RegisterUI.register();
                    case 3 -> System.exit(0);
                    default -> System.out.println("Invalid choice. Please try again.");
                }
            }
        } catch (PageBackException e) {
            welcome();
        }
    }
}
