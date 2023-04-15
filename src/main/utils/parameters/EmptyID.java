package main.utils.parameters;

/**

 The EmptyID class provides a utility method for checking if an ID is empty.
 */
public class EmptyID {
    /**
     * The constant EMPTY_ID represents the empty ID value, which is "null".
     */
    public static final String EMPTY_ID = "null";

    /**
     * Returns true if the specified ID is empty or null, otherwise false.
     *
     * @param ID the ID to be checked
     * @return true if the specified ID is empty or null, otherwise false
     */
    public static boolean isEmptyID(String ID) {
        return ID == null || ID.isBlank() || ID.equalsIgnoreCase(EMPTY_ID);
    }
}
