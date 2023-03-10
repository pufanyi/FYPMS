package project;

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
    ALLOCATED
}
