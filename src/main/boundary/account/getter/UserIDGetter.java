package main.boundary.account.getter;

import java.util.Scanner;

public class UserIDGetter {
    public static String getUserID() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your user ID (Press enter if you forget): ");
        return scanner.nextLine();
    }
}
