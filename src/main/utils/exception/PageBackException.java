package main.utils.exception;

/**
 * The {@link PageBackException} class is a custom exception that is thrown when a user attempts to navigate back from a UI page
 * where it is not allowed.
 * It extends the {@link Exception} class.
 */
public class PageBackException extends Exception {

    /**
     * Creates a new instance of the {@link PageBackException} class with a default error message.
     * The default message is "Page back".
     */
    public PageBackException() {
        super("Page back");
    }
}
