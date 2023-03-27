package main.utils.exception.model;

public class PasswordIncorrectException extends Exception {
    public PasswordIncorrectException() {
        super("Password is incorrect");
    }
}
