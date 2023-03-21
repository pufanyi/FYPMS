package utils.iocontrol;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;

public abstract class Saveable<MappableObject extends Mappable> {
    /**
     * Gets the list of mappable objects.
     *
     * @return the list of mappable objects
     */
    public abstract List<MappableObject> getListOfMappableObjects();

    /**
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    public abstract void setListOfMappableObjects(List<Map<String, String>> listOfMappableObjects);

    /**
     * Saves the list of mappable objects to a file.
     *
     * @param FILE_PATH the path of the file to save to
     * @throws IOException if you cannot write to the file
     */
    public void save(final String FILE_PATH) throws IOException {
        PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_PATH));
        final List<MappableObject> listOfMappableObjects = getListOfMappableObjects();
        for (MappableObject mappableObject : listOfMappableObjects) {
            printWriter.println(StringAndMapConvertor.toString(mappableObject.toMap()));
        }
    }

    /**
     * Loads the list of mappable objects from a file.
     *
     * @param FILE_PATH the path of the file to load from
     * @throws IOException if you cannot read from the file
     */
    public void load(final String FILE_PATH) throws IOException {
        List<Map<String, String>> listOfMappableObjects = new ArrayList<>();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            listOfMappableObjects.add(StringAndMapConvertor.toMap(line));
        }
        setListOfMappableObjects(listOfMappableObjects);
    }
}
