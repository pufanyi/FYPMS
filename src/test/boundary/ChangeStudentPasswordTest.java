package test.boundary;

import main.boundary.account.ChangeAccountPassword;
import main.boundary.account.LoginUI;
import main.controller.account.AccountManager;
import main.model.user.UserType;
import main.repository.user.StudentRepository;
import main.utils.exception.ModelAlreadyExistsException;
import main.utils.exception.PageBackException;

/**
 * This class is responsible for testing the functionality of changing a student's password through the ChangeAccountPassword interface.
 */
public class ChangeStudentPasswordTest {
    /**
     * The main method of this class is responsible for executing the test case of changing a student's password.
     * It creates a new student, registers the student with the system, and then attempts to change their password.
     * Upon completion, the user is redirected to the login page.
     *
     * @param args command line arguments
     * @throws ModelAlreadyExistsException if the model already exists
     * @throws PageBackException           if the user decides to go back to the previous page
     */
    public static void main(String[] args) throws ModelAlreadyExistsException, PageBackException {
        StudentRepository.getInstance().clear();
        AccountManager.register(UserType.STUDENT, "D220006", "Nicole", "D220006@e.ntu.edu.sg");
        try {
            ChangeAccountPassword.changePassword(UserType.STUDENT, "D220006");
        } catch (PageBackException e) {
            System.out.println("Page back.");
        }
        LoginUI.login();
    }
}
