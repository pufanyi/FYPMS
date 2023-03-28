package main.boundary.account;

import main.boundary.account.getter.UserNameGetter;
import main.controller.account.AccountManager;
import main.model.user.User;
import main.utils.exception.ui.PageBackException;
import main.utils.ui.ChangePage;

import java.io.IOException;
import java.util.List;

public class ForgotUserID {
    public static void forgotUserID() throws PageBackException {
        ChangePage.changePage();
        String name = UserNameGetter.getUserName();
        System.out.println("The list of user IDs associated with " + name + " is: ");
        List<User> users = AccountManager.getUsersByUserName(name);
        List<String> userIDs = users.stream().map(User::getID).toList();
        String userIDList = String.join(" | ", userIDs);
        System.out.println("| " + userIDList + " |");
        System.out.println("Press any key to go back to the login page.");
        try {
            System.in.read();
        } catch (IOException e) {
            e.printStackTrace();
        }
        throw new PageBackException();
    }
}
