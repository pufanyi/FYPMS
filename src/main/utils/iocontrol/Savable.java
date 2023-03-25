package main.utils.iocontrol;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Savable<MappableObject extends Mappable> {
    /**
     * Gets the list of mappable objects.
     *
     * @return the list of mappable objects
     */
    public abstract List<MappableObject> getAll();

    /**
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    public abstract void setAll(List<Map<String, String>> listOfMappableObjects);

    /**
     * Saves the list of mappable objects to a file.
     *
     * @param FILE_PATH the path of the file to save to
     */
    public void save(final String FILE_PATH) throws IOException {
        ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(FILE_PATH));
        final List<MappableObject> listOfMappableObjects = getAll();
        for (MappableObject mappableObject : listOfMappableObjects) {
            objectOutput.writeObject(mappableObject.toMap());
        }
        objectOutput.close();
    }

    /**
     * Loads the list of mappable objects from a file.
     *
     * @param FILE_PATH the path of the file to load from
     */
    public void load(final String FILE_PATH) throws IOException {
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(FILE_PATH));
        List<Map<String, String>> listOfMappableObjects = new ArrayList<>();
        try {
            while (true) {
                Map<String, String> map = (Map<String, String>) objectInputStream.readObject();
                listOfMappableObjects.add(map);
            }
        } catch (EOFException e) {
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
