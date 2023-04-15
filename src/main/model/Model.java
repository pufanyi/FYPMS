package main.model;

import main.utils.iocontrol.Mappable;

/**

 The Model interface represents an entity that can be stored in the application's data storage.
 It extends the Mappable interface, which allows it to be serialized and deserialized as a map of key-value pairs.
 The interface requires implementations to provide a unique ID string and defines how the ID can be retrieved.
 */
public interface Model extends Mappable {
    /**
     * Returns the ID of the model as a string.
     *
     * @return the ID of the model.
     */
    String getID();
}
