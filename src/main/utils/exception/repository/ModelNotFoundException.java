package main.utils.exception.repository;

public class ModelNotFoundException extends Exception {
    public ModelNotFoundException() {
        super("Model not found");
    }

    public ModelNotFoundException(String message) {
        super(message);
    }
}
