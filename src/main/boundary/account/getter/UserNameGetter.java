package main.boundary.account.getter;

import java.util.Scanner;

public class UserNameGetter {
    public static String getUserName() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter your username: ");
        return scanner.nextLine();
    }
}
