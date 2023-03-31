package main.boundary.account;

import main.model.user.UserType;
import main.utils.ui.BoundaryStrings;
import main.utils.ui.PasswordReader;

import java.util.Scanner;

public class AttributeGetter {
    public static UserType getDomain() {
        System.out.println(BoundaryStrings.separator);
        System.out.println("\t1. Student");
        System.out.println("\t2. Supervisor");
        System.out.println("\t3. FYP Coordinator");
        System.out.println(BoundaryStrings.separator);
        System.out.print("Please select your domain (1-3): ");
        UserType userType = null;
        while (userType == null) {
            Scanner scanner = new Scanner(System.in);
            int domain = scanner.nextInt();
            userType = switch (domain) {
                case 1 -> UserType.STUDENT;
                case 2 -> UserType.FACULTY;
                case 3 -> UserType.COORDINATOR;
                default -> null;
            };
            if (userType == null) {
                System.out.println("Invalid domain. Please try again.");
            }
        }
        return userType;
    }

    /**
     * Prompts the user to enter their password.
     * When the user enters their password, it does not show up on the screen.
     *
     * @return The password entered by the user.
     */
    public static String getPassword() {
        System.out.print("Please enter your password: ");
        return PasswordReader.getPassword();
    }

    public static String getUserID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your UserID (Press enter if you forget): ");
        return scanner.nextLine();
    }

    public static String getUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        return scanner.nextLine();
    }
}
