package test.boundary.account;

import main.boundary.account.getter.PasswordGetter;

public class PasswordTest {
    public static void main(String[] args) {
        String password = PasswordGetter.getPassword();
        System.out.println("Password: " + password);
    }
}
