package test.boundary;

import main.boundary.mainpage.StudentMainPage;
import main.controller.account.AccountManager;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.StudentRepository;
import main.utils.exception.ModelAlreadyExistsException;

/**

 This class tests the functionality of the StudentMainPage class.
 */
public class StudentMainPageTest {
    /**
     * Tests the StudentMainPage class by creating a new student user and opening the main page for that user.
     *
     * @throws ModelAlreadyExistsException if the student already exists in the system.
     */
    public static void main(String[] args) throws ModelAlreadyExistsException {
        StudentRepository.getInstance().clear();
        User user = AccountManager.register(UserType.STUDENT, "D220006", "Nicole", "D220006@e.ntu.edu.sg");
        StudentMainPage.studentMainPage(user);
    }
}
