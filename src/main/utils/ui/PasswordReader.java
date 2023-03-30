package main.utils.ui;

import java.util.Scanner;

/**
 * The PasswordReader class is responsible for reading a password from the console.
 * It provides a method to read the password securely from the console, and returns the password as a String.
 */
public class PasswordReader {

    /**
     * Reads a password from the console.
     * If the console is not available, the password is read using a Scanner from System.in.
     * Otherwise, the password is read securely using System.console().readPassword().
     *
     * @return The password read from the console as a String.
     */
    public static String getPassword() {
        String password;
        if (System.console() == null) {
            password = new Scanner(System.in).nextLine();
        } else {
            password = new String(System.console().readPassword());
        }
        return password;
    }
}
