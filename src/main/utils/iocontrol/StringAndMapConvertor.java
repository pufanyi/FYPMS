package main.utils.iocontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static main.utils.iocontrol.ObjectOutputControlCharacters.DELIMITER_STRING;
import static main.utils.iocontrol.ObjectOutputControlCharacters.SEPARATOR_STRING;

public class StringAndMapConvertor {
    public static Map<String, String> stringToMap(String string) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = string.split(SEPARATOR_STRING);
        for (String pair : pairs) {
            String[] keyValue = pair.split(DELIMITER_STRING);
            if (keyValue.length != 2) {
                throw new IllegalArgumentException("Invalid key-value pair: " + pair);
            }
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    public static String mapToString(Map<String, String> map) {
        List<String> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            pairs.add(entry.getKey() + DELIMITER_STRING + entry.getValue());
        }
        return String.join(SEPARATOR_STRING, pairs);
    }
}
