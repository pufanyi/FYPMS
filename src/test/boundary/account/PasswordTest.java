package test.boundary.account;

import main.boundary.account.AttributeGetter;

public class PasswordTest {
    public static void main(String[] args) {
        String password = AttributeGetter.getPassword();
        System.out.println("Password: " + password);
    }
}
