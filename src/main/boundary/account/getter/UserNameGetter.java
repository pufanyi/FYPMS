package main.boundary.account.getter;

import java.util.Scanner;

public class UserNameGetter {
    public static String getUserName(boolean isRegistering) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        String userName = scanner.nextLine();
        if (isRegistering) {
            System.out.println("Username: " + userName);
            System.out.print("Is this correct? (Y/N): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equals("Y")) {
                return userName;
            } else {
                return getUserName(true);
            }
        } else {
            return userName;
        }
    }
}
