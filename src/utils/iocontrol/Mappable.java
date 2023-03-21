package utils.iocontrol;

import java.util.Map;

public interface Mappable {
    /**
     * Converts the object to a map
     * @return the map
     */
    Map<String, String> toMap();

    /**
     * Converts the map to an object
     * @param map the map
     */
    void fromMap(Map<String, String> map);
}
