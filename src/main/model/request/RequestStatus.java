package main.model.request;

/**
 * This enum represents the status of a request
 */
public enum RequestStatus {
    /**
     * The request is approved
     */
    APPROVED,
    /**
     * The request is denied
     */
    DENIED,
    /**
     * The request is pending
     */
    PENDING;

    /**
     * This method returns a string representation of the status with colors
     * @return The string representation of the status with colors
     */
    public String showColorfulStatus() {
        return switch (this) {
            case APPROVED -> "\u001B[32m" + this + "\u001B[0m"; // green
            case DENIED -> "\u001B[31m" + this + "\u001B[0m"; // red
            case PENDING -> "\u001B[34m" + this + "\u001B[0m"; // blue
        };
    }
}
