package main.utils.exception.user;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException() {
        super("Password is incorrect");
    }
}
