package main.utils.exception.repository;

public class ModelAlreadyExistsException extends Exception {
    public ModelAlreadyExistsException() {
        super("Model already exists");
    }

    public ModelAlreadyExistsException(String message) {
        super(message);
    }
}
