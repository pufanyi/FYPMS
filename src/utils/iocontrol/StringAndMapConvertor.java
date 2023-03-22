package utils.iocontrol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StringAndMapConvertor {
    private static final String DELIMITER = "=";
    private static final String SEPARATOR = ";";

    public static Map<String, String> stringToMap(String string) {
        Map<String, String> map = new HashMap<>();
        String[] pairs = string.split(SEPARATOR);
        for (String pair : pairs) {
            String[] keyValue = pair.split(DELIMITER);
            map.put(keyValue[0], keyValue[1]);
        }
        return map;
    }

    public static String mapToString(Map<String, String> map) {
        List<String> pairs = new ArrayList<>();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            pairs.add(entry.getKey() + DELIMITER + entry.getValue());
        }
        return String.join(SEPARATOR, pairs);
    }
}
