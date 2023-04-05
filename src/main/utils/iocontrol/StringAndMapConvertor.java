package main.utils.iocontrol;

import java.util.*;

import static main.utils.iocontrol.ObjectOutputControlCharacters.DELIMITER_STRING;
import static main.utils.iocontrol.ObjectOutputControlCharacters.SEPARATOR_STRING;

/**
 * The StringAndMapConvertor class provides methods to convert a String representation of a map to a Map object,
 * and vice versa.
 */
public class StringAndMapConvertor {

    /**
     * Converts a String representation of a map to a Map object.
     *
     * @param string The String representation of the map.
     * @return A Map object containing the key-value pairs from the String representation.
     * @throws IllegalArgumentException if the input string contains invalid key-value pairs.
     */
    public static Map<String, String> stringToMap(String string) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = string.split(SEPARATOR_STRING);
        for (String pair : pairs) {
            String[] keyValue = pair.split(DELIMITER_STRING);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Invalid key-value pair: " + pair + " in string: " + Arrays.toString(keyValue));
            }
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    /**
     * Converts a Map object to a String representation of the map.
     *
     * @param map The Map object to convert.
     * @return A String representation of the map.
     */
    public static String mapToString(Map<String, String> map) {
        List<String> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            pairs.add(entry.getKey() + DELIMITER_STRING + entry.getValue());
        }
        return String.join(SEPARATOR_STRING, pairs);
    }
}
