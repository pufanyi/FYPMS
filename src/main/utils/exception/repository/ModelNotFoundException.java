package main.utils.exception.repository;

/**
 * The {@link ModelNotFoundException} class is a custom exception that is thrown when a requested model cannot be found
 * in a repository.
 * It extends the {@link Exception} class.
 */
public class ModelNotFoundException extends Exception {

    /**
     * Creates a new instance of the {@link ModelNotFoundException} class with a default error message.
     * The default message is "Model not found".
     */
    public ModelNotFoundException() {
        super("Model not found");
    }

    /**
     * Creates a new instance of the {@link ModelNotFoundException} class with a custom error message.
     *
     * @param message The custom error message to be used.
     */
    public ModelNotFoundException(String message) {
        super(message);
    }
}
