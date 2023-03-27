package main.boundary.account;

import main.boundary.account.getter.UserNameGetter;
import main.controller.account.AccountManager;
import main.model.user.User;

import java.util.List;

public class ForgotUserID {
    public static void forgotUserID() {
        String name = UserNameGetter.getUserName();
        System.out.println("The list of user IDs associated with " + name + " is: ");
        List<User> users = AccountManager.getUsersByUserName(name);
        List<String> userIDs = users.stream().map(User::getID).toList();
        String userIDList = String.join(" | ", userIDs);
        System.out.println("| " + userIDList + " |");
    }
}
