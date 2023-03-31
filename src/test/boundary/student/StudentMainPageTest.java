package test.boundary.student;

import main.boundary.student.StudentMainPage;
import main.controller.account.AccountManager;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;

public class StudentMainPageTest {
    public static void main(String[] args) throws ModelAlreadyExistsException {
        StudentRepository.getInstance().clear();
        User user = AccountManager.register(UserType.STUDENT, "D220006", "Nicole", "D220006@e.ntu.edu.sg");
        StudentMainPage.studentMainPage(user);
    }
}
