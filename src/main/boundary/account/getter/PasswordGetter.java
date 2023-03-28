package main.boundary.account.getter;

import main.utils.ui.PasswordReader;

public class PasswordGetter {
    /**
     * Prompts the user to enter their password.
     * When the user enters their password, it does not show up on the screen.
     *
     * @return The password entered by the user.
     */
    public static String getPassword() {
        System.out.print("Please enter your password (Contact administrator if you forget): ");
        return PasswordReader.getPassword();
    }
}
