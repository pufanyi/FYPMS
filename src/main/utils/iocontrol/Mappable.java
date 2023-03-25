package main.utils.iocontrol;

import java.util.Map;

public abstract class Mappable {
    /**
     * Converts the object to a map
     * @return the map
     */
    abstract public Map<String, String> toMap();

    /**
     * Converts the map to an object
     * @param map the map
     */
    abstract public void fromMap(Map<String, String> map);

    public String toString() {
        return toMap().toString();
    }
}
