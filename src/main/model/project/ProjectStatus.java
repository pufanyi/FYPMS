package main.model.project;

/**
 * This enum represents the status of a project
 */
public enum ProjectStatus {
    /**
     * The project is available for allocation
     */
    AVAILABLE,
    /**
     * The project is reserved for a student
     */
    RESERVED,
    /**
     * The project is unavailable for allocation
     */
    UNAVAILABLE,
    /**
     * The project is allocated to a student
     */
    ALLOCATED;

    public String showColorfulString() {
        return switch (this) {
            case AVAILABLE -> "\u001B[32m" + this + "\u001B[0m"; // green
            case RESERVED -> "\u001B[33m" + this + "\u001B[0m"; // yellow
            case UNAVAILABLE -> "\u001B[31m" + this + "\u001B[0m"; // red
            case ALLOCATED -> "\u001B[34m" + this + "\u001B[0m"; // blue
        };
    }
}
