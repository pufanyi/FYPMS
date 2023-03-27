package main.controller.account.password;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException() {
        super("Password is incorrect");
    }
}
