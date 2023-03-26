package main.repository;

import main.model.Model;
import main.utils.iocontrol.Savable;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

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

    public void add(ModelObject modelObject) throws IllegalArgumentException {
        if (contains(modelObject.getID())) {
            throw new IllegalArgumentException("A model object with ID " + modelObject.getID() + " already exists.");
        } else {
            listOfModelObjects.add(modelObject);
            save(getFilePath());
        }
    }

    public void remove(String modelObjectID) throws NoSuchElementException {
        listOfModelObjects.remove(getByID(modelObjectID));
        save(getFilePath());
    }

    public boolean isEmpty() {
        return listOfModelObjects.isEmpty();
    }

    public int size() {
        return listOfModelObjects.size();
    }

    public void clear() {
        listOfModelObjects.clear();
        save(getFilePath());
    }

    public void update(ModelObject modelObject) throws NoSuchElementException {
        ModelObject oldModelObject = getByID(modelObject.getID());
        listOfModelObjects.set(listOfModelObjects.indexOf(oldModelObject), modelObject);
        save(getFilePath());
    }

    public void updateAll(List<ModelObject> modelObjects) {
        listOfModelObjects = modelObjects;
        save(getFilePath());
    }

    public void load() {
        this.listOfModelObjects = new ArrayList<>();
        load(getFilePath());
    }

    public void save() {
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

    public interface RepositoryRule<ModelObject> {
        boolean isMatch(ModelObject modelObject);
    }

    @SafeVarargs
    public final List<ModelObject> findByRules(RepositoryRule<ModelObject>... rules) {
        List<ModelObject> modelObjects = new ArrayList<>();
        for (ModelObject modelObject : listOfModelObjects) {
            boolean isMatch = true;
            for (RepositoryRule<ModelObject> rule : rules) {
                if (!rule.isMatch(modelObject)) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                modelObjects.add(modelObject);
            }
        }
        return modelObjects;
    }
}
