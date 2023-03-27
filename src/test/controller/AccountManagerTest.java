package test.controller;

import main.controller.account.AccountManager;
import main.model.user.Student;
import main.model.user.User;
import main.model.user.UserType;
import main.repository.user.CoordinatorRepository;
import main.repository.user.FacultyRepository;
import main.repository.user.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    @DisplayName("Test Register")
    public void testRegister() {
        AccountManager.register(UserType.STUDENT, "FPU001", "Pu Fanyi", "FPU001@e.ntu.edu.sg");
        assertEquals("FPU001@e.ntu.edu.sg", StudentRepository.getInstance().getByID("FPU001").getEmail());
    }
}
