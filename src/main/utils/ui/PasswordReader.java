package main.utils.ui;

import java.util.Scanner;

public class PasswordReader {
    public static String getPassword() {
        String password = null;
        if (System.console() == null) {
            password = new Scanner(System.in).nextLine();
        } else {
            password = new String(System.console().readPassword());
        }
        return password;
    }
}
