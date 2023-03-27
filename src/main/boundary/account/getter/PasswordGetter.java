package main.boundary.account.getter;

import java.util.Scanner;

public class PasswordGetter {
    /**
     * Prompts the user to enter their password.
     * When the user enters their password, it does not show up on the screen.
     *
     * @return The password entered by the user.
     */
    public static String getPassword() {
        System.out.print("Enter your password: ");
        String password = null;
        if (System.console() == null) {
            Scanner scanner = new Scanner(System.in);
            password = scanner.nextLine();
        } else {
            char[] passwordChars = System.console().readPassword();
            password = new String(passwordChars);
        }
        return password;
    }
}
