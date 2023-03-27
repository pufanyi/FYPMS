package main.boundary.account.getter;

public class PasswordGetter {
    /**
     * Prompts the user to enter their password.
     * When the user enters their password, it does not show up on the screen.
     *
     * @return The password entered by the user.
     */
    public static String getPassword() {
        System.out.print("Enter your password: ");
        char[] passwordChars = System.console().readPassword();
        return new String(passwordChars);
    }
}
