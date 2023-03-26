package main.utils.iocontrol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public interface Mappable {
    /**
     * Converts the object to a map
     *
     * @return the map
     */
    default Map<String, String> toMap() {
        System.err.println("in");
        Map<String, String> map = new HashMap<>();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            System.err.println(field.getName() + " in");
            try {
                field.setAccessible(true);
                try {
                    map.put(field.getName(), field.get(this).toString());
                } catch (NullPointerException e) {
                    map.put(field.getName(), null);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            System.err.println(field.getName() + " out");
        }
        System.err.println("out");
        return map;
    }

    /**
     * Converts the map to an object
     *
     * @param map the map
     */
    default void fromMap(Map<String, String> map) {
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                field.set(this, map.get(field.getName()));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
