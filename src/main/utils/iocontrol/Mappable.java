package main.utils.iocontrol;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class Mappable {
    /**
     * Converts the object to a map
     *
     * @return the map
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new HashMap<>();
        Field[] fields = getClass().getDeclaredFields();
        for (Field field : fields) {
            try {
                field.setAccessible(true);
                map.put(field.getName(), field.get(this).toString());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    /**
     * Converts the map to an object
     *
     * @param map the map
     */
    public void fromMap(Map<String, String> map) {
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

    public String toString() {
        return toMap().toString();
    }
}
