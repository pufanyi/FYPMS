package main.utils.iocontrol;

/**
 * A class for mapping characters to control the output of objects.
 */
public record ObjectOutputControlCharacters() {

    /**
     * The character to be used to separate the key and value of a map entry.
     */
    public static final String DELIMITER_STRING = "\u001B\u001B\u001B";

    /**
     * The character to be used to separate features of an object.
     */
    public static final String SEPARATOR_STRING = "\u001A\u001A\u001A";
}
