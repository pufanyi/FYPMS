package test.controller;

import main.controller.account.AccountManager;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import main.utils.exception.PasswordIncorrectException;
import main.utils.exception.ModelAlreadyExistsException;
import main.utils.exception.ModelNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**

 The AccountManagerTest class contains test cases for the AccountManager class.
 */
public class AccountManagerTest {
    /**
     * Before all test, clear all data in the database
     */
    @BeforeEach
    public void setUp() {
        StudentRepository.getInstance().clear();
        FacultyRepository.getInstance().clear();
        CoordinatorRepository.getInstance().clear();
    }

    /**
     * Test case for the register method of AccountManager
     *
     * @throws ModelNotFoundException     if the model cannot be found in the repository
     * @throws ModelAlreadyExistsException if the model already exists in the repository
     */
    @Test
    @DisplayName("Test Register")
    public void testRegister() throws ModelNotFoundException, ModelAlreadyExistsException {
        AccountManager.register(UserType.STUDENT, "FPU001", "Pu Fanyi", "FPU001@e.ntu.edu.sg");
        assertEquals("FPU001@e.ntu.edu.sg", StudentRepository.getInstance().getByID("FPU001").getEmail());
    }

    /**
     * Test case for the login method of AccountManager
     *
     * @throws PasswordIncorrectException if the password provided is incorrect
     * @throws ModelNotFoundException     if the model cannot be found in the repository
     * @throws ModelAlreadyExistsException if the model already exists in the repository
     */
    @Test
    @DisplayName("Test Login")
    public void testLogin() throws PasswordIncorrectException, ModelNotFoundException, ModelAlreadyExistsException {
        User user = AccountManager.register(UserType.STUDENT, "FPU001", "Pu Fanyi", "FPU001@e.ntu.edu.sg");
        assertEquals(user.getID(), AccountManager.login(UserType.STUDENT, "FPU001", "password").getID());
        assertThrows(PasswordIncorrectException.class, () -> AccountManager.login(UserType.STUDENT, "FPU001", "wrong password"));
    }
}
