package test.boundary;

import main.boundary.account.AttributeGetter;
import main.model.user.UserType;

/**

 A test class to check the functionality of AttributeGetter class which returns the user type/domain of the user.
 */
public class DomainGetterTest {
    /**
     * The main method that calls AttributeGetter class to get the user type/domain of the user.
     * It prints the retrieved user type/domain of the user.
     */
    public static void main(String[] args) {
        UserType userType = AttributeGetter.getDomain();
        System.out.println("User type: " + userType.toString() + ".");
    }

}
