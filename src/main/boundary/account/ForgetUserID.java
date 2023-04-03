package main.boundary.account;

import main.controller.account.AccountManager;
import main.model.user.User;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.io.IOException;
import java.util.List;

import static main.controller.account.user.UserDomainGetter.getUserDomain;

public class ForgetUserID {
    public static void forgotUserID() throws PageBackException {
        ChangePage.changePage();
        String name = AttributeGetter.getUserName();
        List<User> users = AccountManager.getUsersByUserName(name);
        if (users.isEmpty()) {
            System.out.println("No user found with name " + name + ".");
        } else {
            System.out.println("Found " + users.size() + " user(s) with name " + name + ".");
            System.out.println("The list of UserID associated with " + name + " is:");
            System.out.println("┌--------------------------------------┐");
            if (users.isEmpty()) {
                System.out.println("| No user IDs found for " + name + " |");
            } else {
                System.out.println("| User ID          | User Domain       |");
                System.out.println("|--------------------------------------|");
                for (User user : users) {
                    System.out.printf("| %-17s| %-18s|\n", user.getID(), getUserDomain(user));
                }
            }
            System.out.println("└--------------------------------------┘");
        }
        System.out.println("Press Enter key to go back.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new PageBackException();
    }
}