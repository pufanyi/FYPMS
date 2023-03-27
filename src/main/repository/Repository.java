package main.repository;

import main.model.Model;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.repository.ModelNotFoundException;
import main.utils.iocontrol.Savable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Repository<ModelObject extends Model> extends Savable<ModelObject> implements Iterable<ModelObject> {
    List<ModelObject> listOfModelObjects;

    public Repository() {
        super();
        listOfModelObjects = new ArrayList<>();
    }

    public abstract String getFilePath();

    /**
     * Gets the list of mappable objects.
     *
     * @return the list of mappable objects
     */
    @Override
    protected List<ModelObject> getAll() {
        return listOfModelObjects;
    }

    /**
     * method to get the by ID
     *
     * @param modelObjectID
     * @return
     */
    public ModelObject getByID(String modelObjectID) throws ModelNotFoundException {
        for (ModelObject modelObject : listOfModelObjects) {
            if (modelObject.getID().equals(modelObjectID)) {
                return modelObject;
            }
        }
        throw new ModelNotFoundException("No model object with ID " + modelObjectID + " exists.");
    }

    public boolean contains(String modelObjectID) {
        try {
            getByID(modelObjectID);
            return true;
        } catch (ModelNotFoundException e) {
            return false;
        }
    }

    public void add(ModelObject modelObject) throws ModelAlreadyExistsException {
        if (contains(modelObject.getID())) {
            throw new ModelAlreadyExistsException("A model object with ID " + modelObject.getID() + " already exists.");
        } else {
            listOfModelObjects.add(modelObject);
            save(getFilePath());
        }
    }

    public void remove(String modelObjectID) throws NoSuchElementException, ModelNotFoundException {
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

    public void update(ModelObject modelObject) throws NoSuchElementException, ModelNotFoundException {
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

    public List<ModelObject> getList() {
        return findByRules();
    }

    public interface RepositoryRule<ModelObject> {
        boolean isMatch(ModelObject modelObject);
    }
}
