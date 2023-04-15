package test.boundary;

import main.boundary.account.AttributeGetter;

/**

 A test class that retrieves the password from user input using {@link main.boundary.account.AttributeGetter}
 and prints it to the console.
 */
public class PasswordTest {
    public static void main(String[] args) {
        String password = AttributeGetter.getPassword();
        System.out.println("Password: " + password);
    }
}
