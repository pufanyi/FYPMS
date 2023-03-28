package main.utils.iocontrol;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Savable<MappableObject extends Mappable> {
    /**
     * Gets the list of mappable objects.
     *
     * @return the list of mappable objects
     */
    protected abstract List<MappableObject> getAll();

    /**
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    protected abstract void setAll(List<Map<String, String>> listOfMappableObjects) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException;

    /**
     * Saves the list of mappable objects to a file.
     *
     * @param FILE_PATH the path of the file to save to
     */
    protected void save(final String FILE_PATH) {
        try (PrintWriter printWriter = new PrintWriter(new FileWriter(FILE_PATH))) {
            final List<MappableObject> listOfMappableObjects = getAll();
            for (MappableObject mappableObject : listOfMappableObjects) {
                printWriter.println(StringAndMapConvertor.mapToString(mappableObject.toMap()));
            }
        } catch (IOException e) {
            throw new RuntimeException("Data could not be saved to file: " + FILE_PATH);
        }
    }

    /**
     * Loads the list of mappable objects from a file.
     *
     * @param FILE_PATH the path of the file to load from
     */
    protected void load(final String FILE_PATH) {
        List<Map<String, String>> listOfMappableObjects = new ArrayList<>();
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new FileReader(FILE_PATH));
        } catch (FileNotFoundException e) {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();
            if (parent != null && !parent.exists()) {
                parent.mkdirs();
            }
            try {
                file.createNewFile();
                bufferedReader = new BufferedReader(new FileReader(file));
            } catch (IOException ex) {
                throw new RuntimeException("Data could not be loaded from file: " + FILE_PATH);
            }
        }
        String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                listOfMappableObjects.add(StringAndMapConvertor.stringToMap(line));
            }
        } catch (IOException e) {
            throw new RuntimeException("Data could not be loaded from file: " + FILE_PATH);
        }
        try {
            setAll(listOfMappableObjects);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException("Data could not be loaded from file: " + FILE_PATH);
        }
    }
}