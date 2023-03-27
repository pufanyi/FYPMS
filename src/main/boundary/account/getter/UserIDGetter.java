package main.boundary.account.getter;

import java.util.Scanner;

public class UserIDGetter {
    public static String getUserID(boolean isRegistering) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your user ID: ");
        String userID = scanner.nextLine();
        if (isRegistering) {
            System.out.println("User ID: " + userID);
            System.out.print("Is this correct? (Y/N): ");
            String confirmation = scanner.nextLine();
            if (confirmation.equals("Y")) {
                return userID;
            } else {
                return getUserID(true);
            }
        } else {
            return userID;
        }
    }
}
