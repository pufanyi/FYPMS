package test.ui.account;

import main.boundary.account.getter.DomainGetter;
import main.model.user.UserType;

public class DomainGetterTest {

    public static void main(String[] args) {
        UserType userType = DomainGetter.getDomain();
        System.out.println("User type: " + userType.toString() + ".");
    }

}
