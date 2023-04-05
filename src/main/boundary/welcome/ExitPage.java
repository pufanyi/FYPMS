package main.boundary.welcome;

import main.utils.ui.ChangePage;

public class ExitPage {
    public static void exitPage() {
        ChangePage.changePage();
        System.out.println("Thank you for using our system!");
        System.exit(0);
    }
}
