package model.user.userlist;

import model.user.singleuser.Coordinator;

import java.util.List;
import java.util.Map;

/**
 * A class that represents a coordinator
 */
public class CoordinatorList extends UserList<Coordinator> {
    /**
     * Sets the list of mappable objects.
     *
     * @param listOfMappableObjects the list of mappable objects
     */
    @Override
    public void setListOfMappableObjects(List<Map<String, String>> listOfMappableObjects) {
        for (Map<String, String> map : listOfMappableObjects) {
            String coordinatorID = map.get("coordinatorID");
            String coordinatorName = map.get("coordinatorName");
            String email = map.get("email");
            String password = map.get("password");
            Coordinator coordinator = new Coordinator(coordinatorID, coordinatorName, email, password);
            addUser(coordinator);
        }
    }

}
