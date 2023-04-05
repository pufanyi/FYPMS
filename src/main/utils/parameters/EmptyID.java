package main.utils.parameters;

public class EmptyID {
    public static final String EMPTY_ID = "null";

    public static boolean isEmptyID(String ID) {
        return ID == null || ID.isBlank() || ID.equalsIgnoreCase(EMPTY_ID);
    }
}
