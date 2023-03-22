package repository;

import model.Model;
import utils.iocontrol.Savable;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Repository<ModelObject extends Model> extends Savable<ModelObject> implements Iterable<ModelObject> {
    public abstract String getFilePath();

    List<ModelObject> listOfModelObjects;

    public Repository() {
        super();
        listOfModelObjects = new ArrayList<>();
    }

    /**
     * Gets the list of mappable objects.
     *
     * @return the list of mappable objects
     */
    @Override
    public List<ModelObject> getAll() {
        return listOfModelObjects;
    }

    public ModelObject getByID(String modelObjectID) throws NoSuchElementException {
        for (ModelObject modelObject : listOfModelObjects) {
            if (modelObject.getID().equals(modelObjectID)) {
                return modelObject;
            }
        }
        throw new NoSuchElementException("No model object with ID " + modelObjectID + " exists.");
    }

    public boolean contains(String modelObjectID) {
        try {
            getByID(modelObjectID);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void add(ModelObject modelObject) throws IllegalArgumentException, IOException {
        if (contains(modelObject.getID())) {
            throw new IllegalArgumentException("A model object with ID " + modelObject.getID() + " already exists.");
        } else {
            listOfModelObjects.add(modelObject);
            save(getFilePath());
        }
    }

    public void remove(String modelObjectID) throws NoSuchElementException, IOException {
        listOfModelObjects.remove(getByID(modelObjectID));
        save(getFilePath());
    }

    public boolean isEmpty() {
        return listOfModelObjects.isEmpty();
    }

    public int size() {
        return listOfModelObjects.size();
    }

    public void clear() throws IOException {
        listOfModelObjects.clear();
        save(getFilePath());
    }

    public void update(ModelObject modelObject) throws NoSuchElementException, IOException {
        ModelObject oldModelObject = getByID(modelObject.getID());
        listOfModelObjects.set(listOfModelObjects.indexOf(oldModelObject), modelObject);
        save(getFilePath());
    }

    public void updateAll(List<ModelObject> modelObjects) throws IOException {
        listOfModelObjects = modelObjects;
        save(getFilePath());
    }

    public void load() throws IOException {
        load(getFilePath());
    }

    public void save() throws IOException {
        save(getFilePath());
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<ModelObject> iterator() {
        return listOfModelObjects.iterator();
    }
}
