package test.ui.account;

import main.boundary.account.LoginUI;
import main.controller.account.AccountManager;
import main.model.user.UserType;
import main.repository.user.StudentRepository;
import main.utils.exception.repository.ModelAlreadyExistsException;
import main.utils.exception.ui.PageBackException;

public class LoginUITest {
    public static void main(String[] args) throws PageBackException, ModelAlreadyExistsException {
        StudentRepository.getInstance().clear();
        AccountManager.register(
                UserType.STUDENT, "FPU001", "Pu Fanyi", "FPU001@e.ntu.edu.sg"
        );
        LoginUI.login();
    }
}
