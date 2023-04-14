package main.utils.exception;

/**
 * The PasswordIncorrectException class represents an exception that is thrown when a password entered by the user is incorrect.
 * It extends the base Exception class.
 */
public class PasswordIncorrectException extends Exception {

    /**
     * Constructs a new PasswordIncorrectException object with a default message.
     * The default message is "Password is incorrect".
     */
    public PasswordIncorrectException() {
        super("Password is incorrect");
    }
}