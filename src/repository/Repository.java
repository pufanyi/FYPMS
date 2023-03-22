package repository;

import model.Model;
import utils.iocontrol.Savable;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public abstract class Repository<ModelObject extends Model> extends Savable<ModelObject> {
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
    public List<ModelObject> getListOfMappableObjects() {
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

    public boolean findByID(String modelObjectID) {
        try {
            getByID(modelObjectID);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void add(ModelObject modelObject) throws IllegalArgumentException {
        if (findByID(modelObject.getID())) {
            throw new IllegalArgumentException("A model object with ID " + modelObject.getID() + " already exists.");
        } else {
            listOfModelObjects.add(modelObject);
        }
    }

    public void remove(String modelObjectID) throws NoSuchElementException {
        listOfModelObjects.remove(getByID(modelObjectID));
    }
}
