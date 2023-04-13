package main.boundary.welcome;

import main.utils.ui.ChangePage;

import static main.utils.ui.BoundaryStrings.BYE_LOGO;

/**
 * This class provides a UI for the user to exit the system.
 */
public class ExitPage {
    /**
     * Displays an exit page.
     */
    public static void exitPage() {
        ChangePage.changePage();
        System.out.println("Thank you for using our system!");
        System.out.println(BYE_LOGO);
        System.exit(0);
    }
}
