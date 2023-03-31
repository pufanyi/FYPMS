package test.boundary.account;

import main.boundary.account.AttributeGetter;
import main.model.user.UserType;

public class DomainGetterTest {

    public static void main(String[] args) {
        UserType userType = AttributeGetter.getDomain();
        System.out.println("User type: " + userType.toString() + ".");
    }

}
