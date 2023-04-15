package test.boundary;

import main.boundary.account.LoginUI;
import main.controller.account.AccountManager;
import main.model.user.UserType;
import main.repository.user.StudentRepository;
import main.utils.exception.ModelAlreadyExistsException;
import main.utils.exception.PageBackException;


/**

 A test class that tests the functionality of the LoginUI class. It clears the student repository, registers a student,
 then attempts to login with the registered credentials.
 */
public class LoginUITest {
    /**

     The main function to test the login functionality of the LoginUI class.
     It clears the student repository, registers a new student with a userID of "FPU001",
     a name of "Pu Fanyi", and an email of "FPU001@e.ntu.edu.sg". Then it calls the LoginUI
     login function to test if the login functionality works correctly.
     @throws PageBackException if the user navigates back to the previous page
     @throws ModelAlreadyExistsException if the model to be registered already exists in the repository
     */
    public static void main(String[] args) throws PageBackException, ModelAlreadyExistsException {
        StudentRepository.getInstance().clear();
        AccountManager.register(
                UserType.STUDENT, "FPU001", "Pu Fanyi", "FPU001@e.ntu.edu.sg"
        );
        LoginUI.login();
    }
}
