package main.boundary.account;

import main.boundary.welcome.Welcome;

/**
 * This class provides a UI for the user to logout.
 */
public class Logout {
    /**
     * Displays a logout page.
     */
    public static void logout() {
        Welcome.welcome();
        System.exit(0);
    }
}
